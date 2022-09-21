package com.my.database.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCBatchDemo1 {
    /**
     * 大数据量的批处理 使用jdbc预编译批处理 并开启事务
     */
    public void testOne(){
        try {
            Connection conn = null;
            // MYSQL驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 情况1：没有使用批处理 ==> 每次50000条 耗时: 33秒
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "root");

            //情况2：使用批处理 ==> rewriteBatchedStatements=true  ==> 每次50000条  耗时: 2秒
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01?rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "root");

            //批量插入50000
            int batchSize = 50000;
            // 总条数5000000
            int count = 500000;
            //设置自动提交为false
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into user (id) values (?)");
            Long t1 = System.currentTimeMillis();
            System.out.println("========开始运行=========");
            for (long i = 1; i < count; i++) {
                //设置第一个参数的值为i
                ps.setLong(1, i);
                //将该条记录添加到批处理中
                ps.addBatch();
                if (i % batchSize == 0) {
                    //执行批处理
                    ps.executeBatch();
                    //提交
                    conn.commit();
                    System.out.println(i + ":添加" + batchSize + "条");
                }
            }
            if ((count + 1) % batchSize != 0) {
                ps.executeBatch();
                conn.commit();
            }
            ps.close();
            Long t2 = System.currentTimeMillis();
            System.out.println("总条数:"+count + "条  每次插入" + batchSize + "条   " +"  每次耗时:"+ (t2 - t1) / 1000 + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 }
