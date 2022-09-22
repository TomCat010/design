package com.my.io;

import org.junit.Test;

import java.io.*;

public class IODemo1 {
    @Test
    public void ioClose1() {
        int i = 0;
        while (true){
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(new File("D:\\myx_zhixiao\\资料\\CRM\\脚本\\hblife_crm.sql"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                System.out.println(++i);
                //多资源 多个try catch  关闭资源
               /* try {
                    close(fileOutputStream);
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        }
     }

    public void close(Closeable... closeables) throws IOException {
        if(closeables != null){
            Closeable[] var1 = closeables;
            int var2 = closeables.length;
            for (int var3 = 0; var3 < var2; ++var3){
                Closeable closeable = var1[var3];
                if(closeable != null){
                    closeable.close();
                }
            }
        }
    }
}
