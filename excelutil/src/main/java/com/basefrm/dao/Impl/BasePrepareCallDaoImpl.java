package com.basefrm.dao.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;


import com.basefrm.dao.BasePrepareCallDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository("bpcallDao")
public class BasePrepareCallDaoImpl  extends HibernateDaoSupport implements BasePrepareCallDao  {
	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	/**
	 * 用户排序方法调用
	 * @param packname 过程名称 包+方法名
	 * @param ypx     原排序
	 * @param xpx     现排序
	 * @param bmbm	  部门编码
	 * @return
	 */


	@Transactional(readOnly = true)
	public String prepareCall(String packname, String ypx, String xpx, String bmbm) throws SQLException{
		String  result="";
		SessionFactory sessionFacotry=getSessionFactory();
//		Session session=sessionFacotry.getCurrentSession();
//		Transaction tran=session.beginTransaction();
//		tran.begin();
		DataSource datasource= SessionFactoryUtils.getDataSource(sessionFacotry);
		// 取得数据库链接
		Connection conn = null;
		CallableStatement call=null;
		try {
			conn=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			call = conn.prepareCall("{Call "+packname+"(?,?,?,?)}");
			call.setString(1,ypx);
			call.setString(2,xpx);
			call.setString(3,bmbm);
			call.registerOutParameter(4, Types.VARCHAR);
			call.execute();
			result = call.getString(4);
			//tran.commit();
			// 关闭连接
			call.close();
			conn.close();
		} catch (Exception e) {
			// 关闭连接
			call.close();
			conn.close();
			//tran.rollback();
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}


	@Transactional(readOnly = true)
	public String preparesCall(String packname, String param) throws SQLException{
		String  result="";
		SessionFactory sessionFacotry=getSessionFactory();
		DataSource datasource= SessionFactoryUtils.getDataSource(sessionFacotry);
		// 取得数据库链接
		Connection conn = null;
		CallableStatement call=null;
		try {
			conn=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			String[] par = param.split(",");
			StringBuffer csgs = new StringBuffer("");
			for(int i = 0; i<par.length+1;i++ ){
				csgs.append("?");
				if( i != par.length ){
					csgs.append(",");
				}
			}
			call = conn.prepareCall("{Call "+packname+"("+csgs.toString()+")}");
            int j = 0;
			for(int i = 0; i<par.length;i++ ){
				call.setString(i+1,par[i]);
				j = i+1;
			}
			call.registerOutParameter(j+1, Types.VARCHAR);
			call.execute();
			result = call.getString(j+1);
			call.close();
			conn.close();
		} catch (Exception e) {
			// 关闭连接
			call.close();
			conn.close();
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}

	public String prepareCalls(String packname, String... params) throws SQLException {
		String  result="";
		SessionFactory sessionFacotry=getSessionFactory();
		DataSource datasource= SessionFactoryUtils.getDataSource(sessionFacotry);
		// 取得数据库链接
		Connection conn = null;
		CallableStatement call=null;
		try {
			conn=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			String callParams = "";
			for(int i=0;i<=params.length;i++){
				if(i==params.length){
					callParams += "?";
				}else {
					callParams += "?,";
				}
			}
			String callStr = "{Call "+packname+"("+callParams+")}";
			call = conn.prepareCall(callStr);
			for(int j=0;j<params.length;j++){
				call.setString(j+1,params[j]);
			}
			call.registerOutParameter((params.length+1), Types.VARCHAR);
			call.execute();
			result = call.getString((params.length+1));
			//tran.commit();
			// 关闭连接
			call.close();
			conn.close();
		} catch (Exception e) {
			// 关闭连接
			call.close();
			conn.close();
			//tran.rollback();
			e.printStackTrace();
		}
		return result;
	}

	@Transactional(readOnly = true)
	public String prepareCall(String packname, String ypx) throws SQLException{
		String  result="";
		SessionFactory sessionFacotry=getSessionFactory();
//		Session session=sessionFacotry.getCurrentSession();
//		Transaction tran=session.beginTransaction();
//		tran.begin();
		DataSource datasource= SessionFactoryUtils.getDataSource(sessionFacotry);
		// 取得数据库链接
		Connection conn = null;
		CallableStatement call=null;
		try {
			conn=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			call = conn.prepareCall("{Call "+packname+"(?,?)}");
			call.setString(1,ypx);
			call.registerOutParameter(2, Types.VARCHAR);
			call.execute();
			result = call.getString(2);
			//tran.commit();
			// 关闭连接
			call.close();
			conn.close();
		} catch (Exception e) {
			// 关闭连接
			call.close();
			conn.close();
			//tran.rollback();
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}
//	@Transactional(readOnly = true)
//	public String prepareCall(String packname, String ypx, String xpx, String bmbm) {
//		String  result="";
//		try {
//			SessionFactory sessionFacotry=getSessionFactory();
//			DataSource datasource= SessionFactoryUtils.getDataSource(sessionFacotry);
//			CallableStatement call = datasource.getConnection().prepareCall("{Call "+packname+"(?,?,?,?)}");
//			call.setString(1,ypx);
//			call.setString(2,xpx);
//			call.setString(3,bmbm);
//			call.registerOutParameter(4, Types.VARCHAR);
//			call.execute();
//			result = call.getString(4);
//		} catch (Exception e) {
//			e.getStackTrace();
//			System.out.println(e.getMessage());
//		}
//		return result;
//	}

}
