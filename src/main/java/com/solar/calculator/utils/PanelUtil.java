package com.solar.calculator.utils;

import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.entity.Panel;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class PanelUtil {

    private final GlobalDatabase globalDatabase;

    private final static String PANEL_TABLE = "panel";

    public PanelUtil(GlobalDatabase globalDatabase) {
        this.globalDatabase = globalDatabase;
    }

    public PageResult<Panel> getPanel(String company, List<String> columns, Map<String, String> orderByColumns,
                                      int pageNumber, int pageSize,
                                      Map<String, GeneralQueryRequest.FilterCriteria> filters) {
        return globalDatabase.executePaginatedQuery(company, PANEL_TABLE, columns, orderByColumns, pageNumber,
                pageSize, filters, new PanelRowMapper());
    }

    public String addPanel(String company, Panel panel) {
        String sql = String.format("INSERT INTO %s.panel (model, manufacturer, warranty_in_months, watt, price, createdAt, updated_at) \nVALUES" +
                "(?,?, ?, ?,?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);", company);
        try {
            globalDatabase.executeUpdate(sql, panel.getModel(), panel.getManufacturer(), panel.getWarrantyInMonths(), panel.getWatt(), panel.getPrice());
            return "Successfully stored in database";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    static class PanelRowMapper implements RowMapper<Panel> {
        @Override
        public Panel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Panel panel = new Panel();

            // Check if 'id' column exists
            if (columnExists(rs, "id")) {
                panel.setId(getNullableInt(rs, "id"));
            }

            // Check if 'model' column exists
            if (columnExists(rs, "model")) {
                panel.setModel(rs.getString("model"));
            }

            // Check if 'manufacturer' column exists
            if (columnExists(rs, "manufacturer")) {
                panel.setManufacturer(rs.getString("manufacturer"));
            }

            // Check if 'warranty_in_months' column exists
            if (columnExists(rs, "warranty_in_months")) {
                panel.setWarrantyInMonths(getNullableInt(rs, "warranty_in_months"));
            }

            // Check if 'watt' column exists
            if (columnExists(rs, "watt")) {
                panel.setWatt(getNullableInt(rs, "watt"));
            }

            // Check if 'price' column exists
            if (columnExists(rs, "price")) {
                panel.setPrice(getNullableDouble(rs, "price"));
            }

            // Check if 'updated_at' column exists
            if (columnExists(rs, "updated_at")) {
                panel.setUpdatedAt(getNullableLong(rs, "updated_at"));
            }

            return panel;
        }

        // Utility method to check if a column exists in the ResultSet
        private boolean columnExists(ResultSet rs, String columnLabel) throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                if (metaData.getColumnLabel(i).equalsIgnoreCase(columnLabel)) {
                    return true;
                }
            }
            return false;
        }

        // Utility methods for nullable fields
        private Integer getNullableInt(ResultSet rs, String columnLabel) throws SQLException {
            int value = rs.getInt(columnLabel);
            return rs.wasNull() ? null : value;
        }

        private Double getNullableDouble(ResultSet rs, String columnLabel) throws SQLException {
            double value = rs.getDouble(columnLabel);
            return rs.wasNull() ? null : value;
        }

        private Long getNullableLong(ResultSet rs, String columnLabel) throws SQLException {
            Timestamp timestamp = rs.getTimestamp(columnLabel);
            return (timestamp != null) ? timestamp.getTime() : null;
        }
    }


}
