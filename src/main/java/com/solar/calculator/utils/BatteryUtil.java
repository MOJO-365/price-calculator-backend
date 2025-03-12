package com.solar.calculator.utils;

import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.entity.Battery;
import com.solar.calculator.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class BatteryUtil {

    private GlobalDatabase globalDatabase;

    private final String BATTERY_TABLE="battery";

    @Autowired
    public BatteryUtil(GlobalDatabase globalDatabase){
        this.globalDatabase=globalDatabase;
    }


    public PageResult<Battery> getBattery(String company, Integer pageNumber, Integer pageSize, List<String> columns, Map<String, Pair<GlobalDatabase.FilterType, Object>> filter, Map<String, String> orderByColumns) {
     return globalDatabase.executePaginatedQuery(company,BATTERY_TABLE,columns,orderByColumns,pageNumber,pageSize,filter, new BatteryRowMapper());
    }

    public String addBattery(String company, Battery battery) {
        String sql=String.format("INSERT INTO %s.battery (model, manufacturer, warranty_in_months, price, capacity_in_watt) \n" +
                "VALUES (?, ?, ?, ?, ?);",company);
        try {
            globalDatabase.executeUpdate(sql,battery.getModelName(),battery.getManufacturer(),
                    battery.getWarrantyInMonths(),battery.getPrice(),battery.getCapacity());
            return "Battery Added Successfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public static class BatteryRowMapper implements RowMapper<Battery> {

        @Override
        public Battery mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            Integer id = null;
            String modelName = null;
            String manufacturer = null;
            Integer warrantyInMonths = null;
            Integer price = null;
            Integer capacity = null;

            if (columnExists(metaData, "id")) {
                id = rs.getInt("id");
            }

            if (columnExists(metaData, "model")) {
                modelName = rs.getString("model");
            }

            if (columnExists(metaData, "manufacturer")) {
                manufacturer = rs.getString("manufacturer");
            }

            if (columnExists(metaData, "warranty_in_months")) {
                warrantyInMonths = rs.getInt("warranty_in_months");
            }

            if (columnExists(metaData, "price")) {
                price = rs.getInt("price");
            }

            if (columnExists(metaData, "capacity_in_watt")) {
                capacity = rs.getInt("capacity_in_watt");
            }

            return new Battery(id, modelName, manufacturer, warrantyInMonths, price, capacity);
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
