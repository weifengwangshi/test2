package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="JSSKJHXX")
@Data
public class Jsskjhxx implements Serializable{
  private String xh;
  @Id
  private String skjhbh;
  private String yxdm;
  private String yxmc;
  private String zydm;
  private String zymc;
  private String skbj;
  @Id
  private String gh;
  private String skjs;
  private String kcbm;
  private String kcmc;
  private String skdd;
  private String bz;

}
