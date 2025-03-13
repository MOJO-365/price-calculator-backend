package com.solar.calculator.service;

import com.solar.calculator.config.GlobalDatabase;
import com.solar.calculator.dto.PageResult;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.entity.Panel;
import com.solar.calculator.utils.PanelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PanelService {

    PanelUtil panelUtil;

    @Autowired
    public PanelService(PanelUtil panelUtil) {
        this.panelUtil = panelUtil;
    }

    public PageResult<Panel> getPanel(String company, List<String> columns, Map<String, String> orderByColumns,
                                      int pageNumber, int pageSize,
                                      Map<String, Pair<GlobalDatabase.FilterType, Object>> filters) {
        return panelUtil.getPanel(company, columns, orderByColumns, pageNumber, pageSize, filters);
    }

    public String addPanel(PostQueryRequest postQueryRequest) {
        return panelUtil.addPanel(postQueryRequest.getCompany(), postQueryRequest.getPanel());
    }
}
