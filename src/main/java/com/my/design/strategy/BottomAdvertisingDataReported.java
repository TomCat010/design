package com.my.design.strategy;

import org.springframework.stereotype.Service;

@Service
public class BottomAdvertisingDataReported implements AdvertisingDataReported {
    @Override
    public AdvertisingTypeEnum advertisingType() {
        return AdvertisingTypeEnum.BOTTOM;
    }

    @Override
    public String advertisingDataReported(Object param) {
        return "heloo";
    }
}
