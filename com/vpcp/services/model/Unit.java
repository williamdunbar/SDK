/*     */ package com.vpcp.services.model;
/*     */ 
/*     */ public class Unit
/*     */ {
/*     */   private String id;
/*     */   private String pid;
/*     */   private String code;
/*     */   private String name;
/*     */   private String mail;
/*     */   private String centerCode;
/*     */   private int version;
/*     */   private boolean deleted;
/*     */   
/*     */   public String getId() {
/*  15 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  19 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getPid() {
/*  23 */     return this.pid;
/*     */   }
/*     */   
/*     */   public void setPid(String pid) {
/*  27 */     this.pid = pid;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  31 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  35 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  39 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  43 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getMail() {
/*  47 */     return this.mail;
/*     */   }
/*     */   
/*     */   public void setMail(String mail) {
/*  51 */     this.mail = mail;
/*     */   }
/*     */   
/*     */   public String getCenterCode() {
/*  55 */     return this.centerCode;
/*     */   }
/*     */   
/*     */   public void setCenterCode(String centerCode) {
/*  59 */     this.centerCode = centerCode;
/*     */   }
/*     */   
/*     */   public int getVersion() {
/*  63 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setVersion(int version) {
/*  67 */     this.version = version;
/*     */   }
/*     */   
/*     */   public boolean isDeleted() {
/*  71 */     return this.deleted;
/*     */   }
/*     */   
/*     */   public void setDeleted(boolean deleted) {
/*  75 */     this.deleted = deleted;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  79 */     if (this == o)
/*  80 */       return true; 
/*  81 */     if (o != null && getClass() == o.getClass()) {
/*  82 */       Unit unit = (Unit)o;
/*  83 */       if (this.code != null) {
/*  84 */         if (!this.code.equals(unit.code)) {
/*  85 */           return false;
/*     */         }
/*  87 */       } else if (unit.code != null) {
/*  88 */         return false;
/*     */       } 
/*     */       
/*  91 */       return true;
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  98 */     int result = (this.code != null) ? this.code.hashCode() : 0;
/*  99 */     result = 31 * result + ((this.name != null) ? this.name.hashCode() : 0);
/* 100 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 104 */     StringBuilder sb = new StringBuilder("Unit{");
/* 105 */     sb.append("id='").append(this.id).append('\'');
/* 106 */     sb.append(", code='").append(this.code).append('\'');
/* 107 */     sb.append(", name='").append(this.name).append('\'');
/* 108 */     sb.append(", mail='").append(this.mail).append('\'');
/* 109 */     sb.append(", pid='").append(this.pid).append('\'');
/* 110 */     sb.append(", version='").append(this.version).append('\'');
/* 111 */     sb.append(", centerCode='").append(this.centerCode).append('\'');
/* 112 */     sb.append(", deleted='").append(this.deleted).append('\'');
/* 113 */     sb.append('}');
/* 114 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\model\Unit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */