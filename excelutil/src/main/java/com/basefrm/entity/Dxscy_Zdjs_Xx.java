package com.basefrm.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="DXSCY_ZDJS_XX")
@Data
public class Dxscy_Zdjs_Xx implements Serializable{

  private String xh;
  @Id
  private String dcxmbm;
  @Id
  private String gh;
  private String zdjsxm;
  private String szbmdm;
  private String dzbmmc;
  private String xl;
  private String zc;
  private String zt;
  private String bz;

}
