package com.solar.calculator.service;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.entity.Price;
import com.solar.calculator.utils.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@Service
public class PriceService {

    private PriceUtil priceUtil;

    @Autowired
    public PriceService (PriceUtil priceUtil){
        this.priceUtil=priceUtil;
    }

    public List<Price> getPrice(String company){
        return priceUtil.getPrice(company);
    }

    public  String insertPrice(PostQueryRequest postQueryRequest){
        return  priceUtil.insertPrice(postQueryRequest.getCompany(),postQueryRequest.getPrice());
    }
}
