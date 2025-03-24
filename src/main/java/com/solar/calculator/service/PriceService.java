package com.solar.calculator.service;

import com.solar.calculator.dto.ExtraPrice;
import com.solar.calculator.dto.PostQueryRequest;
import com.solar.calculator.entity.Price;
import com.solar.calculator.utils.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
        Map<String, Price> priceMap = unfilteredPrice.stream()
                .collect(Collectors.toMap(Price::getSubId, Function.identity()));
        List<ExtraPrice> extraPrices = new ArrayList<>();
        String ignoreCase=state.toLowerCase();
        if (state.equalsIgnoreCase("NSW")) {
            extraPrices= getNSWPrice(priceMap);
        } else if (state.equalsIgnoreCase("VIC")) {
            extraPrices= getVICPrice(priceMap);
        } else if (state.equalsIgnoreCase("QLD")) {
            extraPrices= getQLDPrice(priceMap);
        } else if (state.equalsIgnoreCase("WA")) {
            extraPrices= getWAPrice(priceMap);
        } else if (state.equalsIgnoreCase("SA")) {
            extraPrices= getSAPrice(priceMap);
        } else if (state.equalsIgnoreCase("TAS")) {
            extraPrices= getTASPrice(priceMap);
        } else if (state.equalsIgnoreCase("ACT")) {
            extraPrices= getACTPrice(priceMap);
        } else if (state.equalsIgnoreCase("NT")) {
            extraPrices= getNTPrice(priceMap);
        }
        return extraPrices;
    }

    private List<ExtraPrice> getNTPrice(Map<String, Price> priceMap) {
        List<ExtraPrice> extraPrices = new ArrayList<>();
        List<Price.UnitPrice> priceList = new ArrayList<>(2);
        // Cost For Storey
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getNt().getKey(),
                priceMap.get("double_storey_installer").getNt().getValue(),priceMap.get("double_storey_installer").getName(),priceMap.get("double_storey_installer").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_roof_access", priceList));
        priceList = new ArrayList<>(2);
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getNt().getKey(),
                priceMap.get("double_storey_installer").getNt().getValue(),priceMap.get("double_storey_installer").getName()
                ,priceMap.get("double_storey_installer").getMessage()));
        priceList.add(new Price.UnitPrice(priceMap.get("scissor_lift").getNt().getKey(),
                priceMap.get("scissor_lift").getNt().getValue(),priceMap.get("scissor_lift").getName(),
                priceMap.get("scissor_lift").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_without_roof_access", priceList));
        extraPrices.add(new ExtraPrice("multi_storey", priceList));

        // Roof Cost
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tin_clip_lock").getNt().getKey(),
                priceMap.get("tin_clip_lock").getNt().getValue(),priceMap.get("tin_clip_lock").getName(),
                priceMap.get("tin_clip_lock").getMessage()));
        extraPrices.add(new ExtraPrice("tin", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tile_terracotta_roof").getNt().getKey(),
                priceMap.get("tile_terracotta_roof").getNt().getValue(),priceMap.get("tile_terracotta_roof").getName(),
                priceMap.get("tile_terracotta_roof").getMessage()));
        extraPrices.add(new ExtraPrice("terracotta", priceList));
        extraPrices.add(new ExtraPrice("tile", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tilt_frame").getNt().getKey(),
                priceMap.get("tilt_frame").getNt().getValue(),priceMap.get("tilt_frame").getName(),
                priceMap.get("tilt_frame").getMessage()));
        extraPrices.add(new ExtraPrice("0_15", priceList));

        //Roof Angle
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("stiff_roof_angle").getNt().getKey(),
                priceMap.get("stiff_roof_angle").getNt().getValue(),priceMap.get("stiff_roof_angle").getName()
                ,priceMap.get("stiff_roof_angle").getMessage()));
        extraPrices.add(new ExtraPrice("45_60", priceList));

        // Shading Info
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("optimiser").getNt().getKey(),
                priceMap.get("optimiser").getNt().getValue(),priceMap.get("optimiser").getName(),
                priceMap.get("optimiser").getMessage()));
        extraPrices.add(new ExtraPrice("shading_info_yes", priceList));

        //Replace Solar
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("replace_solar").getNt().getKey(),priceMap.get("replace_solar").getNt().getValue(),
                priceMap.get("replace_solar").getName(),priceMap.get("replace_solar").getMessage()));
        extraPrices.add(new ExtraPrice("replace_solar", priceList));

        return extraPrices;
    }

    private List<ExtraPrice> getACTPrice(Map<String, Price> priceMap) {
        List<ExtraPrice> extraPrices = new ArrayList<>();
        List<Price.UnitPrice> priceList = new ArrayList<>(2);
        // Cost For Storey
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getAct().getKey(),
                priceMap.get("double_storey_installer").getAct().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_roof_access", priceList));
        priceList = new ArrayList<>(2);
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getAct().getKey(),
                priceMap.get("double_storey_installer").getAct().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        priceList.add(new Price.UnitPrice(priceMap.get("scissor_lift").getAct().getKey(),
                priceMap.get("scissor_lift").getAct().getValue(),priceMap.get("scissor_lift").getName(),
                priceMap.get("scissor_lift").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_without_roof_access", priceList));
        extraPrices.add(new ExtraPrice("multi_storey", priceList));

        // Roof Cost
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tin_clip_lock").getAct().getKey(),
                priceMap.get("tin_clip_lock").getAct().getValue(),priceMap.get("tin_clip_lock").getName(),priceMap.get("tin_clip_lock").getMessage()));
        extraPrices.add(new ExtraPrice("tin", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tile_terracotta_roof").getAct().getKey(),
                priceMap.get("tile_terracotta_roof").getAct().getValue(),priceMap.get("tile_terracotta_roof").getName(),
                priceMap.get("tile_terracotta_roof").getMessage()));
        extraPrices.add(new ExtraPrice("terracotta", priceList));
        extraPrices.add(new ExtraPrice("tile", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tilt_frame").getAct().getKey(),
                priceMap.get("tilt_frame").getAct().getValue(),priceMap.get("tilt_frame").getName(),
                priceMap.get("tilt_frame").getMessage()));
        extraPrices.add(new ExtraPrice("0_15", priceList));

        //Roof Angle
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("stiff_roof_angle").getAct().getKey(),
                priceMap.get("stiff_roof_angle").getAct().getValue(),priceMap.get("stiff_roof_angle").getName(),
                priceMap.get("stiff_roof_angle").getMessage()));
        extraPrices.add(new ExtraPrice("45_60", priceList));

        // Shading Info
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("optimiser").getAct().getKey(),
                priceMap.get("optimiser").getAct().getValue(),priceMap.get("optimiser").getName(),
                priceMap.get("optimiser").getMessage()));
        extraPrices.add(new ExtraPrice("shading_info_yes", priceList));

        //Replace Solar
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("replace_solar").getAct().getKey(),priceMap.get("replace_solar").getAct().getValue(),
                priceMap.get("replace_solar").getName(),priceMap.get("replace_solar").getMessage()));
        extraPrices.add(new ExtraPrice("replace_solar", priceList));

        return extraPrices;
    }

    private List<ExtraPrice> getTASPrice(Map<String, Price> priceMap) {
        List<ExtraPrice> extraPrices = new ArrayList<>();
        List<Price.UnitPrice> priceList = new ArrayList<>(2);
        // Cost For Storey
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getTas().getKey(),
                priceMap.get("double_storey_installer").getTas().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_roof_access", priceList));
        priceList = new ArrayList<>(2);
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getTas().getKey(),
                priceMap.get("double_storey_installer").getTas().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        priceList.add(new Price.UnitPrice(priceMap.get("scissor_lift").getTas().getKey(),
                priceMap.get("scissor_lift").getTas().getValue(),priceMap.get("scissor_lift").getName(),
                priceMap.get("scissor_lift").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_without_roof_access", priceList));
        extraPrices.add(new ExtraPrice("multi_storey", priceList));

        // Roof Cost
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tin_clip_lock").getTas().getKey(),
                priceMap.get("tin_clip_lock").getTas().getValue(),priceMap.get("tin_clip_lock").getName(),
                priceMap.get("tin_clip_lock").getMessage()));
        extraPrices.add(new ExtraPrice("tin", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tile_terracotta_roof").getTas().getKey(),
                priceMap.get("tile_terracotta_roof").getTas().getValue(),priceMap.get("tile_terracotta_roof").getName(),
                priceMap.get("tile_terracotta_roof").getMessage()));
        extraPrices.add(new ExtraPrice("terracotta", priceList));
        extraPrices.add(new ExtraPrice("tile", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tilt_frame").getTas().getKey(),
                priceMap.get("tilt_frame").getTas().getValue(),priceMap.get("tilt_frame").getName(),
                priceMap.get("tilt_frame").getMessage()));
        extraPrices.add(new ExtraPrice("0_15", priceList));

        //Roof Angle
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("stiff_roof_angle").getTas().getKey(),
                priceMap.get("stiff_roof_angle").getTas().getValue(),priceMap.get("stiff_roof_angle").getName(),
                priceMap.get("stiff_roof_angle").getMessage()));
        extraPrices.add(new ExtraPrice("45_60", priceList));

        // Shading Info
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("optimiser").getTas().getKey(),
                priceMap.get("optimiser").getTas().getValue(),priceMap.get("optimiser").getName(),
                priceMap.get("optimiser").getMessage()));
        extraPrices.add(new ExtraPrice("shading_info_yes", priceList));

        //Replace Solar
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("replace_solar").getTas().getKey(),priceMap.get("replace_solar").getTas().getValue(),
                priceMap.get("replace_solar").getName(),priceMap.get("replace_solar").getMessage()));
        extraPrices.add(new ExtraPrice("replace_solar", priceList));

        return extraPrices;
    }

    private List<ExtraPrice> getSAPrice(Map<String, Price> priceMap) {
        List<ExtraPrice> extraPrices = new ArrayList<>();
        List<Price.UnitPrice> priceList = new ArrayList<>(2);
        // Cost For Storey
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getSa().getKey(),
                priceMap.get("double_storey_installer").getSa().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_roof_access", priceList));
        priceList = new ArrayList<>(2);
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getSa().getKey(),
                priceMap.get("double_storey_installer").getSa().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        priceList.add(new Price.UnitPrice(priceMap.get("scissor_lift").getSa().getKey(),
                priceMap.get("scissor_lift").getSa().getValue(),priceMap.get("scissor_lift").getName(),
                priceMap.get("scissor_lift").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_without_roof_access", priceList));
        extraPrices.add(new ExtraPrice("multi_storey", priceList));

        // Roof Cost
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tin_clip_lock").getSa().getKey(),
                priceMap.get("tin_clip_lock").getSa().getValue(),priceMap.get("tin_clip_lock").getName(),
                priceMap.get("tin_clip_lock").getMessage()));
        extraPrices.add(new ExtraPrice("tin", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tile_terracotta_roof").getSa().getKey(),
                priceMap.get("tile_terracotta_roof").getSa().getValue(),priceMap.get("tile_terracotta_roof").getName(),
                priceMap.get("tile_terracotta_roof").getMessage()));
        extraPrices.add(new ExtraPrice("terracotta", priceList));
        extraPrices.add(new ExtraPrice("tile", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tilt_frame").getSa().getKey(),
                priceMap.get("tilt_frame").getSa().getValue(),priceMap.get("tilt_frame").getName(),priceMap.get("tilt_frame").getMessage()));
        extraPrices.add(new ExtraPrice("0_15", priceList));

        //Roof Angle
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("stiff_roof_angle").getSa().getKey(),
                priceMap.get("stiff_roof_angle").getSa().getValue(),priceMap.get("stiff_roof_angle").getName(),
                priceMap.get("stiff_roof_angle").getMessage()));
        extraPrices.add(new ExtraPrice("45_60", priceList));

        // Shading Info
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("optimiser").getSa().getKey(),
                priceMap.get("optimiser").getSa().getValue(),priceMap.get("optimiser").getName(),priceMap.get("optimiser").getMessage()));
        extraPrices.add(new ExtraPrice("shading_info_yes", priceList));

        //Replace Solar
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("replace_solar").getSa().getKey(),priceMap.get("replace_solar").getSa().getValue(),
                priceMap.get("replace_solar").getName(),priceMap.get("replace_solar").getMessage()));
        extraPrices.add(new ExtraPrice("replace_solar", priceList));

        return extraPrices;
    }

    private List<ExtraPrice> getWAPrice(Map<String, Price> priceMap) {
        List<ExtraPrice> extraPrices = new ArrayList<>();
        List<Price.UnitPrice> priceList = new ArrayList<>(2);
        // Cost For Storey
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getWa().getKey(),
                priceMap.get("double_storey_installer").getWa().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_roof_access", priceList));
        priceList = new ArrayList<>(2);
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getWa().getKey(),
                priceMap.get("double_storey_installer").getWa().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        priceList.add(new Price.UnitPrice(priceMap.get("scissor_lift").getWa().getKey(),
                priceMap.get("scissor_lift").getWa().getValue(),priceMap.get("scissor_lift").getName(),
                priceMap.get("scissor_lift").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_without_roof_access", priceList));
        extraPrices.add(new ExtraPrice("multi_storey", priceList));

        // Roof Cost
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tin_clip_lock").getWa().getKey(),
                priceMap.get("tin_clip_lock").getWa().getValue(),priceMap.get("tin_clip_lock").getName(),
                priceMap.get("tin_clip_lock").getMessage()));
        extraPrices.add(new ExtraPrice("tin", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tile_terracotta_roof").getWa().getKey(),
                priceMap.get("tile_terracotta_roof").getWa().getValue(),priceMap.get("tile_terracotta_roof").getName(),
                priceMap.get("tile_terracotta_roof").getMessage()));
        extraPrices.add(new ExtraPrice("terracotta", priceList));
        extraPrices.add(new ExtraPrice("tile", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tilt_frame").getWa().getKey(),
                priceMap.get("tilt_frame").getWa().getValue(),priceMap.get("tilt_frame").getName(),
                priceMap.get("tilt_frame").getMessage()));
        extraPrices.add(new ExtraPrice("flat", priceList));

        //Roof Angle
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("stiff_roof_angle").getWa().getKey(),
                priceMap.get("stiff_roof_angle").getWa().getValue(),priceMap.get("stiff_roof_angle").getName(),
                priceMap.get("stiff_roof_angle").getMessage()));
        extraPrices.add(new ExtraPrice("45_60", priceList));

        // Shading Info
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("optimiser").getWa().getKey(),
                priceMap.get("optimiser").getWa().getValue(),priceMap.get("optimiser").getName(),priceMap.get("optimiser").getMessage()));
        extraPrices.add(new ExtraPrice("shading_info_yes", priceList));

        //Replace Solar
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("replace_solar").getWa().getKey(),priceMap.get("replace_solar").getWa().getValue(),
                priceMap.get("replace_solar").getName(),priceMap.get("replace_solar").getMessage()));
        extraPrices.add(new ExtraPrice("replace_solar", priceList));

        return extraPrices;
    }

    private List<ExtraPrice> getQLDPrice(Map<String, Price> priceMap) {
        List<ExtraPrice> extraPrices = new ArrayList<>();
        List<Price.UnitPrice> priceList = new ArrayList<>();
        // Cost For Storey
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getQld().getKey(),
                priceMap.get("double_storey_installer").getQld().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_roof_access", priceList));
        priceList = new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getQld().getKey(),
                priceMap.get("double_storey_installer").getQld().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        priceList.add(new Price.UnitPrice(priceMap.get("scissor_lift").getQld().getKey(),
                priceMap.get("scissor_lift").getQld().getValue(),priceMap.get("scissor_lift").getName(),
                priceMap.get("scissor_lift").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_without_roof_access", priceList));
        extraPrices.add(new ExtraPrice("multi_storey", priceList));

        // Roof Cost
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tin_clip_lock").getQld().getKey(),
                priceMap.get("tin_clip_lock").getQld().getValue(),priceMap.get("tin_clip_lock").getName(),
                priceMap.get("tin_clip_lock").getMessage()));
        extraPrices.add(new ExtraPrice("tin", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tile_terracotta_roof").getQld().getKey(),
                priceMap.get("tile_terracotta_roof").getQld().getValue(),priceMap.get("tile_terracotta_roof").getName(),
                priceMap.get("tile_terracotta_roof").getMessage()));
        extraPrices.add(new ExtraPrice("terracotta", priceList));
        extraPrices.add(new ExtraPrice("tile", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tilt_frame").getQld().getKey(),
                priceMap.get("tilt_frame").getQld().getValue(),priceMap.get("tilt_frame").getName(),
                priceMap.get("tilt_frame").getMessage()));
        extraPrices.add(new ExtraPrice("0_15", priceList));

        //Roof Angle
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("stiff_roof_angle").getQld().getKey(),
                priceMap.get("stiff_roof_angle").getQld().getValue(),priceMap.get("stiff_roof_angle").getName(),
                priceMap.get("stiff_roof_angle").getMessage()));
        extraPrices.add(new ExtraPrice("45_60", priceList));

        // Shading Info
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("optimiser").getQld().getKey(),
                priceMap.get("optimiser").getQld().getValue(),priceMap.get("optimiser").getName(),
                priceMap.get("optimiser").getMessage()));
        extraPrices.add(new ExtraPrice("shading_info_yes", priceList));

        //Replace Solar
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("replace_solar").getQld().getKey(),priceMap.get("replace_solar").getQld().getValue(),
                priceMap.get("replace_solar").getName(),priceMap.get("replace_solar").getMessage()));
        extraPrices.add(new ExtraPrice("replace_solar", priceList));

        return extraPrices;

    }

    private List<ExtraPrice> getVICPrice(Map<String, Price> priceMap) {
        List<ExtraPrice> extraPrices = new ArrayList<>();
        List<Price.UnitPrice> priceList = new ArrayList<>(2);
        // Cost For Storey
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getVic().getKey(),
                priceMap.get("double_storey_installer").getVic().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_roof_access", priceList));
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getVic().getKey(),
                priceMap.get("double_storey_installer").getVic().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        priceList.add(new Price.UnitPrice(priceMap.get("scissor_lift").getVic().getKey(),
                priceMap.get("scissor_lift").getVic().getValue(),priceMap.get("scissor_lift").getName(),
                priceMap.get("scissor_lift").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_without_roof_access", priceList));
        extraPrices.add(new ExtraPrice("multi_storey", priceList));

        // Roof Cost
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tin_clip_lock").getVic().getKey(),
                priceMap.get("tin_clip_lock").getVic().getValue(),priceMap.get("tin_clip_lock").getName(),
                priceMap.get("tin_clip_lock").getMessage()));
        extraPrices.add(new ExtraPrice("tin", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tile_terracotta_roof").getVic().getKey(),
                priceMap.get("tile_terracotta_roof").getVic().getValue(),priceMap.get("tile_terracotta_roof").getName(),
                priceMap.get("tile_terracotta_roof").getMessage()));
        extraPrices.add(new ExtraPrice("terracotta", priceList));
        extraPrices.add(new ExtraPrice("tile", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tilt_frame").getVic().getKey(),
                priceMap.get("tilt_frame").getVic().getValue(),priceMap.get("tilt_frame").getName(),
                priceMap.get("tilt_frame").getMessage()));
        extraPrices.add(new ExtraPrice("0_15", priceList));

        //Roof Angle
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("stiff_roof_angle").getVic().getKey(),
                priceMap.get("stiff_roof_angle").getVic().getValue(),priceMap.get("stiff_roof_angle").getName(),
                priceMap.get("stiff_roof_angle").getMessage()));
        extraPrices.add(new ExtraPrice("45_60", priceList));

        // Shading Info
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("optimiser").getVic().getKey(),
                priceMap.get("optimiser").getVic().getValue(),priceMap.get("optimiser").getName(),
                priceMap.get("optimiser").getMessage()));
        extraPrices.add(new ExtraPrice("shading_info_yes", priceList));

        //Replace Solar
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("replace_solar").getVic().getKey(),priceMap.get("replace_solar").getVic().getValue(),
                priceMap.get("replace_solar").getName(),priceMap.get("replace_solar").getMessage()));
        extraPrices.add(new ExtraPrice("replace_solar", priceList));

        return extraPrices;

    }

    private List<ExtraPrice> getNSWPrice(Map<String, Price> priceMap){
        List<ExtraPrice> extraPrices = new ArrayList<>();
        List<Price.UnitPrice> priceList = new ArrayList<>();
        // Cost For Storey
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getNsw().getKey(),
                priceMap.get("double_storey_installer").getNsw().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_roof_access", priceList));
        priceList = new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("double_storey_installer").getNsw().getKey(),
                priceMap.get("double_storey_installer").getNsw().getValue(),priceMap.get("double_storey_installer").getName(),
                priceMap.get("double_storey_installer").getMessage()));
        priceList.add(new Price.UnitPrice(priceMap.get("scissor_lift").getNsw().getKey(),
                priceMap.get("scissor_lift").getNsw().getValue(),priceMap.get("scissor_lift").getName(),
                priceMap.get("scissor_lift").getMessage()));
        extraPrices.add(new ExtraPrice("double_storey_without_roof_access", priceList));
        extraPrices.add(new ExtraPrice("multi_storey", priceList));

        // Roof Cost
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tin_clip_lock").getNsw().getKey(),
                priceMap.get("tin_clip_lock").getNsw().getValue(),priceMap.get("tin_clip_lock").getName(),
                priceMap.get("tin_clip_lock").getMessage()));
        extraPrices.add(new ExtraPrice("tin", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tile_terracotta_roof").getNsw().getKey(),
                priceMap.get("tile_terracotta_roof").getNsw().getValue(),priceMap.get("tile_terracotta_roof").getName(),
                priceMap.get("tile_terracotta_roof").getMessage()));
        extraPrices.add(new ExtraPrice("terracotta", priceList));
        extraPrices.add(new ExtraPrice("tile", priceList));

        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("tilt_frame").getNsw().getKey(),
                priceMap.get("tilt_frame").getNsw().getValue(),priceMap.get("tilt_frame").getName(),
                priceMap.get("tilt_frame").getMessage()));
        extraPrices.add(new ExtraPrice("0_15", priceList));

        //Roof Angle
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("stiff_roof_angle").getNsw().getKey(),
                priceMap.get("stiff_roof_angle").getNsw().getValue(),priceMap.get("stiff_roof_angle").getName(),
                priceMap.get("stiff_roof_angle").getMessage()));
        extraPrices.add(new ExtraPrice("45_60", priceList));

        // Shading Info
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("optimiser").getNsw().getKey(),
                priceMap.get("optimiser").getNsw().getValue(),priceMap.get("optimiser").getName(),
                priceMap.get("optimiser").getMessage()));
        extraPrices.add(new ExtraPrice("shading_info_yes", priceList));

        //Replace Solar
        priceList=new ArrayList<>();
        priceList.add(new Price.UnitPrice(priceMap.get("replace_solar").getNsw().getKey(),priceMap.get("replace_solar").getNsw().getValue(),
                priceMap.get("replace_solar").getName(),priceMap.get("replace_solar").getMessage()));
        extraPrices.add(new ExtraPrice("replace_solar", priceList));

        return extraPrices;

    }


    public String insertPrice(PostQueryRequest postQueryRequest) {
        return priceUtil.insertPrice(postQueryRequest.getCompany(), postQueryRequest.getPrice());
    }
}
