package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="JXJHTZXX")
@Data
/*此表主键待定*/
public class Jxjhtzxx  implements Serializable{
  @Id
  private String xh;
  private String yxdm;
  private String yxmc;
  private String zzdm;
  private String zymc;
  private String kcbm;
  private String kcmc;
  private String bglb;
  private String bgrq;
  private String bgyy;
  private String bz;
  private String fj;

}
