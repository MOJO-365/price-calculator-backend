package com.solar.calculator.controller;

import com.solar.calculator.dto.GeneralQueryRequest;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.service.PanelService;
import com.solar.calculator.utils.DeferredResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/v1/panel")
@CrossOrigin("*")
public class PanelController {

    PanelService panelService;

    @Autowired
    public PanelController(PanelService panelService) {
        this.panelService = panelService;
    }

    @PostMapping("/get-panel")
    public DeferredResult<PageResult<?>> getPanel(@RequestBody GeneralQueryRequest generalQueryRequest) {
        return DeferredResultUtil.executeAsync(() -> {
            try {
                return panelService.getPanel(generalQueryRequest.getCompany(), generalQueryRequest.getColumns(),
                        generalQueryRequest.getOrderByColumns(), generalQueryRequest.getPageNumber(),
                        generalQueryRequest.getPageSize(), generalQueryRequest.getFilter());
            } catch (Exception e) {
                return new PageResult<>(e.getMessage());
            }
        });
    }

    @PostMapping("/add-panel")
    public DeferredResult<String> addPanel(@RequestBody PostQueryRequest postQueryRequest) {
        return DeferredResultUtil.executeAsync(() -> {
            try {
                return panelService.addPanel(postQueryRequest);
            } catch (Exception e) {
                return e.getMessage();
            }
        });
    }
}