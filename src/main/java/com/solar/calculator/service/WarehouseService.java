package com.solar.calculator.service;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.entity.Warehouse;
import com.solar.calculator.utils.WarehouseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    private WarehouseUtil warehouseUtil;

    @Autowired
    public WarehouseService(WarehouseUtil warehouseUtil) {
        this.warehouseUtil = warehouseUtil;
    }

    public PageResult<Warehouse> getWarehouse(GeneralQueryRequest generalQueryRequest) {
        return warehouseUtil.getWarehouse(generalQueryRequest.getCompany(), generalQueryRequest.getFilter(),
                generalQueryRequest.getOrderByColumns(), generalQueryRequest.getPageNumber(),
                generalQueryRequest.getPageSize(), generalQueryRequest.getColumns());
    }

    public String insertWarehouse(PostQueryRequest postQueryRequest) {
        return warehouseUtil.insertWarehouse(postQueryRequest.getCompany(), postQueryRequest.getWarehouse());
    }
}
