package com.basefrm.controller;
import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basefrm.dao.IBaseDao;
import com.basefrm.util.FileUtils;
import com.basefrm.util.Utils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.*;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExportController {
	
	@Autowired
	private IBaseDao baseDao;

	@RequestMapping("exportYm")
	//@ResponseBody
	public String exportYm(){
		return "export";
	}

	@RequestMapping("export")
	@ResponseBody
	public Map exportExcel(HttpServletRequest request,HttpServletResponse response){
		Map map = new HashMap();
		String zds="";
		int titleRow = 0;//
		String sql = request.getParameter("sql");
		if(Utils.isEmpty(sql)||"".equals(sql)){
			map.put("code", "0");
			map.put("message","务必填写查询sql");
			return map;
		}else {
			zds = sql.split("select")[1].split("from")[0];
		}
		String tableName = request.getParameter("tableName");
		if(Utils.isEmpty(tableName)||"".equals(tableName)){
			map.put("code", "0");
			map.put("message","务必填写保存的文件名");
			return map;
		}
		String colNames = request.getParameter("colNames");
		if(Utils.isEmpty(colNames)||"".equals(colNames)){
			map.put("code", "0");
			map.put("message","务必填写导出表所对应的列名,以英文逗号隔开!");
			return map;
		}
		String zd[] = zds.split(",");
		String titleContent = request.getParameter("titleContent");
		if(!Utils.isEmpty(titleContent)||!"".equals(titleContent)){
			//如果有表行则给死表头占2行
			titleRow = 2;
		}

		String[] col = colNames.split(",");
		if(col.length!=zd.length){
			map.put("code", "0");
			map.put("message","请检查输入列名称是否和查询字段个数以及顺序相对应!");
			return map;
		}

		WritableWorkbook book=null;
		//String folderPath = System.getProperty("catalina.base") + "/temp/";
		String folderPath = "";
		try {
			//生成.csv文件
			book = Workbook.createWorkbook(new File(folderPath+tableName+".xls"));

			// 设置字体
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 12);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD);

			// 标题居中
			WritableCellFormat titleFormat = new WritableCellFormat(BoldFont);
			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			titleFormat.setAlignment(Alignment.CENTRE); // 文字水平对齐
			titleFormat.setWrap(false); // 文字是否换行

			//设置字体;
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);

			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
			//设置背景颜色;  Colour.BLUE_GREY)
			cellFormat1.setBackground(Colour.BLUE_GREY);
			//设置边框;
			cellFormat1.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
			//设置自动换行;
			cellFormat1.setWrap(true);
			//设置文字居中对齐方式;
			cellFormat1.setAlignment(Alignment.CENTRE);
			//设置垂直居中;
			cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 正文居中
			WritableCellFormat contentCenterFormat = new WritableCellFormat(NormalFont);
			contentCenterFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			contentCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			contentCenterFormat.setAlignment(Alignment.CENTRE);
			contentCenterFormat.setWrap(false);

			// 正文右对齐
			WritableCellFormat contentRightFormat = new WritableCellFormat(NormalFont);
			contentRightFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			contentRightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			contentRightFormat.setAlignment(Alignment.RIGHT);
			contentRightFormat.setWrap(false);
			WritableSheet sheet=null;
			int index=1;

			sheet=book.createSheet(tableName, index);
			//excel表头  参数为(列号,行号,列号,行号)
			if(titleRow!=0){
				//带标题
				// 合并
				sheet.mergeCells(0, 0,zd.length,titleRow); // 合并单元格
				Label label0 = new Label(0, 0,titleContent,titleFormat);
				sheet.addCell(label0);
				Label label1 = new Label(0, titleRow+1,"序号",cellFormat1);
				sheet.addCell(label1);
				//先列后行
				for(int i=0;i<zd.length;i++){
					//System.out.println(zd[i].trim());
					Label label = new Label(i+1,titleRow+1,col[i].trim().toUpperCase(),cellFormat1);
					sheet.addCell(label);

				}

				List list=(List) baseDao.loadBySql(sql, "");
				//处理数据
				titleRow=titleRow+2;
				if(Utils.isNotEmpty(list)){
					for(int i=titleRow;i<list.size()+titleRow;i++){
						Map jgmd=(Map) list.get(i-titleRow);
						Label lbl0   = new Label(0,i,Integer.toString(i-titleRow+1),titleFormat);
						sheet.addCell(lbl0);

						for(int j=0;j<zd.length;j++){
							//第四行
							Label lbl1 = new Label(j+1,i,(Utils.isEmpty(jgmd.get(zd[j].trim().toUpperCase())+"")?"":jgmd.get(zd[j].trim().toUpperCase())+""),titleFormat);
							sheet.addCell(lbl1);
						}
					}
				}
			}else{
				//不带标题

			//第一行 row=0
			Label label1 = new Label(0, 0,"序号",cellFormat1);
			sheet.addCell(label1);
			//先列后行
			for(int i=0;i<zd.length;i++){
				//System.out.println(zd[i].trim());
				Label label = new Label(i+1,0,col[i].trim().toUpperCase(),cellFormat1);
				sheet.addCell(label);

			}

			List list=(List) baseDao.loadBySql(sql, "");
			//处理数据
			if(Utils.isNotEmpty(list)){
				for(int i=0;i<list.size();i++){
					Map jgmd=(Map) list.get(i);
					Label lbl0   = new Label(0,i+1,Integer.toString(i+1),titleFormat);
					sheet.addCell(lbl0);

					for(int j=0;j<zd.length;j++){
						//第二行
						Label lbl1 = new Label(j+1,i+1,(Utils.isEmpty(jgmd.get(zd[j].trim().toUpperCase())+"")?"":jgmd.get(zd[j].trim().toUpperCase())+""),titleFormat);
						sheet.addCell(lbl1);
					}
				}
			}
			}
			book.write();
			book.close();
			FileUtils.downloadFile(folderPath+tableName+".xls", tableName+".xls", response);
		} catch (Exception e) {
			map.put("code", "0");
			map.put("message",e.getMessage());
			e.printStackTrace();
		}
		map.put("code", "1");
		map.put("message","到处成功!");
		return map;
	}

}
