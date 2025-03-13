package com.solar.calculator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PriceUtil {

    private GlobalDatabase globalDatabase;

    private ObjectMapper objectMapper;

    @Autowired
    public PriceUtil(GlobalDatabase globalDatabase, ObjectMapper objectMapper) {
        this.globalDatabase = globalDatabase;
        this.objectMapper = objectMapper;
    }

    public List<Price> getPrice(String company) {
        String sql = String.format("select * from %s.price;", company);
        return globalDatabase.executeQuery(sql, new PriceResultSetExtractor());
    }


    public String insertPrice(String company, Price price) {
        String sql = String.format("INSERT INTO %s.Price (name, sub_id, option_state_pricing,updated_at) VALUES (?, ?,?::json,?);", company);
        try {
            globalDatabase.executeUpdate(sql, price.getName(), price.getSubId(), objectMapper.writeValueAsString(price.getStatePricing()),
                    LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().getEpochSecond());
            return "Price Added Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    class PriceRowMapper implements RowMapper<Price> {
        @Override
        public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
            Price price = new Price();
            price.setId(rs.getInt("id"));
            price.setName(rs.getString("name"));
            price.setSubId(rs.getString("sub_id"));
            price.setUpdatedAt(rs.getLong("updated_at"));
            try {
                price.setStatePricing(objectMapper.readValue(rs.getString("msg"), new TypeReference<HashMap<String, Price.StatePricing>>() {
                }));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return price;
        }
    }

    class PriceResultSetExtractor implements GlobalDatabase.ResultSetExtractor<List<Price>> {
        @Override
        public List<Price> extractData(ResultSet rs) throws SQLException {
            List<Price> prices = new ArrayList<>();
            while (rs.next()) {
                Price price = new Price();
                price.setId(rs.getInt("id"));
                price.setName(rs.getString("name"));
                price.setSubId(rs.getString("sub_id"));
                price.setUpdatedAt(rs.getLong("updated_at"));
                try {
                    price.setStatePricing(objectMapper.readValue(rs.getString("option_state_pricing"), new TypeReference<HashMap<String, Price.StatePricing>>() {
                    }));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                prices.add(price);
            }
            return prices;
        }
    }

}
