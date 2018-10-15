package com.basefrm.controller;

import com.basefrm.dao.IBaseDao;
import com.basefrm.dao.Impl.BaseDaoImpl;
import com.basefrm.entity.BaseYh;
import com.basefrm.excel.ExcelContext;
import com.basefrm.excel.parsing.ExcelError;
import com.basefrm.excel.result.ExcelImportResult;
import com.basefrm.excel.util.ExcelErrorMs;
import com.basefrm.util.FileUtils;
import com.basefrm.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ImportController<T extends Serializable> {
    //配置文件路径
   // private static ExcelContext context = new ExcelContext(ResourceUtils.CLASSPATH_URL_PREFIX+"excel-config.xml");
    // Excel配置文件中配置的id
   // private static String excelId = "student";
    @Autowired
    private BaseDaoImpl baseDao;
    @Value("${excel.nums}")
    private int nums;
    @Value("${error.multivalidate.value}")
    private boolean multivalidate;
    @Value("${file.excel.basePath}")
    private  String filePathd;
    //数据导入,method= RequestMethod.GET
    @RequestMapping(value="/dataImport.do")
    public @ResponseBody Map dataImport(HttpSession session, HttpServletRequest request, @RequestParam("file_upload") MultipartFile multipartFile,String excelId) {
        Map map = new HashMap();
        Map semap = new HashMap();
            try {
                BaseYh yh = (BaseYh) session.getAttribute("carryYh");
                //准备excel文件流
                //InputStream excelStream = new FileInputStream("E:/exportStudent.xlsx");
                InputStream excelStream = multipartFile.getInputStream();
                //创建excel上下文实例,它的构成需要配置文件的路径ExcelContext(ResourceUtils.CLASSPATH_URL_PREFIX+"excelconfig/gxjg_qjb.xml");
               // ExcelContext context = new ExcelContext("excel-config.xml");
                ExcelContext context = new ExcelContext("excelconfig/excel-config.xml");
                //按照xml配置中id为student的配置形式读取excel文件,并转换成StudentModel
                //这里的第二个参数是值,标题是第几行开始,之前也说了标题之前的数据并不是规则的数据
                // ExcelImportResult result = context.readExcel("student", 0,excelStream);
                //titleIndex 在这不用写，写到xml 配置文件中
                ExcelImportResult result = context.readExcel(excelId,5,excelStream,multivalidate,"dr");
                // 打印导入结果,查看标题之前不规则的数据
               List<List<Object>> header = result.getHeader();
//                System.out.println(header.get(0));
//                System.out.println(header.get(1));
//                System.out.println(result.getTitles());
                //查看学生集合导入结果
//                for(int i = 0; i<result.getErrors().size();i++){
//                    System.out.println(result.getErrors().get(i).getRow()+" HHHHHHHH "+result.getErrors().get(i).getErrorMsg());
//                }
               // String folderPath = System.getProperty("catalina.base") + "/temp/";
                //currentYh = CommonUtil.getCurrentYhbh(request);
                //importInfo=folderPath + "ErrorInfo"+"-"+currentYh+".xls";
               // WritableWorkbook book=Workbook.createWorkbook(new File(importInfo));
               // System.out.println(result.getErrors());
                List<T> model = result.getListBean();
                //List<Studentmodel1> students = result.getListBean();
                boolean pl = false;
                int success = 0;
                int errordu = 0;
                int errorin = 0;
                int row = 1;
                for(T md:model){
                    try {
                            if(!Utils.ObjectIsEmpty(md)){
                                pl =  baseDao.saveOrUpdateModel(md);
                            }else{
                                errordu++;
                            }
                         } catch (Exception e) {
                              errorin++;
                              result.getErrors().add(new ExcelError(row, "数据保存时出错"+md.toString()));
                             // System.out.println("错误信息："+e.getMessage());
                              continue;
                         }
                         if(pl && !Utils.ObjectIsEmpty(md)){
                               success++;
                         }else if(!pl && !Utils.ObjectIsEmpty(md)){
                               errorin++;
                         }
                     if(row % nums == 0 ){ //更新进度条(每nums条更新一次session)
                            int  ycls = success+errorin+errordu;
                            int  zs = model.size();
                            int intsf = (ycls*100)/zs;
                            map.put("jd",intsf);
                            map.put("mx","导入数据共："+zs+" 条数据，成功导入："+success+" 条，失败："+(errorin+errordu)+" 条，正在导入，请稍等...");
                            session.setAttribute("exp",map);
                     }
                    if( row == (model.size())){
                        int  ycls = success+errorin+errordu;
                        int  zs = model.size();
                        map.put("jd",100);
                        map.put("mx","导入数据共："+zs+" 条数据，成功导入："+success+" 条，失败："+(errorin+errordu)+" 条，已完毕！！！");
                        //map.put("mx",zs+"/"+ycls);
                        session.setAttribute("exp",map);
                    }
                    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHh row :"+row +"MMMMMMMMMMMMMMMMMMM model.size"+model.size());
                    row++;
                }
                boolean bool = ExcelErrorMs.writeExcel(result.getErrors());
                if(Utils.isNotEmpty(result.getErrors())) {
                    bool = ExcelErrorMs.writeExcel(result.getErrors());
                }
                System.out.println(bool);
                map.put("totle",model.size());
                map.put("suces",success);
                map.put("error",errorin+errordu);
                map.put("flag","1");
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("测试异常：》》》》》"+e.getMessage());
                map.put("flag","0");
                //map.put("message",e.getMessage());
                map.put("message","数据异常！！！");
            }
        return map;
    }
    //数据预览
    @RequestMapping(value="/dataExpyl.do")
    public @ResponseBody Map dataExpyl(HttpSession session, HttpServletRequest request, @RequestParam("file_upload") MultipartFile multipartFile,String excelId) {
        Map map = new HashMap();

        try {
            //准备excel文件流
            //InputStream excelStream = new FileInputStream("E:/exportStudent.xlsx");
            InputStream excelStream = multipartFile.getInputStream();
            //创建excel上下文实例,它的构成需要配置文件的路径ExcelContext(ResourceUtils.CLASSPATH_URL_PREFIX+"excelconfig/gxjg_qjb.xml");
            // ExcelContext context = new ExcelContext("excel-config.xml");
            ExcelContext context = new ExcelContext("excelconfig/excel-config.xml");
            //按照xml配置中id为student的配置形式读取excel文件,并转换成StudentModel
            //这里的第二个参数是值,标题是第几行开始,之前也说了标题之前的数据并不是规则的数据
            // ExcelImportResult result = context.readExcel("student", 0,excelStream);
            ExcelImportResult result = context.readExcel(excelId,0,excelStream,true,"yl");
            // 打印导入结果,查看标题之前不规则的数据
            List<List<Object>> header = result.getHeader();
            System.out.println(result.getTitles());
            //查看集合导入结果
            List<Object> imp = result.getListBean();
           // students.RemoveRange(0,100);
            map.put("title",result.getTitle());
            map.put("names",result.getNames());
            map.put("datas",imp);
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("getSejd.do")
    public  @ResponseBody Map getProgess(HttpServletRequest request , HttpServletResponse response, HttpSession session){
        long startTime = System.currentTimeMillis();
        System.out.println("调用时间："+startTime);
        Map map = new HashMap();
        try {
            // BaseYh yh = (BaseYh) session.getAttribute("carryYh");
             //System.out.println("》》》》》》》》》》》》"+session.getAttribute(yh.getYhbh()+"exp")+"《《《《《《《《《《《《《");
             map = (Map)session.getAttribute("exp");
            //ImportSchedule sj = (ImportSchedule)session.getAttribute(yh.getYhbh()+"exp");
            //System.out.println(sj.getSumProgress()+"  MMMMMMMMMMMMMMMMMMMMMMMMM   "+sj.getTotalData());
            if(Utils.isNotEmpty(map)) {
                System.out.println(map.get("jd") + "  <---------------------->   " + map.get("mx"));
                if ("100".equals(map.get("jd") + "")) {
                    session.removeAttribute( "exp");
                }
            }else{
                System.out.println("《进度为空----------------------------》");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

//    //定时任务
//    @Scheduled(cron = "0/2 * * * * *")
//    public void getProgess2(){
//        long startTime = System.currentTimeMillis();
//        System.out.println("系统时间："+startTime);
//
//    }
    @RequestMapping("getXmlId.do")
    public @ResponseBody Map getXmlId(HttpServletRequest request,HttpServletResponse response,HttpSession session,String fileName){
        Map map = new HashMap();
        Map mapXml = new HashMap();
        if(Utils.isNotEmpty(fileName)) {
            String sql = "select XH,LB,GJBS,PZID,MB,HH,WJM,BLB from excel_pzxx where gjbs = '" + fileName + "' and lb = 'R' and rownum = 1";
            try {
                mapXml = (Map)baseDao.loadBySql(sql,"map");
                if(Utils.isNotEmpty(mapXml.get("PZID")+"")){
                    map.put("xmlId",mapXml.get("PZID")+"");
                    map.put("xmlHh",mapXml.get("HH")+"");
                    map.put("xmlBlb",mapXml.get("BLB")+"");
                    map.put("code","1");
                }else{
                    map.put("code","0");
                    map.put("massage","数据库中没有对应的值！请检查数据库配置！！！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code","0");
                map.put("massage","数据异常！！！");
            }
        }else{
            map.put("code","0");
            map.put("massage","文件名为空！");
        }
        return map;
    }
    //错误信息下载
    @RequestMapping(value="errorInfoDownload.do", method= RequestMethod.GET)
    public @ResponseBody String errorInfoDownload(HttpSession session,HttpServletRequest request, HttpServletResponse response){
        BaseYh yh=(BaseYh) session.getAttribute("carryYh");
//        String folderPath2 = System.getProperty("catalina.base") + "/temp/";
//        String filePath=folderPath2 + "ExcelErrorInfo"+"-"+yh.getYhbh()+".xlsx";
        String filePath = filePathd+"ExcelErrorInfos.xlsx";
        FileUtils.downloadFile(filePath, "错误信息.xls", response);
        return null;
    }
    //定时删除临时文件
    @Scheduled(cron = "0 0 1 * * ?")
    public  void  delAllFile() {
        String path = filePathd;
        File file = new File(path);
        if (!file.exists()) {
            return ;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
        }
        System.out.println("删除临时文件！！！");
    }

}
