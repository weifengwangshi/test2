package com.basefrm.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="XS_SX_QK")
@Data
public class Xs_Sx_Qk implements Serializable{
  private String xn;
  private String xq;
  @Id
  private String sxxmbm;
  private String yxmc;
  private String zydm;
  private String zymc;
  @Id
  private String xh;
  private String xsxm;
  private String scfj;
  private String bz;
  private String xhs;
}
