package com.my.test;

import org.junit.Test;

import java.io.*;

public class RandomAssessFileTest {

    @Test
    public void test1() throws FileNotFoundException {
        File file = new File("");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws");

    }

    public static void main(String[] args) throws Exception {
        // 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件
        RandomAccessFile raf = new RandomAccessFile("D://abc.txt", "rw");
        raf.setLength(1024*1024); // 预分配 1M 的文件空间
        raf.close();

        // 所要写入的文件内容
        String s1 = "第一个字符串";
        //byte[] bytes = s1.getBytes("UTF-8");
        String s2 = "第二个字符串";
        String s3 = "第三个字符串";
        String s4 = "第四个字符串";
        String s5 = "第五个字符串";

        // 利用多线程同时写入一个文件
        int a = s1.getBytes().length;
        new FileWriteThread(0,s1.getBytes()).start(); // 从文件的1024字节之后开始写入数据
        new FileWriteThread(a,s2.getBytes()).start(); // 从文件的2048字节之后开始写入数据
        new FileWriteThread(2*a,s3.getBytes()).start(); // 从文件的3072字节之后开始写入数据
        new FileWriteThread(3*a,s4.getBytes()).start(); // 从文件的4096字节之后开始写入数据
        new FileWriteThread(4*a,s5.getBytes()).start(); // 从文件的5120字节之后开始写入数据
    }

    // 利用线程在文件的指定位置写入指定数据
    static class FileWriteThread extends Thread{
        private int skip;
        private byte[] content;

        public FileWriteThread(int skip,byte[] content){
            this.skip = skip;
            this.content = content;
        }

        @Override
        public void run(){
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile("D://abc.txt", "rw");
                raf.seek(skip);
                raf.write(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    raf.close();
                    raf.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        String a = "名";
        System.out.println("UTF-8编码长度:"+a.getBytes("UTF-8").length);
        System.out.println("GBK编码长度:"+a.getBytes("GBK").length);
        System.out.println("GB2312编码长度:"+a.getBytes("GB2312").length);
        System.out.println("==========================================");

        String c = "0x20001";
        System.out.println("UTF-8编码长度:"+c.getBytes("UTF-8").length);
        System.out.println("GBK编码长度:"+c.getBytes("GBK").length);
        System.out.println("GB2312编码长度:"+c.getBytes("GB2312").length);
        System.out.println("==========================================");

        char[] arr = Character.toChars(0x20001);
        String s = new String(arr);
        System.out.println("char array length:" + arr.length);
        System.out.println("content:|  " + s + " |");
        System.out.println("String length:" + s.length());
        System.out.println("UTF-8编码长度:"+s.getBytes("UTF-8").length);
        System.out.println("GBK编码长度:"+s.getBytes("GBK").length);
        System.out.println("GB2312编码长度:"+s.getBytes("GB2312").length);
        System.out.println("==========================================");
    }
}
