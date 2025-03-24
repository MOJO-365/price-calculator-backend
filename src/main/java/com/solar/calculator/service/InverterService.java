package com.solar.calculator.service;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.entity.Inverter;
import com.solar.calculator.utils.InverterUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class InverterService {

    InverterUtil inverterUtil;

    public InverterService(InverterUtil inverterUtil) {
        this.inverterUtil = inverterUtil;
    }

    public PageResult<Inverter> getInverter(GeneralQueryRequest generalQueryRequest) {
        return inverterUtil.getInverter(generalQueryRequest.getCompany(), generalQueryRequest.getPageNumber(),
                generalQueryRequest.getPageSize(), generalQueryRequest.getColumns(), generalQueryRequest.getOrderByColumns(), generalQueryRequest.getFilter());
    }

    public String addInverter(@RequestBody PostQueryRequest postQueryRequest) {
        return inverterUtil.addInverter(postQueryRequest.getCompany(), postQueryRequest.getInverter());
    }
}
