package com.my.container.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class demo1 {


    @Test
    public void test1(){
        List<String> strings = Arrays.asList("a,b,c,d".split(","));
        int i = strings.indexOf("");
        int a = strings.indexOf("a");
        if(strings.indexOf("")!=-1){

        }
    }
}
