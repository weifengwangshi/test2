package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name="JXHJ_XXYLB")
public class Jxhj_Xxylb  implements Serializable{
 @Id
  private String cgbm;
  private String cgmc;
  private String hjsj;
  private String gh;
  private String wcr;
  private String gh1;
  private String cyr1;
  private String gh2;
  private String cyr2;
  private String gh3;
  private String cyr3;
  private String dwdm;
  private String ssdw;
  private String hjdj;
  private String xh;
}
