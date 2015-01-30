package com.unbank.tools;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.unbank.paser.entity.WebBean;

public class TableMaker {
	private static Log logger = LogFactory.getLog(TableMaker.class);
	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(TableMaker.class.getClassLoader()
					.getResource("").toURI().getPath()
					+ "log4j.properties");
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

	public static void main(String[] args) {
		List<WebBean> beans = ConfigReader.getWebBeans();
		WebBean webBean = beans.get(0);
		Map<String, String> attr = webBean.getDetailAtrr();
		Set<String> keyset = attr.keySet();
		String tableName = webBean.getTableName();
		StringBuffer createTableSQL = new StringBuffer();
		createTableSQL.append("CREATE TABLE " + tableName + " (");
		createTableSQL.append(" id int(11) NOT NULL AUTO_INCREMENT,");
		for (String string : keyset) {
			if (string.equals("detailurl")) {
				createTableSQL.append(string + " varchar(255) DEFAULT NULL,");
			} else {
				createTableSQL.append(string + " varchar(255) DEFAULT NULL,");
			}

		}
		createTableSQL.append("TIME datetime NOT NULL , ");
		createTableSQL.append(" PRIMARY KEY (id) ,UNIQUE KEY (detailurl)");
		createTableSQL.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		System.out.println(createTableSQL.toString());

	}
}
