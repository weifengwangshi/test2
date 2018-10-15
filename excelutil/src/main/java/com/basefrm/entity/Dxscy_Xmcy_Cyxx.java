package com.basefrm.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="DXSCY_XMCY_CYXX")
@Data
public class Dxscy_Xmcy_Cyxx implements Serializable {
  private String xhs;
  @Id
  private String dcxmbm;
  @Id
  private String xh;
  private String xsxm;
  private String zt;
  private String bt;
}
