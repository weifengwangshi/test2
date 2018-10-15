package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="ZYZGZS_HQQK")
@Data
public class Zyzgzs_Hqqk implements Serializable{
  private String xh;
  private String yxdm;
  private String yxmc;
  private String zzdm;
  private String zymc;
  @Id
  private String xuehao;
  private String xsxm;
  private String zsmc;
  private String zsbh;
  private String scfj;
  private String bz;

}
