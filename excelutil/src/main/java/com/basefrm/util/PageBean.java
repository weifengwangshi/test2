package com.basefrm.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class PageBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer currentPage = 1;//当前页数
	private Integer pageSize = 10;//每页显示条数
	private Integer totalCount;//总记录数
	private Integer totalPage;//总页数
	private Integer beginNumber = 1;
	@SuppressWarnings("unchecked")
	private List rows=new ArrayList(); //数据集合
	
	
	
	

	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	@SuppressWarnings("unchecked")
	public List getRows() {
		
		return rows;
	}

	@SuppressWarnings("unchecked")
	public void setRows(List rows) {
		this.rows = rows;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
//		this.beginNumber = (currentPage-1)*this.pageSize+1;
		this.beginNumber = (currentPage-1)*this.pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		this.totalPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getBeginNumber() {
		return beginNumber;
	}
	public void setBeginNumber(Integer beginNumber) {
		this.beginNumber = beginNumber;
	}
	
}
