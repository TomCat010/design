package com.my.container.list;

import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void test2(){
        ArrayList<Object> objects = new ArrayList<>();
        //objects = null;
        for (Object o:
        objects) {
          System.out.println("for eatch");
        }


    }
    @Test
    public void test3(){
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i <200 ; i++) {
            objects.add(i);
        }
         //objects = null;
        for (Object o:
                objects) {
            System.out.println(o);
        }
     }
}
