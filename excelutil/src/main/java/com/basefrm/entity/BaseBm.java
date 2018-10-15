package com.basefrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;


/**
 * BaseBm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BASE_BM")
public class BaseBm  implements java.io.Serializable {


    @Id
    private String bmbm;
    private String sjbmbm;
    private String bmmc;
    private String bmjc;
    private Integer bmjb;
    private String jglx;
    private Date gxsj;
    private String bz;
    private String zt;
     @Transient //表示此数据不在数据库表里建立属性
     private String csbj;


    // Constructors  
	/** default constructor */
    public BaseBm() {
    }

	/** minimal constructor */
    public BaseBm(String bmbm) {
        this.bmbm = bmbm;
    }
    
    /** full constructor */
    public BaseBm(String bmbm, String bmmc, String sjbmbm, Integer bmjb, String jglx,  Date gxsj, String bz, String zt,String bmjc) {
        this.bmbm = bmbm;
        this.bmmc = bmmc;
        this.sjbmbm = sjbmbm;
        this.bmjb = bmjb;
        this.jglx = jglx;
        this.gxsj = gxsj;
        this.bz = bz;
        this.zt = zt;
        this.bmjc = bmjc;
    }

   
    // Property accessors

    public String getBmbm() {
        return this.bmbm;
    }
    
    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

    public String getBmmc() {
        return this.bmmc;
    }
    
    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getSjbmbm() {
        return this.sjbmbm;
    }
    
    public void setSjbmbm(String sjbmbm) {
        this.sjbmbm = sjbmbm;
    }

    public Integer getBmjb() {
        return this.bmjb;
    }
    
    public void setBmjb(Integer bmjb) {
        this.bmjb = bmjb;
    }



    public String getJglx() {
        return this.jglx;
    }
    
    public void setJglx(String jglx) {
        this.jglx = jglx;
    }



    public Date getGxsj() {
        return this.gxsj;
    }
    
    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
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

	public String getCsbj() {
		return csbj;
	}

	public void setCsbj(String csbj) {
		this.csbj = csbj;
	}
	public String getBmjc() {
		return bmjc;
	}

	public void setBmjc(String bmjc) {
		this.bmjc = bmjc;
	}
}