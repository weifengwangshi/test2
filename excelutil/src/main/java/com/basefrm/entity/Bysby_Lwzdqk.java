package com.basefrm.entity;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="bysby_lwzdqk")
@Data
public class Bysby_Lwzdqk implements Serializable {
  @Id
  private String byjq;
  private String yxdm;
  private String yxmc;
  private String zydm;
  private String zymc;
  private String bjdm;
  private String bjmc;
  @Id
  private String xh;
  private String xsxm;
  private String lwtmbm;
  private String lwtm;
  private String ktrq;
  private String tmsfbg;
  private String bgrq;
  private String gh;
  private String zdjsxm;
  private String zdjdpdcj;
  private String pyjspdcj;
  private String dbcj;
  private String cjdj;
  private String xhs;
}
