package com.solar.calculator.utils;

import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.entity.Battery;
import com.solar.calculator.entity.Inverter;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class InverterUtil {

    GlobalDatabase globalDatabase;

    private static final String INVERTER_TABLE = "inverter";

    public InverterUtil(GlobalDatabase globalDatabase) {
        this.globalDatabase = globalDatabase;
    }


    public PageResult<Inverter> getInverter(String company, Integer pageNumber, Integer pageSize, List<String> columns,
                                            Map<String, String> orderByColumns, Map<String, Pair<GlobalDatabase.FilterType,
            Object>> filter) {

        return globalDatabase.executePaginatedQuery(company, INVERTER_TABLE, columns, orderByColumns, pageNumber, pageSize, filter, new InverterRowMapper());
    }

    public String addInverter(String company, Inverter inverter) {
        String sql = String.format("INSERT INTO %s.inverter (model, manufacturer, price, warranty_in_months,updated_at,createdAt,phase,mppt,is_hybrid)" +
                "VALUES(?,?,?,?,?,?)", company);
        try {
            globalDatabase.executeUpdate(sql, inverter.getModel(), inverter.getManufacturer(), inverter.getPrice(),
                    inverter.getWarrantyInMonths(), LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().getEpochSecond(),
                    LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().getEpochSecond(), inverter.getPhase(),
                    inverter.getMppt(),inverter.getIsHybrid());
            return "Inverter Added Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public class InverterRowMapper implements RowMapper<Inverter> {

        @Override
        public Inverter mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();

            Inverter inverter = new Inverter();

            if (columnExists(metaData, "id")) {
                inverter.setId(rs.getInt("id"));
            }
            if (columnExists(metaData, "model")) {
                inverter.setModel(rs.getString("model"));
            }
            if (columnExists(metaData, "manufacturer")) {
                inverter.setManufacturer(rs.getString("manufacturer"));
            }
            if (columnExists(metaData, "price")) {
                inverter.setPrice(rs.getInt("price"));
            }
            if (columnExists(metaData, "warranty_in_months")) {
                inverter.setWarrantyInMonths(rs.getInt("warranty_in_months"));
            }
            if (columnExists(metaData, "updated_at")) {
                inverter.setUpdatedAt(rs.getLong("updated_at"));
            }
            if (columnExists(metaData, "createdAt")) {
                inverter.setCreatedAt(rs.getLong("createdAt"));
            }
            if(columnExists(metaData, "phase")){
                inverter.setPhase(Battery.Phase.valueOf(rs.getString("phase")));
            }
            if(columnExists(metaData,"mppt")){
                inverter.setMppt(rs.getInt("mppt"));
            }
            if(columnExists(metaData,"is_hybrid")){
                inverter.setIsHybrid(Boolean.parseBoolean(rs.getString("is_hybrid")));
            }

            return inverter;
        }

        private boolean columnExists(ResultSetMetaData metaData, String columnName) throws SQLException {
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                if (metaData.getColumnLabel(i).equalsIgnoreCase(columnName)) {
                    return true;
                }
            }
            return false;
        }
    }
}
