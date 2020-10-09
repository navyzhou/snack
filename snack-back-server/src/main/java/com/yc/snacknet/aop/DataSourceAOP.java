package com.yc.snacknet.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.yc.snacknet.datasource.DynamicDataSource;
import com.yc.snacknet.enums.DataSourceTypeEnum;

@Aspect
@Component
public class DataSourceAOP {
	@Before("execution(* com.yc.snacknet.service.impl.AdminInfoServiceImpl.*(..)) or execution(* com.yc.snacknet.service.impl.MemberInfoServiceImpl.*(..))")
	public void setDataSourceUser() {
		DynamicDataSource.setDatabaseType(DataSourceTypeEnum.USER);
	}
	
	@Before("execution(* com.yc.snacknet.service.impl.Goods*.*(..))")
	public void setDataSourceProduct() {
		DynamicDataSource.setDatabaseType(DataSourceTypeEnum.PRODUCT);
	}
	
	@Before("execution(* com.yc.snacknet.service.impl.Order*.*(..))")
	public void setDataSourceOrder() {
		DynamicDataSource.setDatabaseType(DataSourceTypeEnum.ORDER);
	}
}
