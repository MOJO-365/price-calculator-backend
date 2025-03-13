package com.solar.calculator.service;

import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.controller.DatabaseSetupController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class DatabaseSetupService {

    @Value("classpath:schema.sql")
    private Resource schemaResource;

    @Value("classpath:sample-data.sql")
    private Resource sampleDataResource;

    private  GlobalDatabase globalDatabase;

    @Autowired
    public DatabaseSetupService(GlobalDatabase globalDatabase){
        this.globalDatabase=globalDatabase;
    }

    public String setupDatabase(String company) {
        try {
            String sql = Files.readString(schemaResource.getFile().toPath());
            sql = sql.replace("${company}", company);
            globalDatabase.executeUpdate(sql);
            return "Database Created Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addSampleData(String company) {
        try {
            String sql = Files.readString(sampleDataResource.getFile().toPath());
            sql =sql.replace("${company}", company);
            globalDatabase.executeUpdate(sql);
            return "Database Updated Successfully";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
