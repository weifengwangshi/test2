package com.basefrm.entity;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name= "GXYX_JZJSXX")
@Data
public class Gxyx_Jzjsxx implements Serializable {
  @Id
  private String gh;
  private String xm;
  private String yxmc;
  private String xb;
  private String mz;
  private String jg;
  private String zzmm;
  private String sfzhm;
  private String byyx;
  private String xl;
  private String xw;
  private String zw;
  private String zc;
  private String jslb;
  private String rkkc;
  private String sfssx;
  private String lxsj;
  private String lxfs;
  private String xh;
  @Id
  private String tjsj;

}
