package com.my.design.strategy;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AdvertisingDataReportedController {
    @Resource
    private StrategyFactory strategyFactory;

    @RequestMapping(value = "/reported/data", method = RequestMethod.POST)
    public String reportedData(AdvertisingTypeEnum advertisingTypeEnum, Object obj) {

        AdvertisingDataReported dataReported = strategyFactory.getInstance(advertisingTypeEnum);

        String result = dataReported.advertisingDataReported(obj);

        return result;
    }
}
