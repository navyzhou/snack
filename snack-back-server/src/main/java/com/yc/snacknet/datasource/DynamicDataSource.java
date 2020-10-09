package com.yc.snacknet.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.yc.snacknet.enums.DataSourceTypeEnum;

/**
 * 使用DatabaseContextHolder获取当前线程的DatabaseType
 * 动态数据源,需要继承AbstractRoutingDataSource
 * @author navy
 * @company 源辰信息
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
	// 使用ThreadLocal保证线程安全
	private static final ThreadLocal<DataSourceTypeEnum> DATASOURCETYPE = new ThreadLocal<DataSourceTypeEnum>();

	// 往当前线程里设置数据源类型
	public static void setDatabaseType(DataSourceTypeEnum type){
		if (type == null) {
			throw new NullPointerException();
		}
		DATASOURCETYPE.set(type);
	}

	// 获取数据源类型
	public static DataSourceTypeEnum getDataBaseType() {
		DataSourceTypeEnum dataBaseType = DATASOURCETYPE.get() == null ? DataSourceTypeEnum.USER : DATASOURCETYPE.get();
		log.error("获取当前数据源的类型为： {}", dataBaseType);
		return dataBaseType;
	}

	// 清空数据类型
	public static void clearDataBaseType() {
		DATASOURCETYPE.remove();
	}

	protected Object determineCurrentLookupKey() {  // determine: 决定、限定  CurrentLookupKey : 当前查找关键字
		return DATASOURCETYPE.get();
	}

	// 重置数据源
	public static void resetDataSourceType(){
		DATASOURCETYPE.set(DataSourceTypeEnum.USER);
	}
}
