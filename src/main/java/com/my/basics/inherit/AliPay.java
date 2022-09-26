package com.my.basics.inherit;

public class AliPay extends Pay{


    public AliPay(PayMode payMode) {
        super(payMode);
    }

    @Override
    protected String platform() {
        return "aliPay";
    }
}
