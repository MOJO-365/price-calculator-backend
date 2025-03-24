package com.solar.calculator.controller;


import com.solar.calculator.service.DatabaseSetupService;
import com.solar.calculator.utils.DeferredResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/v1/database")
@CrossOrigin("*")
public class DatabaseSetupController {

    @Autowired
    DatabaseSetupService databaseSetupService;

    @PostMapping("/setup")
    public DeferredResult<String> setupDatabase(@RequestParam(required = true) String company) {
        return DeferredResultUtil.executeAsync(() -> {
            return databaseSetupService.setupDatabase(company);
        });
    }

    @PostMapping("/add-sample-data")
    public DeferredResult<String> addSampleData(@RequestParam(required = true) String company) {
        return DeferredResultUtil.executeAsync(() -> {
            return databaseSetupService.addSampleData(company);
        });
    }

}
