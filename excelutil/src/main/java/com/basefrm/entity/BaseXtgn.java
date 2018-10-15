package com.basefrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * BaseXtgn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BASE_XTGN")
public class BaseXtgn  implements java.io.Serializable {


    @Id
     private String gnxbh;
     private String gnxmc;
     private Integer px;
     private String url;
     private String sjgnbh;
     private Integer cdjb;
     private String tp;
     private String bz;
     private String zt;
     private Date gxsj;
     private String hlp;


    // Constructors

    /** default constructor */
    public BaseXtgn() {
    }

	/** minimal constructor */
    public BaseXtgn(String gnxbh, String gnxmc, Integer px, String url, Integer cdjb) {
        this.gnxbh = gnxbh;
        this.gnxmc = gnxmc;
        this.px = px;
        this.url = url;
        this.cdjb = cdjb;
    }
    
    /** full constructor */
    public BaseXtgn(String gnxbh, String gnxmc, Integer px, String url, String sjgnbh, Integer cdjb, String tp, String bz, String zt, Date gxsj, String hlp) {
        this.gnxbh = gnxbh;
        this.gnxmc = gnxmc;
        this.px = px;
        this.url = url;
        this.sjgnbh = sjgnbh;
        this.cdjb = cdjb;
        this.tp = tp;
        this.bz = bz;
        this.zt = zt;
        this.gxsj = gxsj;
        this.hlp = hlp;
    }

   
    // Property accessors

    public String getGnxbh() {
        return this.gnxbh;
    }
    
    public void setGnxbh(String gnxbh) {
        this.gnxbh = gnxbh;
    }

    public String getGnxmc() {
        return this.gnxmc;
    }
    
    public void setGnxmc(String gnxmc) {
        this.gnxmc = gnxmc;
    }

    public Integer getPx() {
        return this.px;
    }
    
    public void setPx(Integer px) {
        this.px = px;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getSjgnbh() {
        return this.sjgnbh;
    }
    
    public void setSjgnbh(String sjgnbh) {
        this.sjgnbh = sjgnbh;
    }

    public Integer getCdjb() {
        return this.cdjb;
    }
    
    public void setCdjb(Integer cdjb) {
        this.cdjb = cdjb;
    }

    public String getTp() {
        return this.tp;
    }
    
    public void setTp(String tp) {
        this.tp = tp;
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

    public Date getGxsj() {
        return this.gxsj;
    }
    
    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getHlp() {
        return this.hlp;
    }
    
    public void setHlp(String hlp) {
        this.hlp = hlp;
    }
   








}