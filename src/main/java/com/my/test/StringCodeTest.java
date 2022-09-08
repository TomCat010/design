package com.my.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class StringCodeTest {

    @Test
    public void test1(){
        String B = "𝄞𝄞𝄞𝄞𝄞"; // 这个就是那个音符字符，只不过由于当前的网页没支持这种编码，所以没显示。
        String C = "\uD834\uDD1E";// 这个就是音符字符的UTF-16编码
        System.out.println(C);
        System.out.println(B.length());
        System.out.println(B.codePointCount(0,B.length()));
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        String A = "￥";
        String B = "𝄞"; // 这个就是那个音符字符，只不过由于当前的网页没支持这种编码，所以没显示。
        String  C= "}";
        String  D= "]";
        String  E= "中";
        String  F= "A";
         byte[] bytesA = A.getBytes("UTF-8");
        byte[] bytesB = B.getBytes("UTF-8");
        byte[] bytesC = C.getBytes("UTF-8");
        byte[] bytesD = D.getBytes("UTF-8");

        byte[] bytesE = E.getBytes("UTF-8");
        byte[] bytesF = F.getBytes("UTF-8");


        System.out.println(A.getBytes("UTF-8").length);
        System.out.println(A.length());
        System.out.println(B.length());
     }
}
