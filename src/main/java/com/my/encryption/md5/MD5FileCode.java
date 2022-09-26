package com.my.encryption.md5;

import org.springframework.util.DigestUtils;

import java.io.*;

/**
 * 文件MD5 码生成
 */
public class MD5FileCode {

    public static void main(String[] args) throws IOException {
        String dir = "D:\\work\\c.txt";
        File file = new File(dir);
        if(file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try(FileOutputStream out = new FileOutputStream(dir,true);
            FileInputStream in = new FileInputStream(dir);
            Writer w = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"))
        ){
            String code = DigestUtils.md5DigestAsHex(in);
            w.write("\r\n");
            w.write(code);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
