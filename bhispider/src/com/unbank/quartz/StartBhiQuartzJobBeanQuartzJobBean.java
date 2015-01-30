package com.unbank.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.quartz.task.BhiSpider;
import com.unbank.quartz.task.SpiderConsole;

public class StartBhiQuartzJobBeanQuartzJobBean {
	private static Log logger = LogFactory
			.getLog(StartBhiQuartzJobBeanQuartzJobBean.class);

	public void executeInternal() {
		try {
			logger.info("重新启动定时任务");

			new BhiSpider().BHIConsole();

		} catch (Exception e) {
			logger.info("检测内容节点出错", e);
		}
	}

}
