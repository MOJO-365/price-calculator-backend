package com.solar.calculator.utils;

import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseUtil {
    private GlobalDatabase globalDatabase;

    private final static String WAREHOUSE_TABLE="warehouse";

    @Autowired
    public WarehouseUtil(GlobalDatabase globalDatabase){
        this.globalDatabase=globalDatabase;
    }

    public PageResult<Warehouse> getWarehouse(String company, Map<String, Pair<GlobalDatabase.FilterType, Object>> filter, Map<String, String> orderByColumns, Integer pageNumber, Integer pageSize, List<String> columns) {
        return globalDatabase.executePaginatedQuery(company,WAREHOUSE_TABLE,columns,orderByColumns,pageNumber,pageSize,
                filter,new WarehouseRowMapper());
    }

    public String insertWarehouse(String company, Warehouse warehouse){
        String sql=String.format("INSERT INTO %s.warehouse (company_name, address, postcode, state, is_active, updated_at, created_at)\n" +
                "VALUES (?, ?,?,?,?,?,?);",company);
        try{
            globalDatabase.executeUpdate(sql,warehouse.getCompanyName(),warehouse.getAddress(),warehouse.getPostcode(),
                    warehouse.getState().name(),warehouse.isActive(),
                    LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().getEpochSecond(),
                    LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().getEpochSecond());
            return "Warehouse Details Added Successfully";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    public  class WarehouseRowMapper implements RowMapper<Warehouse> {
        @Override
        public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(rs.getInt("id"));
            warehouse.setCompanyName(rs.getString("company_name"));
            warehouse.setAddress(rs.getString("address"));
            warehouse.setPostcode(rs.getInt("postcode"));
            warehouse.setState(Warehouse.State.valueOf(rs.getString("state")));
            warehouse.setActive(rs.getBoolean("is_active"));
            warehouse.setUpdatedAt(rs.getLong("updated_at"));
            warehouse.setCreatedAt(rs.getLong("created_at"));
            return warehouse;
        }
    }

}
