package com.solar.calculator.controller;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.service.InverterService;
import com.solar.calculator.utils.DeferredResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/v1/inverter")
@CrossOrigin("*")
public class InverterController {

    InverterService inverterService;

    @Autowired
    public InverterController(InverterService inverterService){
        this.inverterService=inverterService;
    }

    @PostMapping("/get-inverter")
    public DeferredResult<PageResult<?>> getInverters(@RequestBody GeneralQueryRequest generalQueryRequest){
        return DeferredResultUtil.executeAsync(()->{
            try {
                return inverterService.getInverter(generalQueryRequest);
            }
            catch (Exception e){
                return new PageResult<Object>(e.getMessage());
            }
        });
    }

    @PostMapping("/add-inverter")
    public DeferredResult<String> addInverter(@RequestBody PostQueryRequest postQueryRequest){
        return DeferredResultUtil.executeAsync(()->{
            try{
                return inverterService.addInverter(postQueryRequest);
            }catch (Exception e){
                return e.getMessage();
            }
        });
    }
}
