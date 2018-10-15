package com.basefrm.dao;


import java.sql.SQLException;

public interface BasePrepareCallDao {
	
	/**
	 * 用户排序方法调用
	 * @param packname 过程名称 包+方法名
	 * @param ypx     原排序
	 * @param xpx     现排序
	 * @param bmbm	  部门编码
	 * @return
	 */
	public String prepareCall(String packname, String ypx, String xpx, String bmbm) throws SQLException;
	public String prepareCalls(String packname, String... params) throws SQLException;
	public String prepareCall(String packname, String ypx) throws SQLException;
}
