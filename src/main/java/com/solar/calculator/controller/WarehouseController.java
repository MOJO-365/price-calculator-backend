package com.solar.calculator.controller;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.service.WarehouseService;
import com.solar.calculator.utils.DeferredResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    private WarehouseService warehouseService;

    @Autowired
    WarehouseController(WarehouseService warehouseService){
        this.warehouseService=warehouseService;
    }

    @PostMapping("/get-warehouse-details")
    public DeferredResult<PageResult<?>> getWarehouseDetails(@RequestBody GeneralQueryRequest generalQueryRequest) {
        return DeferredResultUtil.executeAsync(()->{
            try {
                return  warehouseService.getWarehouse(generalQueryRequest);
            }
            catch (Exception e){
                return new PageResult<>(e.getMessage());
            }

        });
    }

    @PostMapping("/add-warehouse-details")
    public DeferredResult<String> addWarehouseDetails(@RequestBody PostQueryRequest postQueryRequest){
        return DeferredResultUtil.executeAsync(()->{
            return warehouseService.insertWarehouse(postQueryRequest);
        });
    }
}
