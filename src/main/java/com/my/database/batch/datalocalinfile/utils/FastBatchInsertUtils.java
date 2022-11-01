package com.my.database.batch.datalocalinfile.utils;

import com.my.database.batch.datalocalinfile.entity.ModifyAndDelete;
import com.my.database.batch.datalocalinfile.entity.OrderPool;
import com.my.database.batch.datalocalinfile.entity.VerifyOrderPool;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FastBatchInsertUtils {
    public static int bulkLoadFromInputStream(String loadDataSql, InputStream dataStream, Connection conn)
            throws SQLException {
        if (dataStream == null) {
            return 0;
        }
        PreparedStatement statement = conn.prepareStatement(loadDataSql);

        int result = 0;

        if (statement.isWrapperFor(com.mysql.jdbc.Statement.class)) {

            com.mysql.jdbc.PreparedStatement mysqlStatement = statement.unwrap(com.mysql.jdbc.PreparedStatement.class);

            mysqlStatement.setLocalInfileInputStream(dataStream);
            result = mysqlStatement.executeUpdate();
        }
        return result;
    }

    public static InputStream getDataInputStreamForVerifyOrderPool(List<VerifyOrderPool> verifyOrderPoolList) {
        StringBuilder builder = new StringBuilder();
        for (VerifyOrderPool verifyOrderPool : verifyOrderPoolList) {
            StringBuilder md5sb = new StringBuilder();
            /*builder.append(verifyOrderPool.getMchOrderNo());
            md5sb.append(verifyOrderPool.getMchOrderNo());
            builder.append("\t");
            builder.append(verifyOrderPool.getStoreNo());
            md5sb.append(verifyOrderPool.getStoreNo());
            builder.append("\t");
            builder.append(verifyOrderPool.getTotalMoney());
            md5sb.append(verifyOrderPool.getTotalMoney() != null ? verifyOrderPool.getTotalMoney() : 0);
            builder.append("\t");
            builder.append(verifyOrderPool.getRefundMoney());
            md5sb.append(verifyOrderPool.getRefundMoney() != null ? verifyOrderPool.getRefundMoney() : 0);
            builder.append("\t");
            builder.append(DateUtils.formatDate(
                    verifyOrderPool.getPayTime() != null ? new Date() : verifyOrderPool.getPayTime(),
                    "yyyy-MM-dd HH:mm:ss"));
            builder.append("\t");
            builder.append(verifyOrderPool.getTradeType());
            md5sb.append(verifyOrderPool.getTradeType());
            builder.append("\t");
            builder.append(verifyOrderPool.getBillDate());
            md5sb.append(verifyOrderPool.getBillDate());
            builder.append("\t");
            builder.append(DateUtils.formatDate(
                    verifyOrderPool.getCreateDate() == null ? new Date() : verifyOrderPool.getCreateDate(),
                    "yyyy-MM-dd HH:mm:ss"));
            builder.append("\t");

            builder.append(MD5Util.md5s(md5sb.toString())).append("\n");*/

        }
        byte[] bytes = builder.toString().getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        return is;
    }

    public static InputStream getDataInputStreamForOrderPool(List<OrderPool> orderPoolList) {
        StringBuilder builder = new StringBuilder();
        for (OrderPool orderPool : orderPoolList) {
            /*builder.append(orderPool.getOutTradeNo());
            builder.append("\t");
            builder.append(orderPool.getClassdate());
            builder.append("\t");
            builder.append(orderPool.getOliStationCode());
            builder.append("\t");
            builder.append(orderPool.getTtc());
            builder.append("\t");
            builder.append(orderPool.getAmount());
            builder.append("\t");
            builder.append(orderPool.getTotalFee());
            builder.append("\t");
            builder.append(DateUtils.formatDate(orderPool.getTakedate() == null ? new Date() : orderPool.getTakedate(),
                    "yyyy-MM-dd HH:mm:ss"));
            builder.append("\t");
            builder.append(DateUtils.formatDate(orderPool.getPayTime() == null ? new Date() : orderPool.getPayTime(),
                    "yyyy-MM-dd HH:mm:ss"));
            builder.append("\t");
            builder.append(orderPool.getPayPlatform());
            builder.append("\t");
            builder.append(DateUtils.formatDate(
                    orderPool.getCreateDate() == null ? new Date() : orderPool.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));*/
            builder.append("\t");
            builder.append("\n");

        }
        byte[] bytes = builder.toString().getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        return is;
    }

    public static InputStream getDataInputStreamForModifyAndDelete(List<ModifyAndDelete> modifyAndDeleteList) {
        StringBuilder builder = new StringBuilder();
        for (ModifyAndDelete modifyAndDelete : modifyAndDeleteList) {
           /* builder.append(modifyAndDelete.getTablePk());
            builder.append("\t");
            builder.append(modifyAndDelete.getTableName());
            builder.append("\t");
            builder.append(modifyAndDelete.getData());
            builder.append("\t");
            builder.append(modifyAndDelete.getType());
            builder.append("\t");
            builder.append(DateUtils.formatDate(
                    modifyAndDelete.getCreateDate() == null ? new Date() : modifyAndDelete.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
            builder.append("\t");
            builder.append(DateUtils.formatDate(
                    modifyAndDelete.getUpdateDate() == null ? new Date() : modifyAndDelete.getUpdateDate(), "yyyy-MM-dd HH:mm:ss"));
            builder.append("\t");
            builder.append("\n");*/

        }
        byte[] bytes = builder.toString().getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        return is;
    }
}
