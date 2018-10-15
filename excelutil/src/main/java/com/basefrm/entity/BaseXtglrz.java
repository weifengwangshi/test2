package com.basefrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * BaseXtglrz entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BASE_XTGLRZ")
public class BaseXtglrz implements java.io.Serializable {

	@Id
	private String id;
	private String czyh;
	private String bmbm;
	private Date czsj;
	private String cznr;
	private String jsjip;
	private String cz;
	private String gnxbh;

	// Constructors

	/** default constructor */
	public BaseXtglrz() {
	}

	/** minimal constructor */
	public BaseXtglrz(String id, String bmbm) {
		this.id = id;
		this.bmbm = bmbm;
	}

	/** full constructor */
	public BaseXtglrz(String id, String czyh, String bmbm, Date czsj,
			String cznr, String jsjip, String cz, String gnxbh) {
		this.id = id;
		this.czyh = czyh;
		this.bmbm = bmbm;
		this.czsj = czsj;
		this.cznr = cznr;
		this.jsjip = jsjip;
		this.cz = cz;
		this.gnxbh = gnxbh;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCzyh() {
		return this.czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}

	public String getBmbm() {
		return this.bmbm;
	}

	public void setBmbm(String bmbm) {
		this.bmbm = bmbm;
	}

	public Date getCzsj() {
		return this.czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getCznr() {
		return this.cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
	}

	public String getJsjip() {
		return this.jsjip;
	}

	public void setJsjip(String jsjip) {
		this.jsjip = jsjip;
	}

	public String getCz() {
		return this.cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}

	public String getGnxbh() {
		return this.gnxbh;
	}

	public void setGnxbh(String gnxbh) {
		this.gnxbh = gnxbh;
	}

}