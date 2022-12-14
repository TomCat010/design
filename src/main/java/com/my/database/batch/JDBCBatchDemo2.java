package com.my.database.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCBatchDemo2 {
    private long begin = 33112001;//起始id
    private long end = begin+100000;//每次循环插入的数据量
    private String url = "jdbc:mysql://localhost:3306/bigdata?useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true&amp;characterEncoding=UTF-8";
    private String user = "root";
    private String password = "root";
    @org.junit.Test
    public void insertBigData() throws SQLException {
        //定义连接、statement对象
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            //加载jdbc驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            conn.setAutoCommit(false);
            //编写sql
            String sql = "INSERT INTO person VALUES (?,?,?,?,?,?,?)";
            //预编译sql

            pstm = conn.prepareStatement(sql);
            //流样式查询设置
            pstm.setFetchSize(Integer.MIN_VALUE);

            //开始总计时
            long bTime1 = System.currentTimeMillis();

            //循环10次，每次十万数据，一共1000万
            for(int i=0;i<10;i++) {

                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //赋值
                    pstm.setLong(1, begin);
                    pstm.setString(2, "RandomValue.getChineseName()");
                    pstm.setString(3, "RandomValue.name_sex");
                    pstm.setInt(4, 3);
                    pstm.setString(5, "RandomValue.getEmail(4, 15)");
                    pstm.setString(6, "17877778999");
                    pstm.setString(7, "");
                    //添加到同一个批处理中
                    pstm.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
                //提交事务
                conn.commit();
                //边界值自增10W
                end += 100000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时："+(eTime-bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时："+(eTime1-bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }


 }
