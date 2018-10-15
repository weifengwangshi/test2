package com.basefrm.dao;

import com.basefrm.util.PageBean;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 操作数据库公用方法类：基本的增删改查等功能
 */
public interface IBaseDao<T extends Serializable,ID extends Serializable> {
	

	/**
	 * 保存实体对象到数据库
	 * @param t：要保存的对象，调用时传入具体的对象
	 * @return 成功返回true,失败返回false
	 * @throws Exception 
	 */
	public boolean saveModel(T t) throws Exception;
	/**
	 * 保存或修改实体对象到数据库
	 * @param t：要保存的对象，调用时传入具体的对象
	 * @return 成功返回true,失败返回false
	 * @throws Exception 
	 */
	public boolean saveOrUpdateModel(T t);
	/**
	 * 批量删除
	 * @param list：需要删除的对象集合(每个对象的主键属性不能为空)
	 * @return 成功返回 true,失败返回false
	 * @throws Exception 
	 */
	public boolean deleteAll(List<T> list) throws Exception;
	
	public boolean delete(T t) throws Exception;
	/**
	 * 通过主键加载实体对象
	 * @param obj：需要加载的实体对象
	 * @param id：实体对象的主键，对应数据库对应表的主键
	 * @return 返回实际的实体对象
	 */
	public Object loadById(Object obj, Serializable id);
	/**
	 * 根据复合主键加载实体对象
	 * @param obj：实际加载的实体对象
	 * @param fhobj：复合主键实体对象
	 */
	public Object loadByObjId(Object obj, Object fhobj);
	/**
	 * 功能：通过实体对象修改数据库信息
	 *@param t:需要修改的实体对象(泛型);修改成功返回true,失败返回false
	 * @throws Exception 
	 */
	public boolean updateModel(T t) throws Exception;
	/**
	 * @param sql:需要查询的sql语句
	 * @param pages:分页参数对象(封装了当前页，每页多少条记录等信息)
	 * @return PageBean:将查询结果封装到分页对象中
	 */
	public PageBean getByExample(String sql, PageBean pages);
	/**
	 * 功能：获取总记录数
	 * @param sql:需要统计的sql;返回实际记录数
	 */
	public int getByExampleRowsNum(String sql);
	/**
	 * 根据sql查询
	 * @param sql：要执行的sql
	 * @param type:返回类型区分 ,当type值为“map”时，返回一个Map对象，type值为null或者“”时，返回List<Map>对象
	 */
	public Object loadBySql(String sql, String type);
	public boolean updateBySql(String sql) throws Exception;
}
