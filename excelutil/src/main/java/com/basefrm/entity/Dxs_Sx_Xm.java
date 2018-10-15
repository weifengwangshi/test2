package com.basefrm.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="DXS_SX_XM")
@Data
/*此表主键待定*/
public class Dxs_Sx_Xm implements Serializable{
  private String xn;
  private String xq;
  private String sxxmbm;
  private String yxdm;
  private String yxmc;
  private String zydm;
  private String zymc;
  private String nj;
  private String xf;
  private String dyxq;
  private String qsrq;
  private String jsrq;
  private String sxlx;
  private String cd;
  private String sxxs;
  private String ywsxqy;
  private String qydm;
  private String qymc;
  private String sxdd;
  private String dxls;
  private String fdy;
  private String bz;
  @Id
  private String xh;

}
