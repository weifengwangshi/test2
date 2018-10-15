package com.basefrm.converter;

import com.basefrm.dao.IBaseDao;
import com.basefrm.dao.Impl.BaseDaoImpl;
import com.basefrm.dao.Impl.BasePrepareCallDaoImpl;
import com.basefrm.excel.config.FieldTitleValue;
import com.basefrm.excel.config.FieldValue;
import com.basefrm.excel.exception.ExcelException;
import com.basefrm.excel.parsing.CellValueConverter;
import com.basefrm.util.SpringContextUtils;
import com.basefrm.util2.SpringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
public class GenderValueConverter implements CellValueConverter {
//    @Autowired
//    private IBaseDao baseDao;

    @Override
    public Object convert(Object bean, Object value, FieldValue fieldValue, Type type, int rowNum) throws Exception {
        IBaseDao baseDao=(IBaseDao) SpringContextUtils.getBean("baseDao");
        BasePrepareCallDaoImpl  callDao=   (BasePrepareCallDaoImpl) SpringContextUtils.getBean("bpcallDao");
        System.out.println("OtherConfig的值:"+fieldValue.getOtherConfig());
        if(type == Type.IMPORT) {
            if("FUNCTION".equals(fieldValue.getCallType()) && "REPLACE".equals(fieldValue.getProcessType())) {
                try {
                    String functions = fieldValue.getCellValueConfig().replace("?",value.toString());
                    String sql = "select "+functions+"  BZDMS from dual";
                    //String sql = "select f_get_zd_mcTodm（'GB_XB','女')  BZDMS from dual";
                    System.out.println("FUNCTION时调用SQL《《》》: "+sql);
                    System.out.println("value值《《》》: "+value.toString());
                    Map map = (Map) baseDao.loadBySql(sql, "map");
                    System.out.println(map.toString());
                    if(StringUtils.isNotBlank(map.get("BZDMS")+"")) {
                        value = map.get("BZDMS");
                        return value;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if("PROCEDURE".equals(fieldValue.getCallType()) &&  "CHECK".equals(fieldValue.getProcessType())){
                boolean  bool = false;
                String result = null ;
                try {
                    String packname = (fieldValue.getCellValueConfig().split("（"))[0];
                    String param = (fieldValue.getCellValueConfig().split("（")[1]).substring(0, (fieldValue.getCellValueConfig().split("（")[1]).length()-1);
                     result = callDao.preparesCall(packname, param);
                    System.out.println(result);
                    if("true".equals(result)){
                        bool = true;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(bool){//依据返回结果而定
                     return value;
                }else{
                    StringBuilder err = new StringBuilder();
                        err.append("第[").append(rowNum).append("行],[");
                        err .append(fieldValue.getTitle()).append("]");
                        err.append("在数据库中没有找到["+value.toString()+"]的信息");
                    throw new ExcelException(err.toString());
                }
            }else if("PROCEDURE".equals(fieldValue.getCallType()) &&  "PROCESSE".equals(fieldValue.getProcessType())){
                //待定（目前和上面一样）
                boolean  bool = false;
                String result = null ;
                try {
                    String packname = fieldValue.getCellValueConfig().split("（")[0];
                    String param = (fieldValue.getCellValueConfig().split("（")[1]).substring(0, (fieldValue.getCellValueConfig().split("（")[1]).length()-1);
                    result = callDao.preparesCall(packname, param);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(bool){//依据返回结果而定
                    return value;
                }else{
                    StringBuilder err = new StringBuilder();
                    err.append("第[").append(rowNum).append("行],[");
                    err .append(fieldValue.getTitle()).append("]");
                    err.append("在数据库中没有找到["+value.toString()+"]的信息");
                    throw new ExcelException(err.toString());
                }
            }
        }
        return value;
    }
    @Override
    public Object convert(Object bean, Object value, FieldTitleValue fieldValue, Type type, int rowNum) throws Exception {
//        IBaseDao baseDao=(IBaseDao) SpringContextUtils.getBean("baseDao");
//        System.out.println("OtherConfig的值:"+fieldValue.getOtherConfig());
//        if(type == Type.IMPORT) {
//            if("FUNCTION".equals(fieldValue.getCallType())) {
//                try {
//                    String functions = fieldValue.getCellValueConfig().replace("?",value.toString());
//                    String sql = "select "+functions+" bzdm from dual";
//                    System.out.println("FUNCTION时调用SQL《《》》: "+sql);
//                    System.out.println("value值《《》》: "+value.toString());
//                    Map map = (Map) baseDao.loadBySql(sql, "map");
//                    System.out.println(map.toString());
//                    if(StringUtils.isNotBlank(map.get("BZDM")+"")) {
//                        value = map.get("BZDM");
//                        return value;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }else if("PROCEDURE".equals(fieldValue.getCallType())){
//
//            }
//        }
        return value;
    }
}
