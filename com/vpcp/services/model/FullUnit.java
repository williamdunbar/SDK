/*     */ package com.vpcp.services.model;
/*     */ 
/*     */ public class FullUnit
/*     */ {
/*     */   private String id;
/*     */   private String code;
/*     */   private String name;
/*     */   private SmallUnit parent;
/*     */   private String mail;
/*     */   private Integer version;
/*     */   private String centerCode;
/*     */   private PAction pAction;
/*     */   private Link link;
/*     */   private Valid valid;
/*     */   
/*     */   public enum PAction {
/*  17 */     CREATE,
/*  18 */     MODIFY,
/*  19 */     DELETE;
/*     */   }
/*     */   
/*     */   public enum Link {
/*  23 */     REGISTERED,
/*  24 */     PENDING,
/*  25 */     NOT_EXIST;
/*     */   }
/*     */   
/*     */   public enum Valid
/*     */   {
/*  30 */     VALID,
/*  31 */     CONFLICT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/*  37 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  41 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  45 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  49 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  53 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  57 */     this.name = name;
/*     */   }
/*     */   
/*     */   public SmallUnit getParent() {
/*  61 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void setParent(SmallUnit parent) {
/*  65 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public String getMail() {
/*  69 */     return this.mail;
/*     */   }
/*     */   
/*     */   public void setMail(String mail) {
/*  73 */     this.mail = mail;
/*     */   }
/*     */   
/*     */   public Integer getVersion() {
/*  77 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setVersion(Integer version) {
/*  81 */     this.version = version;
/*     */   }
/*     */   
/*     */   public String getCenterCode() {
/*  85 */     return this.centerCode;
/*     */   }
/*     */   
/*     */   public void setCenterCode(String centerCode) {
/*  89 */     this.centerCode = centerCode;
/*     */   }
/*     */   
/*     */   public PAction getpAction() {
/*  93 */     return this.pAction;
/*     */   }
/*     */   
/*     */   public void setpAction(PAction pAction) {
/*  97 */     this.pAction = pAction;
/*     */   }
/*     */   
/*     */   public void setPAction(PAction paction) {
/* 101 */     this.pAction = paction;
/*     */   }
/*     */   
/*     */   public Link getLink() {
/* 105 */     return this.link;
/*     */   }
/*     */   
/*     */   public void setLink(Link link) {
/* 109 */     this.link = link;
/*     */   }
/*     */   
/*     */   public Valid getValid() {
/* 113 */     return this.valid;
/*     */   }
/*     */   
/*     */   public void setValid(Valid valid) {
/* 117 */     this.valid = valid;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 121 */     StringBuilder sb = new StringBuilder("FullUnit{");
/* 122 */     sb.append("id='").append(this.id).append('\'');
/* 123 */     sb.append(", code='").append(this.code).append('\'');
/* 124 */     sb.append(", name='").append(this.name).append('\'');
/* 125 */     sb.append(", mail='").append(this.mail).append('\'');
/* 126 */     sb.append(", PAction='").append(this.pAction).append('\'');
/* 127 */     sb.append(", version='").append(this.version).append('\'');
/* 128 */     sb.append(", centerCode='").append(this.centerCode).append('\'');
/* 129 */     sb.append(", parent='").append(this.parent).append('\'');
/* 130 */     sb.append(", link='").append(this.link).append('\'');
/* 131 */     sb.append(", valid='").append(this.valid).append('\'');
/* 132 */     sb.append('}');
/* 133 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\model\FullUnit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */