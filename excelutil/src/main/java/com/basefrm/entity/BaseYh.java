package com.basefrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;


/**
 * BaseYh entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BASE_YH")
public class BaseYh  implements java.io.Serializable {
     @Id
     private String yhbh;
     private String dlmc;
     private String mm;
     private String yhmc;
     private String bm;
     private String sfzmhm;
     private String sjh;
     private String lxdh;
     private String zt;
     private String bz;
     private Date gxsj;
     private String yhlx;
     private String zhjzrq;
     private String mmjzrq;
     private String dzyx;
     private String guid;

    @Transient //表示此数据不在数据库表里建立属性
    private String bmbm;
    @Transient //表示此数据不在数据库表里建立属性
    private String bmmc;
    @Transient //表示此数据不在数据库表里建立属性
    private String dlmcs;

    public String getBmbm() {
        return bmbm;
    }

    public void setBmbm(String bmbm) {
        this.bmbm = bmbm;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getDlmcs() {
        return dlmcs;
    }

    public void setDlmcs(String dlmcs) {
        this.dlmcs = dlmcs;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh;
    }

    public String getDlmc() {
        return dlmc;
    }

    public void setDlmc(String dlmc) {
        this.dlmc = dlmc;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getYhlx() {
        return yhlx;
    }

    public void setYhlx(String yhlx) {
        this.yhlx = yhlx;
    }

    public String getZhjzrq() {
        return zhjzrq;
    }

    public void setZhjzrq(String zhjzrq) {
        this.zhjzrq = zhjzrq;
    }

    public String getMmjzrq() {
        return mmjzrq;
    }

    public void setMmjzrq(String mmjzrq) {
        this.mmjzrq = mmjzrq;
    }

    public String getDzyx() {
        return dzyx;
    }

    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
// Constructors

    /** default constructor */
    public BaseYh() {
    }



}