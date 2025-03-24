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
        String sql = String.format("INSERT INTO %s.Price (name, sub_id, nsw,qld,act,vic,wa,sa,tas,nt,jbt,updated_at,msg) VALUES (?, ?,?::json,?::json,?::json,?::json,?::json,?::json,?::json,?::json,?::json,?,?);", company);
        try {
            globalDatabase.executeUpdate(sql, price.getName(), price.getSubId(), objectMapper.writeValueAsString(price.getNsw()),
                    objectMapper.writeValueAsString(price.getQld()),objectMapper.writeValueAsString(price.getAct()),
                    objectMapper.writeValueAsString(price.getVic()),objectMapper.writeValueAsString(price.getWa()),
                    objectMapper.writeValueAsString(price.getSa()),objectMapper.writeValueAsString(price.getTas()),
                    objectMapper.writeValueAsString(price.getNt()),objectMapper.writeValueAsString(price.getJbt()),
                    LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().getEpochSecond(),
                    price.getMessage());
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
            price.setMessage(rs.getString("msg"));
            try {
                price.setNsw(objectMapper.readValue(rs.getString("nsw"), new TypeReference<Price.UnitPrice>(){}));
                price.setQld(objectMapper.readValue(rs.getString("qld"), new TypeReference<Price.UnitPrice>(){}));
                price.setAct(objectMapper.readValue(rs.getString("act"), new TypeReference<Price.UnitPrice>(){}));
                price.setVic(objectMapper.readValue(rs.getString("vic"), new TypeReference<Price.UnitPrice>(){}));
                price.setWa(objectMapper.readValue(rs.getString("wa"), new TypeReference<Price.UnitPrice>(){}));
                price.setSa(objectMapper.readValue(rs.getString("sa"), new TypeReference<Price.UnitPrice>(){}));
                price.setTas(objectMapper.readValue(rs.getString("tas"), new TypeReference<Price.UnitPrice>(){}));
                price.setNt(objectMapper.readValue(rs.getString("nt"), new TypeReference<Price.UnitPrice>(){}));
                price.setJbt(objectMapper.readValue(rs.getString("jbt"), new TypeReference<Price.UnitPrice>(){}));

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
                price.setMessage(rs.getString("msg"));
                try {
                    price.setNsw(objectMapper.readValue(rs.getString("nsw"), new TypeReference<Price.UnitPrice>(){}));
                    price.setQld(objectMapper.readValue(rs.getString("qld"), new TypeReference<Price.UnitPrice>(){}));
                    price.setAct(objectMapper.readValue(rs.getString("act"), new TypeReference<Price.UnitPrice>(){}));
                    price.setVic(objectMapper.readValue(rs.getString("vic"), new TypeReference<Price.UnitPrice>(){}));
                    price.setWa(objectMapper.readValue(rs.getString("wa"), new TypeReference<Price.UnitPrice>(){}));
                    price.setSa(objectMapper.readValue(rs.getString("sa"), new TypeReference<Price.UnitPrice>(){}));
                    price.setTas(objectMapper.readValue(rs.getString("tas"), new TypeReference<Price.UnitPrice>(){}));
                    price.setNt(objectMapper.readValue(rs.getString("nt"), new TypeReference<Price.UnitPrice>(){}));
                    price.setJbt(objectMapper.readValue(rs.getString("jbt"), new TypeReference<Price.UnitPrice>(){}));

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                prices.add(price);
            }
            return prices;
        }
    }

}
