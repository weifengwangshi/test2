package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="DXSCYFH_ZDJS")
@Data
public class Dxscyfh_Zdjs implements Serializable{

  private String xh;
  @Id
  private String dcfhxmbm;
  @Id
  private String gh;
  private String zdjsxm;
  private String szbmdm;
  private String szbmmc;
  private String xl;
  private String zc;
  private String zt;
  private String bz;


}
