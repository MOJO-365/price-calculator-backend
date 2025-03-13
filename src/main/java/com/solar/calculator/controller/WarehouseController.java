package com.solar.calculator.controller;

import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.service.WarehouseService;
import com.solar.calculator.utils.DeferredResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/v1/warehouse")
@CrossOrigin("*")
public class WarehouseController {

    private WarehouseService warehouseService;

    @Autowired
    WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/get-warehouse-details")
    public DeferredResult<PageResult<?>> getWarehouseDetails(@RequestParam String company) {
        return DeferredResultUtil.executeAsync(() -> {
            try {
                return warehouseService.getWarehouse(company);
            } catch (Exception e) {
                return new PageResult<>(e.getMessage());
            }

        });
    }

    @PostMapping("/add-warehouse-details")
    public DeferredResult<String> addWarehouseDetails(@RequestBody PostQueryRequest postQueryRequest) {
        return DeferredResultUtil.executeAsync(() -> {
            return warehouseService.insertWarehouse(postQueryRequest);
        });
    }
}
