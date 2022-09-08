package com.my.design.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StrategyFactory implements ApplicationContextAware {
    // Map的Key改为广告类型枚举类
    private final Map<AdvertisingTypeEnum, AdvertisingDataReported> STRATEGY_MAP = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AdvertisingDataReported> tempMap = applicationContext.getBeansOfType(AdvertisingDataReported.class);
        tempMap.values().forEach(strategyService -> STRATEGY_MAP.put(strategyService.advertisingType(), strategyService));
        System.out.println(STRATEGY_MAP);
    }

    public <T extends AdvertisingDataReported> AdvertisingDataReported getInstance(AdvertisingTypeEnum advertisingTypeEnum) {
        return STRATEGY_MAP.get(advertisingTypeEnum);
    }

}
