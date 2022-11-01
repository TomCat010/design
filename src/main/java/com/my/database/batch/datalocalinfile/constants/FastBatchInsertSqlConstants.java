package com.my.database.batch.datalocalinfile.constants;

public interface FastBatchInsertSqlConstants {
    String VERIFY_ORDER_POOL_HIS_SQL = "LOAD DATA LOCAL INFILE 'sql.csv' IGNORE into TABLE t_verify_order_pool_his (mch_order_no,store_no,total_money,refund_money,pay_time,trade_type, bill_date, create_date,md5String)";
    String ORDER_POOL_SQL = "LOAD DATA LOCAL INFILE 'sql.csv' IGNORE into TABLE t_order_pool (out_trade_no,classdate,oli_station_code,ttc,amount,total_fee, takedate, pay_time,pay_platform,create_date)";

    String MODIFY_AND_DELETE_SQL = "LOAD DATA LOCAL INFILE 'sql.csv' IGNORE into TABLE t_modify_and_delete (table_pk,table_name,data,type,create_date,update_date)";

  }
