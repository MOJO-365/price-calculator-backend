package com.solar.calculator.config;

import com.solar.calculator.dto.PageResult;
import jakarta.annotation.PostConstruct;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GlobalDatabase {

    private static final int MAX_POOL_SIZE = 10;
    private static final List<Connection> connectionPool = new ArrayList<>();
    private static GlobalDatabase instance;

    private static final String DATABASE_URL="jdbc:postgresql://ccba8a0vn4fb2p.cluster-czrs8kj4isg7.us-east-1.rds.amazonaws.com:5432/d56u92rh5pn7dl";
    private static final String USERNAME="u4aemm9ip8eij1";
    private static final String PASSWORD="pff8bd0a590b2a5d0eec25b8bbf4615d24fb6196f5b022100cc001cdd711e8b93";

    public GlobalDatabase() {
    }

    @PostConstruct
    private void initializePool() {
        try {
            for (int i = 0; i < MAX_POOL_SIZE / 2; i++) {
                connectionPool.add(createConnection());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing connection pool", e);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    private synchronized Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            return createConnection();
        }
        return connectionPool.remove(connectionPool.size() - 1);
    }

    private synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }

    public <T> T executeQuery(String sql, ResultSetExtractor<T> extractor) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            return extractor.extractData(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("SQL execution failed", e);
        }
    }

    public int executeUpdate(String sql, Object... params) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("SQL execution failed", e);
        }
    }


    @FunctionalInterface
    public interface ResultSetExtractor<T> {
        T extractData(ResultSet rs) throws SQLException;
    }

    public enum FilterType {
        EQUALS, CONTAINS, GREATER_THAN, LESS_THAN, STARTS_WITH, ENDS_WITH;
    }

    public <T> PageResult<T> executePaginatedQuery(String companyName, String tableName, List<String> columns,
                                                   Map<String, String> orderByColumns,
                                                   int pageNumber, int pageSize,
                                                   Map<String, Pair<FilterType, Object>> filters,
                                                   RowMapper<T> rowMapper) {

        String sanitizedTableName = sanitizeIdentifier(tableName);
        String tenantSchema = sanitizeIdentifier(companyName);

        String columnNames = (columns != null && !columns.isEmpty())
                ? columns.stream().map(this::sanitizeIdentifier).collect(Collectors.joining(", "))
                : "*"; // Default to all columns

        String whereClause = buildWhereClause(filters);

        String countQuery = "SELECT COUNT(*) FROM " + tenantSchema + "." + sanitizedTableName + whereClause;
        long totalElements = executeQuery(countQuery, resultSet -> {
            resultSet.next();
            return resultSet.getLong(1);
        });

        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        StringBuilder dataQuery = new StringBuilder(String.format("SELECT %s FROM %s.%s %s", columnNames, tenantSchema, sanitizedTableName, whereClause));

        if (orderByColumns != null && !orderByColumns.isEmpty()) {
            String orderByClause = buildOrderByClause(orderByColumns);
            dataQuery.append(" ").append(orderByClause);
        }

        dataQuery.append(String.format(" LIMIT %d OFFSET %d", pageSize, pageNumber * pageSize));

        List<T> content = executeQuery(dataQuery.toString(), resultSet -> {
            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet, resultSet.getRow()));
            }
            return result;
        });

        return new PageResult<>(content, pageNumber, pageSize, totalElements, totalPages);
    }

    private String buildWhereClause(Map<String, Pair<FilterType, Object>> filters) {
        if (filters == null || filters.isEmpty()) {
            return "";
        }

        List<String> conditions = new ArrayList<>();
        for (Map.Entry<String, Pair<FilterType, Object>> entry : filters.entrySet()) {
            String column = sanitizeIdentifier(entry.getKey());
            FilterType filterType = entry.getValue().getFirst();
            Object value = entry.getValue().getSecond();

            switch (filterType) {
                case EQUALS:
                    conditions.add(column + " = '" + value + "'");
                    break;
                case CONTAINS:
                    conditions.add(column + " LIKE '%" + value + "%'");
                    break;
                case GREATER_THAN:
                    conditions.add(column + " > '" + value + "'");
                    break;
                case LESS_THAN:
                    conditions.add(column + " < '" + value + "'");
                    break;
                case STARTS_WITH:
                    conditions.add(column + " LIKE '" + value + "%'");
                    break;
                case ENDS_WITH:
                    conditions.add(column + " LIKE '%" + value + "'");
                    break;
            }
        }
        return " WHERE " + String.join(" AND ", conditions);
    }


    private String buildOrderByClause(Map<String, String> orderByColumns) {
        if (orderByColumns == null || orderByColumns.isEmpty()) {
            return ""; // Return empty string if there are no columns to order by
        }

        StringBuilder orderByClause = new StringBuilder("ORDER BY ");

        for (Map.Entry<String, String> entry : orderByColumns.entrySet()) {
            String sanitizedColumn = sanitizeIdentifier(entry.getKey());
            String sanitizedDirection = validateSortDirection(entry.getValue());

            orderByClause.append(sanitizedColumn)
                    .append(" ")
                    .append(sanitizedDirection);

            if (orderByColumns.size() > 1) {
                orderByClause.append(", ");
            }
            orderByColumns.remove(sanitizedColumn);
        }

        return orderByClause.toString();
    }


    private String sanitizeIdentifier(String identifier) {
        return identifier.replaceAll("[^a-zA-Z0-9_]", ""); // Prevents SQL injection
    }

    private String validateSortDirection(String sortDirection) {
        return ("DESC".equalsIgnoreCase(sortDirection)) ? "DESC" : "ASC"; // Ensures only ASC or DESC
    }

}
