package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="JSJRJ_KSCJ")
@Data
public class Jsjrj_Kscj implements Serializable {

  private String yxdm;
  private String yxmc;
  private String zydm;
  private String zymc;
  @Id
  private String xh;
  private String xsxm;
  private String zgdj;
  private String cj;
  private String sshg;
  private String xhs;
}
