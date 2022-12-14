package com.my.io;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

import java.io.*;

public class IoTest1 {

    @Test
    public void ioClose1() {
        FileOutputStream fileOutputStream = null;
        try {
             fileOutputStream = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\a.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            //多资源 多个try catch  关闭资源
            try {
                close(fileOutputStream);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //@Test
    public void ioClose2() {
        // try with resource 不支持io资源重新赋值
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\a.txt"));){
            fileOutputStream.write("&& 中文 abc *&^%$#@!".getBytes());
            //fileOutputStream = null;
        }  catch (IOException e) {

            e.printStackTrace();
        }
    }
    //ioClose2 反编译后的代码
    public void ioClose3() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\a.txt"));
            Throwable throwable = null;
            if (fileOutputStream != null)
                if (throwable != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                } else {
                    fileOutputStream.close();
                }
        } catch (IOException e) {
            e.printStackTrace();
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
