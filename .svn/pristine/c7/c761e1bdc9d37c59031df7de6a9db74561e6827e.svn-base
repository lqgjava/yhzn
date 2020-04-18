package com.yhzn.quartz;

import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yhzn.common.util.zncb.operateDB;
import com.yhzn.common.util.zncb.runZncb;

public class QuartzZlcb {

	private static final Logger logger = LoggerFactory.getLogger(QuartzZlcb.class);
	
	public void execute() throws Exception{
		long ms = System.currentTimeMillis();
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>定时任务zlcb："+new Date(ms));
		//测试全量
		 /*
		  * 1.数据库上去执行函数 估计时间12小时 然后定个时间启动算法
		  */
		  operateDB.flag=0;
          runZncb.cbrk("");

		
		//增量另
		/*
		 * 先去掉函数 跑完数据增量入库  根据返回值 去调用算法
		 */
	}
	
}
