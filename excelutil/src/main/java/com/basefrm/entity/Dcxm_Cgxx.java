package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="DCXM_CGXX")
@Data
public class Dcxm_Cgxx implements Serializable{

  private String xh;
  @Id
  private String dcxmbm;
  private String jtcg;
  private String fjxx;
  private String bz;


}
