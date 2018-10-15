package com.basefrm.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="XFZH_QK")
@Data
public class Xfzh_Qk implements Serializable{
  private String xh;
  private String yxdm;
  private String yxmc;
  private String zzdm;
  private String zymc;
  @Id
  private String xuehao;
  private String xsxm;
  private String kcbm;
  private String zhkcmc;
  private String zhxf;
  private String zhyy;
  private String scfj;
  private String bz;

}
