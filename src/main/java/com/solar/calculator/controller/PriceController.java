package com.solar.calculator.controller;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.entity.Price;
import com.solar.calculator.service.PriceService;
import com.solar.calculator.utils.DeferredResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    private PriceService priceService;

    @Autowired
    public PriceController (PriceService priceService){
        this.priceService =priceService;
    }

    @PostMapping ("/get-price")
    public DeferredResult<List<Price>> getPrice(@RequestParam String company){
        return DeferredResultUtil.executeAsync(()->{
            return priceService.getPrice(company);
        });
    }

    @PostMapping("/add-price")
    public DeferredResult<String> addPrice(@RequestBody PostQueryRequest postQueryRequest){
        return DeferredResultUtil.executeAsync(()->{
            return priceService.insertPrice(postQueryRequest);
        });
    }

}
