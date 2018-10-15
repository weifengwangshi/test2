package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="DXSCY_XMMC_BGXX")
@Data
public class Dxscy_Xmmc_Bgxx implements Serializable{

  private String xh;
  @Id
  private String dcxmbm;
  private String xmmcbgw;
  private String bz;

}
