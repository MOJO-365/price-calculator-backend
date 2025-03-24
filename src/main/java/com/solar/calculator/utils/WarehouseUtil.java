package com.solar.calculator.utils;

import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

@Service
public class WarehouseUtil {
    private GlobalDatabase globalDatabase;

    private final static String WAREHOUSE_TABLE = "warehouse";

    @Autowired
    public WarehouseUtil(GlobalDatabase globalDatabase) {
        this.globalDatabase = globalDatabase;
    }

    public PageResult<Warehouse> getWarehouse(String company) {

        return globalDatabase.executePaginatedQuery(company, WAREHOUSE_TABLE, new ArrayList<>(), null, 0, 1000,
                null, new WarehouseRowMapper());
    }

    public String insertWarehouse(String company, Warehouse warehouse) {
        String sql = String.format("INSERT INTO %s.warehouse (warehouse_name, address, postcode, state, is_active, updated_at, createdAt,latitude, longitude)\n" +
                "VALUES (?, ?,?,?,?,?,?,?,?);", company);
        try {
            globalDatabase.executeUpdate(sql, warehouse.getWarehouseName(), warehouse.getAddress(), warehouse.getPostcode(),
                    warehouse.getState().name(), warehouse.isActive(),
                    LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().getEpochSecond(),
                    LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().getEpochSecond(),
                    warehouse.getLatitude(), warehouse.getLongitude());
            return "Warehouse Details Added Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public class WarehouseRowMapper implements RowMapper<Warehouse> {
        @Override
        public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(rs.getInt("id"));
            warehouse.setWarehouseName(rs.getString("warehouse_name"));
            warehouse.setAddress(rs.getString("address"));
            warehouse.setPostcode(rs.getInt("postcode"));
            warehouse.setState(Warehouse.State.valueOf(rs.getString("state")));
            warehouse.setLatitude(rs.getString("latitude"));
            warehouse.setLongitude(rs.getString("longitude"));
            warehouse.setActive(rs.getBoolean("is_active"));
            warehouse.setUpdatedAt(rs.getLong("updated_at"));
            warehouse.setCreatedAt(rs.getLong("created_at"));
            return warehouse;
        }
    }

}
