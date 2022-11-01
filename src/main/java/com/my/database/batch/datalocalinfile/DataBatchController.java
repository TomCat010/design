package com.my.database.batch.datalocalinfile;

import org.springframework.transaction.annotation.Transactional;

public class DataBatchController {
    /**
     * 目前在项目中发现一张700万的表，插入10万条数据通过Mybatis的批量插入大概需要3分钟左右，耗时太长。
     * 现在通过mysql的LOAD DATA LOCAL INFILE 命令进行优化插入。最后测试10万条数据批量插入大概在3秒左右的时间。
     *
     * todo-----------------------------------------------------------------------------------------------------------------------
     * 在此连接过程中遇到 连接问题
     *
     *  java.sql.SQLSyntaxErrorException: Loading local data is disabled; this must be enabled on both the client and server sides
     * 1
     * 问题处理：
     * 一定要注意的问题 客户端一定要加上 allowLoadLocalInfile=true mysq的服务端要开启
     * 服务端开启任务
     *
     * 查看 local_infile 的设置是否为off（分步执行下面命令；然后重启mysql）一定要重新启动 (若是重启会把该状态修改为off 需要持久化到 my.ini 文件中，我目前没有重启服务)
     * 查看 local_infile 的状态
     *
     * SHOW GLOBAL VARIABLES LIKE 'local_infile';
     * 1
     * 如果是off,使用命令设置为on
     *
     * SET GLOBAL local_infile = 'ON';
     * 1
     * SET GLOBAL local_infile = ‘ON’;
     *
     * SHOW GLOBAL VARIABLES LIKE 'local_infile';
     * 1
     *todo-----------------------------------------------------------------------------------------------------------------------
      * @Transactional
     *     public void newInsertBillData(DownRespVo result) throws Exception{
     * 		VerifyOrderTotal total = result.getTotal();
     * 		List<VerifyOrderPool> verifyOrderPoolList = result.getVerifyOrderPoolList();
     *
     * 		Map<String,List<VerifyOrderPool>> map = verifyOrderPoolList.stream().collect(Collectors.groupingBy(d -> fetchCodeGroupKey(d)));
     * 		Date now = new Date();
     * 		List<VerifyOrderTotalCode> totalCodeList = new ArrayList<VerifyOrderTotalCode>();
     * 		for(Map.Entry<String, List<VerifyOrderPool>> entry : map.entrySet()) {
     * 			String key = entry.getKey();
     * 			String[] props = key.split(":");
     * 			VerifyOrderTotalCode totalCode = new VerifyOrderTotalCode();
     * 			totalCode.setBillDate(props[0]);
     * 			totalCode.setStoreNo(props[1]);
     * 			totalCode.setTradeType(props[2]);
     * 			totalCode.setTradeNum(entry.getValue().size());
     * 			totalCode.setTurnover(sumTotalFee(entry.getValue()));
     * 			totalCode.setCreateDate(now);
     * 			totalCode.setUpdateDate(now);
     * 			totalCodeList.add(totalCode);
     *                }
     *
     * 		DataSourceTransactionManager transactionManager = SpringContextHolder
     * 				.getBeanByClass(DataSourceTransactionManager.class);
     * 		ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager
     * 				.getResource(transactionManager.getDataSource());// 返回事务对象
     * 		// 从spring中获取事务连接，让verifyOrderTotalMapper.insertSelective(total)与下面的LOAD DATA LOCAL INFILE操作是在同一个事务中
     * 		Connection connection = connectionHolder.getConnection();
     *
     * 		// 注意不能用下面的获取Connection的方法，因为此方法获取到的连接是新的连接，并不是线程上下文中的连接
     * 		// 如果用此连接的话那和verifyOrderTotalMapper.insertSelective(total)的连接就不在同一个事务中，那事务管理就不是spring管理
     * 		// 需要自己手动进行事务管理
     * 		// Connection connection= transactionManager.getDataSource().getConnection();
     * 		// connection.setAutoCommit(false);
     * 		try {
     * 			// 统计数据入库
     * 			VerifyOrderTotal totalFromDB = verifyOrderTotalMapper.qryByBillDateLimitOne(total.getBillDate());
     * 			if(totalFromDB != null) {
     * 				totalFromDB.setTradeNum(totalFromDB.getTradeNum() + total.getTradeNum());
     * 				totalFromDB.setTurnover(totalFromDB.getTurnover() + total.getTurnover());
     * 				totalFromDB.setRefundMoney(totalFromDB.getRefundMoney() + total.getRefundMoney());
     * 				totalFromDB.setRedBagsRefundMoney(totalFromDB.getRedBagsRefundMoney() + total.getRedBagsRefundMoney());
     * 				totalFromDB.setPoundage(totalFromDB.getPoundage() + total.getPoundage());
     * 				totalFromDB.setRealityMoney(totalFromDB.getRealityMoney() + total.getRealityMoney());
     * 				verifyOrderTotalMapper.updateByPrimaryKeySelective(totalFromDB);
     *            }else {
     * 				verifyOrderTotalMapper.insertSelective(total);
     *            }
     * 			if(CollectionUtils.isNotEmpty(totalCodeList)) {
     * 				for (VerifyOrderTotalCode verifyOrderTotalCode : totalCodeList) {
     * 					VerifyOrderTotalCode totalCodeFromDB = verifyOrderTotalCodeMapper.findByDateCodeTradeType(verifyOrderTotalCode.getBillDate(),
     * 							verifyOrderTotalCode.getStoreNo(), verifyOrderTotalCode.getTradeType());
     * 					if(totalCodeFromDB != null) {
     * 						totalCodeFromDB.setTradeNum(totalCodeFromDB.getTradeNum() + verifyOrderTotalCode.getTradeNum());
     * 						totalCodeFromDB.setTurnover(totalCodeFromDB.getTurnover() + verifyOrderTotalCode.getTurnover());
     * 						verifyOrderTotalCodeMapper.updateByPrimaryKeySelective(totalCodeFromDB);
     *                    }else {
     * 						verifyOrderTotalCodeMapper.insertSelective(verifyOrderTotalCode);
     *                    }
     *                }
     *            }
     * 			long start = System.currentTimeMillis();
     *
     * 			// 对账池入库
     * 			long min = System.currentTimeMillis();
     * 			if(CollectionUtils.isNotEmpty(verifyOrderPoolList)) {
     * 				InputStream dataInputStream = FastBatchInsertUtils.getDataInputStreamForVerifyOrderPool(verifyOrderPoolList);
     * 				FastBatchInsertUtils.bulkLoadFromInputStream(FastBatchInsertSqlConstants.VERIFY_ORDER_POOL_SQL,dataInputStream,connection);
     * 				dataInputStream = FastBatchInsertUtils.getDataInputStreamForVerifyOrderPool(verifyOrderPoolList);
     * 				FastBatchInsertUtils.bulkLoadFromInputStream(FastBatchInsertSqlConstants.VERIFY_ORDER_POOL_HIS_SQL,dataInputStream,connection);
     *            }
     * 			long end =  System.currentTimeMillis();
     * 			System.out.println("order耗时："+(min-start)+"ms"+" pool耗时："+(end-min)+"ms");
     *
     * 			//connection.commit();
     *        } catch (Exception e) {
     * 			logger.info("对账单下载数据错误，日期：" + total != null ? total.getBillDate() : "", e);
     *
     * 			// connection.rollback();
     * 			throw e;
     *        }
     *
     * ————————————————
     * 版权声明：本文为CSDN博主「伫望-向北」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/zhuwangxiangbie/article/details/104972099
     */

 }
