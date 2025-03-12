package com.solar.calculator.service;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.entity.Battery;
import com.solar.calculator.utils.BatteryUtil;
import com.solar.calculator.utils.PanelUtil;
import org.springframework.stereotype.Service;

@Service
public class BatteryService {

    private BatteryUtil batteryUtil;

    public BatteryService(BatteryUtil batteryUtil){
        this.batteryUtil=batteryUtil;
    }

    public PageResult<Battery> getBattery(GeneralQueryRequest generalQueryRequest){
        return batteryUtil.getBattery(generalQueryRequest.getCompany(),generalQueryRequest.getPageNumber(),
                generalQueryRequest.getPageSize(),generalQueryRequest.getColumns(),generalQueryRequest.getFilter(),
                generalQueryRequest.getOrderByColumns());
    }

    public  String addBattery(PostQueryRequest postQueryRequest){
        return batteryUtil.addBattery(postQueryRequest.getCompany(),postQueryRequest.getBattery());
    }
}
