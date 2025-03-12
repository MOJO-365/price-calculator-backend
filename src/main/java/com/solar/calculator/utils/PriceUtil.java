package com.solar.calculator.utils;

import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceUtil {

    private GlobalDatabase globalDatabase;

    @Autowired
    public PriceUtil(GlobalDatabase globalDatabase){
        this.globalDatabase=globalDatabase;
    }

    public List<Price> getPrice(String company) {
        String sql=String.format("select * from %s.price;",company);
        return globalDatabase.executeQuery(sql, new PriceResultSetExtractor());
    }


    public String insertPrice(String company,Price price){
        String sql=String.format("INSERT INTO %s.Price (name, unit, price) VALUES (?, ?,?);",company);
        try {
            globalDatabase.executeUpdate(sql,price.getName(),price.getUnit(),price.getPrice());
            return "Price Added Successfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }

     class PriceRowMapper implements RowMapper<Price> {
        @Override
        public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
            Price price = new Price();
            price.setId(rs.getInt("id"));
            price.setName(rs.getString("name"));
            price.setUnit(rs.getString("unit"));
            price.setPrice(rs.getDouble("price"));
            return price;
        }
    }

     static class PriceResultSetExtractor implements GlobalDatabase.ResultSetExtractor<List<Price>> {
        @Override
        public List<Price> extractData(ResultSet rs) throws SQLException {
            List<Price> prices = new ArrayList<>();
            while (rs.next()) {
                Price price = new Price();
                price.setId(rs.getInt("id"));
                price.setName(rs.getString("name"));
                price.setUnit(rs.getString("unit"));
                price.setPrice(rs.getDouble("price"));
                price.setMsg(rs.getString("msg"));
                prices.add(price);
            }
            return prices;
        }
    }

}
