package com.basefrm.dao.Impl;
import com.basefrm.dao.IBaseDao;
import com.basefrm.entity.BaseYh;
import com.basefrm.util.PageBean;
import com.basefrm.util.Utils;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.Serializable;
import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("baseDao")
@Transactional(rollbackFor = Exception.class)
public class BaseDaoImpl<T extends Serializable,ID extends Serializable> extends HibernateDaoSupport implements IBaseDao<T, ID> {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	public void InitSession(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFactory);
	}

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}
	public BaseDaoImpl(){
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	@Transactional(readOnly = true)
	public PageBean getByExample(String sql, PageBean pages) {
		Query  query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if(pages!=null){
			int totalRows=this.getByExampleRowsNum(sql);
			pages.setTotalCount(totalRows);
			query.setFirstResult(pages.getBeginNumber());
			query.setMaxResults(pages.getPageSize());
		}else{
			pages=new PageBean();
		}
		
		List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		pages.setRows(list);
		return pages;
	}
	@Transactional(readOnly = true)
	public int getByExampleRowsNum(String sql) {
		try {
			sql="select count(*) "+sql.substring(sql.indexOf("from"));
			Query  query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			
			return ((Number) query.uniqueResult()).intValue();
		}  catch (Exception e) {
			return 0;
		}
	}
	@Transactional(readOnly = true)
	public Object loadById(Object obj,Serializable id) {
		try {
			obj=this.getHibernateTemplate().get(obj.getClass(),id);
			return obj;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		 
		
	}
	@Transactional
	public boolean saveModel(T t) throws Exception{
			this.getHibernateTemplate().save(t);
			return true;
	}
	//批量保存（Excel导入）
	public Map plSaveModel(List<T> tModelist){
		Map map = new HashMap();
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		//ImportSchedule is = new ImportSchedule(tModelist.size());
		int i = 0;

		try {
			for (T tModel : tModelist) {
				if (session.contains(tModel)) {
					continue;
				} else {
					//实体不问空时保存
					if(!Utils.ObjectIsEmpty(tModel)){
						session.saveOrUpdate(tModel);
					}else{
						continue;
					}
				}
				i += 1;
				if (i % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
//		        return map;
		} catch (Exception e) {
			tx.rollback();
			map.put("flag", 0);
			if(Utils.isNotEmpty(e.getCause().getMessage())){
				map.put("message", e.getCause().getMessage());
			}else{
				map.put("message", e.getMessage());
			}
//	         return map;
		}finally{
			return map;
		}
	}
	//批量保存（Excel导入）
	public Map plSaveModel2(List<T> tModelist,HttpSession se){
		Map semap = new HashMap();
		BaseYh yh = (BaseYh) se.getAttribute("carryYh");
		Map map = new HashMap();
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		//ImportSchedule is = new ImportSchedule(tModelist.size());
		int i = 0;
		int cgjls=0;
		int sbjls=0;
		try {
			long startTime = System.currentTimeMillis();
//			ImportSchedule is = new ImportSchedule();
//			new Thread(is).start();
			System.out.println("开始时间："+startTime);
			for (T tModel : tModelist) {
				if (session.contains(tModel)) {
					continue;
				} else {
					//实体不问空时保存
					if(!Utils.ObjectIsEmpty(tModel)){
						session.saveOrUpdate(tModel);
					}else{
						continue;
					}
				}
				i += 1;
				cgjls++;
				if (i % 20 == 0) {
					session.flush();
					session.clear();
				}
				if(i % 20 == 0 ){ //更新进度条(每十条更新一次session)
					int  ycls = cgjls+sbjls;
					int  zs = tModelist.size();
//					float fs = (ycls/zs)*100;
//					int   scale  =   1;//设置位数
//					int   roundingMode  =  4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
//					BigDecimal   bd  =   new BigDecimal((double)fs);
//					bd   =  bd.setScale(scale,roundingMode);
//					fs   =  bd.floatValue();
					DecimalFormat df = new DecimalFormat("0.00");
					String s = df.format((float)ycls/zs);
					//int bfs = Integer.valueOf(s);
					// se.setAttribute(yh.getYhbh()+"exp",bfs);
					int intsf = (ycls*100)/zs;
//					is.setTotalData(String.valueOf(zs));
//					is.setSumProgress(String.valueOf(intsf));
					semap.put("jd",intsf);
					semap.put("mx",zs+"/"+ycls);
					se.setAttribute("exp",semap);
					//se.setAttribute(yh.getYhbh()+"exp",is);
					System.out.println("进度："+intsf +"%"+" <<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>> "+"明细："+zs+"/"+ycls);
					//System.out.println(new Date()+"  HttpSession值《《《》》》"+se.getAttribute(yh.getYhbh()+"exp"));
				}else if( i == tModelist.size()){
					int  ycls = cgjls+sbjls;
					int  zs = tModelist.size();
					semap.put("jd",100);
					semap.put("mx",zs+"/"+ycls);
					se.setAttribute("exp",semap);
					//System.out.println("进度："+100 +"%"+" <<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>> "+"明细："+zs+"/"+ycls);

				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
			tx.commit();
			map.put("flag", 1);
			map.put("message", "成功");
//		        return map;
		} catch (Exception e) {
			sbjls++;
			tx.rollback();
			map.put("flag", 0);
			if(Utils.isNotEmpty(e.getCause().getMessage())){
				map.put("message", e.getCause().getMessage());
			}else{
				map.put("message", e.getMessage());
			}
//	         return map;
		}finally{
			return map;
		}
	}

	@Transactional
	public boolean saveOrUpdateModel(T t) {
		try {
			this.getHibernateTemplate().saveOrUpdate(t);
			return true;
		} catch (Exception e) {
		   return false;
		}
	}
	@Transactional
	public boolean updateModel(T t) throws Exception{
//		try {
			this.getHibernateTemplate().update(t);
			return true;
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			return false;
//		}
	}
	@Transactional
	public boolean deleteAll(List<T> list) throws Exception{
//		try {
			this.getHibernateTemplate().deleteAll(list);
			return true;
//		} catch (DataAccessException e) {
//			return false;
//		}
	}
	@Transactional
	public boolean delete(T t) throws Exception{
//		try {
			this.getHibernateTemplate().delete(t);
			return true;
//		} catch (DataAccessException e) {
//			return false;
//		}
	}
//    Transformers.ALIAS_TO_ENTITY_MAP  把输出结果转换成map  
//    Transformers.TO_LIST  把结果按顺序排进List  
//    Transformers.aliasToBean(target)  把结果通过setter方法注入到指定的对像属性中
    @Transactional
	public Object loadBySql(String sql,String type) {
		Query  query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if(list!=null && list.size()>0){
			if("map".equals(type)){
				return list.get(0);
			}else{
				return list;
			}
		}
		return null;
	}
	
	


	public Object loadByObjId(Object obj, Object fhobj) {
		obj=this.getHibernateTemplate().get(obj.getClass(), (Serializable) fhobj);
		return obj;
	}




	public boolean updateBySql(String sql) throws Exception{
		try {
			Query  query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			int num=query.executeUpdate();
			if(num>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}




}
