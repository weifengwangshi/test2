package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="DCXM_CYBG")
@Data
public class Dcxm_Cybg implements Serializable{

  private String xh;
  @Id
  private String dcxmbm;
  private String yxh;
  private String yxsxm;
  @Id
  private String xuehao;
  private String xsxm;
  private String zt;
  private String bz;

}
