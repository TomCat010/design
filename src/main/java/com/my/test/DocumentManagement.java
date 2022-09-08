package com.my.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DocumentManagement {
    // 判断文件是否存在
    public static boolean isFileExist(File file) {

        return file.exists();
    }

    public static File checkExist(String filepath) throws Exception{

        File file=new File(filepath);



        if (file.exists()) {//判断文件目录的存在

            System.out.println("文件夹存在！");

            if(file.isDirectory()){//判断文件的存在性

                System.out.println("文件存在！");

            }else{

//                file.createNewFile();//创建文件

                System.out.println("文件不存在，创建文件成功！"   );

            }

        }else {

            System.out.println("文件夹不存在！");

            File file2=new File(file.getParent());

            file2.mkdirs();

            System.out.println("创建文件夹成功！");

            if(file.isDirectory()){

                System.out.println("文件存在！");

            }else{
//                file.createNewFile();//创建文件
                System.out.println("文件不存在，创建文件成功！");
            }

        }
        return file;
    }




//=======================================================================================


    public static void main(String[] args) {
       // getSplitFile(new File("C:\\Users\\admin\\Desktop\\r_couns_customer.sql"),1024*1024);

        List<String> strings = readLastLines(new File("C:\\Users\\admin\\Desktop\\r_couns_customer_2.tmp"), 1);

        System.out.println(strings);

    }
    /**
     * 文件分割方法
     * @param targetFile 分割的文件
     * @param cutSize 分割文件的大小
     * @return int 文件切割的个数
     */
    public static int getSplitFile(File targetFile ,long cutSize ) {

        //计算切割文件大小
        int count = targetFile.length() % cutSize == 0 ? (int) (targetFile.length() / cutSize) :
                (int) (targetFile.length() / cutSize + 1);

        RandomAccessFile raf = null;
        try {
            //获取目标文件 预分配文件所占的空间 在磁盘中创建一个指定大小的文件   r 是只读
            raf = new RandomAccessFile(targetFile, "r");
            long length = raf.length();//文件的总长度
            long maxSize = length / count;//文件切片后的长度
            long offSet = 0L;//初始化偏移量

            for (int i = 0; i < count - 1; i++) { //最后一片单独处理
                long begin = offSet;
                long end = (i + 1) * maxSize;
                offSet = getWrite(targetFile.getAbsolutePath(), i, begin, end);
            }
            if (length - offSet > 0) {
                getWrite(targetFile.getAbsolutePath(), count-1, offSet, length);
            }

        } catch (FileNotFoundException e) {
//            System.out.println("没有找到文件");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    /**
     * 指定文件每一份的边界，写入不同文件中
     * @param file 源文件地址
     * @param index 源文件的顺序标识
     * @param begin 开始指针的位置
     * @param end 结束指针的位置
     * @return long
     */
    public static long getWrite(String file,int index,long begin,long end ){

        long endPointer = 0L;

        String a=file.split(suffixName(new File(file)))[0];

        try {
            //申明文件切割后的文件磁盘
            RandomAccessFile in = new RandomAccessFile(new File(file), "r");
            //定义一个可读，可写的文件并且后缀名为.tmp的二进制文件
            //读取切片文件
            File mFile = new File(a + "_" + index + ".tmp");
            //如果存在
            if (!isFileExist(mFile)) {
                RandomAccessFile out = new RandomAccessFile(mFile, "rw");
                //申明具体每一文件的字节数组
                byte[] b = new byte[1024];
                int n = 0;
                //从指定位置读取文件字节流
                in.seek(begin);
                //判断文件流读取的边界
                while ((n = in.read(b)) != -1 && in.getFilePointer() <= end) {
                    //从指定每一份文件的范围，写入不同的文件
                    out.write(b, 0, n);
                }

                //定义当前读取文件的指针
                endPointer = in.getFilePointer();
                //关闭输入流
                in.close();
                //关闭输出流
                out.close();
            }else {
                //不存在

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return endPointer - 1024;
    }
    /**
     * 文件合并
     * @param fileName 指定合并文件
     * @param targetFile 分割前的文件
     * @param cutSize 分割文件的大小
     */
    public static void merge(String fileName,File targetFile ,long cutSize) {


        int tempCount  = targetFile.length() % cutSize == 0 ? (int) (targetFile.length() / cutSize) :
                (int) (targetFile.length() / cutSize + 1);
        //文件名
        String a=targetFile.getAbsolutePath().split(suffixName(targetFile))[0];

        RandomAccessFile raf = null;
        try {
            //申明随机读取文件RandomAccessFile
            raf = new RandomAccessFile(new File(fileName+suffixName(targetFile)), "rw");
            //开始合并文件，对应切片的二进制文件
            for (int i = 0; i < tempCount; i++) {
                //读取切片文件
                File mFile = new File(a + "_" + i + ".tmp");
                //
                RandomAccessFile reader = new RandomAccessFile(mFile, "r");
                byte[] b = new byte[1024];
                int n = 0;
                //先读后写
                while ((n = reader.read(b)) != -1) {//读
                    raf.write(b, 0, n);//写
                }
                //合并后删除文件
                isDeleteFile(mFile);
                //日志
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 获取文件后缀名 例如：.mp4 /.jpg /.apk
     * @param file 指定文件
     * @return String 文件后缀名
     */
    public static String suffixName (File file){
        String fileName=file.getName();
        String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length());
        return fileTyle;
    }
    /**
     * 删除文件
     * @param file 指定文件
     * @return boolean
     */
    public static boolean isDeleteFile(File file){
        return file.delete();
    }

    /**
     * 读出文件的最后n行
     *
     * @param file 文件
     * @param num  第几行
     * @return 读取文本文件的最后n行
     */
    public static List<String> readLastLines(File file, int num) {
        if (num == 0) {
            return null;
        }
        // 判断该文件是否存在，可读
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }

        List<String> result = new ArrayList<String>();
        // 行数
        int count = 0;
        RandomAccessFile rafFile = null;
        try {
            // 选择只读模式
            rafFile = new RandomAccessFile(file, "r");
            // 读取文件长度
            long length = 1;
            // 判断长度
            if (length == 0L) {
                return null;
            } else {
                // 因为是倒数，所以从最大值开始读起
                long pos = length - 1;
                // 当下一个大于0，则代表文章有内容
                while (pos > 0) {
                    // 开始读取
                    rafFile.seek(pos);
                    // 如果读取到\n代表是读取到一行
                    if (rafFile.readByte() == '\n') {
                        // 使用readLine获取当前行 ,转码，如果存在乱码可以转码
                        String line =
                                new String(rafFile.readLine().getBytes("ISO-8859-1"), "utf-8");
                        // 保存结果
                        result.add(line);
                        // 行数统计，如果到达了指定的行数，就跳出循环
                        count++;
                        if (count == num) {
                            break;
                        }
                    }
                    pos--;
                }
                //next为0，代表长度为1
                if (pos == 0) {
                    rafFile.seek(0);
                    result.add(rafFile.readLine());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rafFile != null) {
                try {
                    rafFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
 }
