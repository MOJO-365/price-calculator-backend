package com.solar.calculator.service;

import com.solar.calculator.dto.ExtraPrice;
import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.entity.Price;
import com.solar.calculator.utils.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceService {

    private PriceUtil priceUtil;

    @Autowired
    public PriceService(PriceUtil priceUtil) {
        this.priceUtil = priceUtil;
    }

    public List<ExtraPrice> getPrice(String company, String state) {
        List<Price> unfilteredPrice = priceUtil.getPrice(company);
        List<ExtraPrice> extraPrices = new ArrayList<>();
        unfilteredPrice.stream().forEach(price -> {
            ExtraPrice extraPrice = new ExtraPrice();
            extraPrice.setSubId(price.getSubId());
            extraPrice.setName(price.getName());
            HashMap<String, Price.UnitPrice> options = new HashMap<>();
            price.getStatePricing().forEach((option, statesPricing) -> {
                options.put(option, filterState(statesPricing, state));
            });
            extraPrice.setOption(options);
            extraPrices.add(extraPrice);
        });
        return extraPrices;
    }

    private Price.UnitPrice filterState(Price.StatePricing statePricing, String state) {

        // Set only the requested state field
        switch (state.toLowerCase()) {
            case "nsw":
                return statePricing.getNsw();
            case "qld":
                return statePricing.getQld();
            case "act":
                return statePricing.getAct();
            case "vic":
                return statePricing.getVic();
            case "wa":
                return statePricing.getWa();
            case "sa":
                return statePricing.getSa();
            case "tas":
                return statePricing.getTas();
            case "nt":
                return statePricing.getNt();
            case "jbt":
                return statePricing.getJbt();
            default:
                return null; // No match, return null
        }
    }


    public String insertPrice(PostQueryRequest postQueryRequest) {
        return priceUtil.insertPrice(postQueryRequest.getCompany(), postQueryRequest.getPrice());
    }
}
