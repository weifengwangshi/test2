package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="DCXM_ZQBG")
@Data
public class Dcxm_Zqbg implements Serializable{

  private String xh;
  @Id
  private String dcxmbm;
  private String bglx;
  private String rq;
  private String cj;
  private String jl;
  private String bz;
}
