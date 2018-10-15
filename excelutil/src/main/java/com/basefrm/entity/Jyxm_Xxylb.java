package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="JYXM_XXYLB")
@Data
public class Jyxm_Xxylb implements Serializable {
  @Id
  private String xmbm;
  private String xmmc;
  private String kssj;
  private String gh;
  private String zcr;
  private String gh1;
  private String cyr1;
  private String gh2;
  private String cyr2;
  private String gh3;
  private String cyr3;
  private String dwdm;
  private String ssdw;
  private String jsjf;
  private String sdjt;
  private String xh;
}
