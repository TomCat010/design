package com.my.exception;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;


public class Demo1 {

    @Test
    public void test1(){
        try {
            System.out.println("23");
            System.out.println("23");
            int a = 1/0;
            System.out.println("23");

        }catch (Exception e){

             //e.printStackTrace();
            //throw e;
        }
    }
    public void test2() throws FileNotFoundException {
        int i=0;

        int y=2;

        try{

            int z=y/i;

        } catch (Exception e) {


        }
        FileOutputStream fos = new FileOutputStream("fos.txt");


    }


}
