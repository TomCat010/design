package com.my.test;

import org.junit.Test;

import java.io.*;

public class FileTest {
    @Test
    public void test1() throws FileNotFoundException {
        File file = new File("C:\\Users\\admin\\Desktop\\b2.txt");
        boolean delete = file.delete();
       // file.deleteOnExit();
        System.out.println(file.toString()+delete);
       // FileOutputStream fos = new FileOutputStream(file);
     }
    @Test
    public void test2() {
        File file = new File("C:\\Users\\admin\\Desktop\\a.txt");
        System.out.println(file);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write("nihaoya".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @Test
        public void test3() throws IOException {
            File file = new File("C:\\Users\\admin\\Desktop\\a.txt");
            file.createNewFile();
            System.out.println(file);
            try (FileOutputStream fos = new FileOutputStream(file,true)){
                //fos.write("nihaoya34 \n".getBytes());
                fos.write(new byte[]{(byte)0xEF,(byte)0xBB,(byte)0xBF});
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @Test
    public void test4() throws IOException {
        File file = new File("C:\\Users\\admin\\Desktop\\a.txt");
        file.createNewFile();
        System.out.println(file);
        try (FileOutputStream fos = new FileOutputStream(file,true);
            Writer w = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"),1024*8)
        ){
            fos.write("1".getBytes());
            w.append("2");
            w.append("2");
            boolean delete = file.delete();
            System.out.println(delete);
            w.append("4555");
            w.append("4555");
            w.append("4555");
            w.append("4555");
            w.append("4555");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        File file = new File("C:\\Users\\admin\\Desktop\\a.txt");
        System.out.println(file);
        try (FileOutputStream fos = new FileOutputStream(file,true)) {
            fos.write("123456".getBytes());
            file.delete();
            fos.write("34567".getBytes());
            fos.write("wwwww".getBytes());
            fos.close();
            fos.close();
            fos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();

    }
    @Test
    public void test6() throws IOException {
        File file = new File("C:\\Users\\admin\\Desktop\\a.txt");
        System.out.println(file);
        FileOutputStream fos = new FileOutputStream(file,true);
            fos.write("123456".getBytes());
            file.delete();
            fos.write("34567".getBytes());
            fos.write("wwwww".getBytes());
            fos.close();
            fos.close();
            fos.close();
            fos.close();

        //file.delete();

    }
    @Test
    public void fileTitle(){
        System.out.println("0xEF");
    }
}
