package com.my.basics.inherit;

public abstract class Pay {

    protected PayMode payMode;

    public Pay(PayMode payMode) {
        this.payMode = payMode;
    }

    protected abstract String platform();

    public final void pay(){
        System.out.println("使用:" + platform() + "的" + payMode.mode());
    }

}
