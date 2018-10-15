package com.basefrm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="DXSJS_ZDJS")
@Data
/*此表主键待定*/
public class Dxsjs_Zdjs implements Serializable{

  private String jsbm;
  private String zdjsgh;
  private String zdjsxm;
  private String szyxdm;
  private String yxmc;
  private String cjzydm;
  private String cjzymc;
  private String zc;
  private String bz;
  @Id
  private String xh;
}
