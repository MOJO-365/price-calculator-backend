package com.solar.calculator.controller;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.service.BatteryService;
import com.solar.calculator.utils.DeferredResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/battery")
public class BatteryController {

    private BatteryService batteryService;

    @Autowired
    BatteryController(BatteryService batteryService){
        this.batteryService =batteryService;
    }

    @PostMapping("/get-battery")
    public DeferredResult<PageResult<?>> getBattery(@RequestBody GeneralQueryRequest generalQueryRequest){
        return DeferredResultUtil.executeAsync(()->{
            try {
               return batteryService.getBattery(generalQueryRequest);
            }catch (Exception e){
                return new PageResult<>(e.getMessage());
            }
        });
    }

    @PostMapping("/add-battery")
    public DeferredResult<String> addBattery(@RequestBody PostQueryRequest postQueryRequest){
        return DeferredResultUtil.executeAsync(()->{
            return batteryService.addBattery(postQueryRequest);
        });
    }
}
