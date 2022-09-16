package com.my.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class StringTest {

    public static void main(String[] args) {
        /*for (int i = 0; i <200 ; i++) {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

            String time = sdf.format(new Date());
            System.out.println("AP"+time);

      }*/
        String a = "123456789ax";
        String b = a.substring(a.length()-2,11);
        System.out.println(b);
        a =a.substring(0,a.length()-4)+a.substring(a.length()-2,11);
        //String b = a.substring(4,a.length()-2);
        System.out.println(a);
        String hamburger = "hamburger".substring(4, 9);
        System.out.println(hamburger);

    }
@Test
    public void test9(){
        Integer a = null;
        System.out.println(a+"");
    }

    public void test1(){
        String[] str = new String[] { "chen", "yang", "hao" };
        List list = Arrays.asList(str);
        System.out.println(list);
    }


    public void test2(){
        for (int i = 0; i <10 ; i++) {
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            //list.add("3");
            //list.add("4");
           /* Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (item.equals("1")) {
                    iterator.remove();
                }
            }*/

        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
            System.out.println(list);
       }
     }


    public void test3(){
        // 开始时间
        long stime = System.currentTimeMillis();
        // 执行时间（1s）
        String d = "";
        for (int i = 0; i <100000 ; i++) {
            d += "Stringwww"+i;
         }
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("执行时长：%d 毫秒.", (etime - stime));
        //System.out.println(d);
     }

    public void test4(){
        // 开始时间
        long stime = System.currentTimeMillis();
        // 执行时间（1s）
         StringBuilder builder = new StringBuilder();
        for (int i = 0; i <1000000 ; i++) {
            builder.append("Stringwww");
         }
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("执行时长：%d 毫秒.", (etime - stime));
        //System.out.println(builder);


    }

    public void test5(){
        // 开始时间
        long stime = System.currentTimeMillis();
        // 执行时间（1s）
        StringJoiner joiner = new StringJoiner("");
        for (int i = 0; i <1000000 ; i++) {
            joiner.add("Stringwww");
        }
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("执行时长：%d 毫秒.", (etime - stime));
        //System.out.println(builder);


    }

   // @Test
    public void test6() {

        long l1 = System.currentTimeMillis();
        for (int i=0; i<10000000; i++) {
            String s1 = "a" + "b" + "c";
        }
        System.out.println(System.currentTimeMillis() - l1);    //结果： 2

        long l2 = System.currentTimeMillis();
        for (int i=0; i<1000000; i++) {
            String s2 = new StringBuilder().append("a").append("b").append("c").toString();
        }
        System.out.println(System.currentTimeMillis() - l2);    //结果 ： 19

     }

}
