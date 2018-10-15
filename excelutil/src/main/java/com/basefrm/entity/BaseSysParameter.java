package com.basefrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseSysParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BASE_SYS_PARAMETER")
public class BaseSysParameter implements java.io.Serializable {

	@Id
	private String bm;
	private String mc;
	private String csz;
	private String bz;
	private String zt;
	private String lb;

	// Constructors

	/** default constructor */
	public BaseSysParameter() {
	}

	/** minimal constructor */
	public BaseSysParameter(String bm, String mc) {
		this.bm = bm;
		this.mc = mc;
	}

	/** full constructor */
	public BaseSysParameter(String bm, String mc, String csz, String bz,
			String zt,String lb) {
		this.bm = bm;
		this.mc = mc;
		this.csz = csz;
		this.bz = bz;
		this.zt = zt;
		this.lb = lb;
	}

	// Property accessors

	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getCsz() {
		return this.csz;
	}

	public void setCsz(String csz) {
		this.csz = csz;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}