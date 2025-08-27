/*     */ package com.vpcp.services.model;
/*     */ 
/*     */ 
/*     */ public class UnitSynchronize
/*     */ {
/*     */   private String id;
/*     */   private String pid;
/*     */   private String code;
/*     */   private String name;
/*     */   private String mail;
/*     */   private String centerCode;
/*     */   private int version;
/*     */   private boolean deleted;
/*     */   private String modifyDate;
/*     */   private boolean isAgency;
/*     */   
/*     */   public String getModifyDate() {
/*  18 */     return this.modifyDate;
/*     */   }
/*     */   
/*     */   public void setModifyDate(String modifyDate) {
/*  22 */     this.modifyDate = modifyDate;
/*     */   }
/*     */   
/*     */   public Boolean getIsAgency() {
/*  26 */     return Boolean.valueOf(this.isAgency);
/*     */   }
/*     */   
/*     */   public void setIsAgency(Boolean isAgency) {
/*  30 */     this.isAgency = isAgency.booleanValue();
/*     */   }
/*     */   
/*     */   public String getId() {
/*  34 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  38 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getPid() {
/*  42 */     return this.pid;
/*     */   }
/*     */   
/*     */   public void setPid(String pid) {
/*  46 */     this.pid = pid;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  50 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  54 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  58 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  62 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getMail() {
/*  66 */     return this.mail;
/*     */   }
/*     */   
/*     */   public void setMail(String mail) {
/*  70 */     this.mail = mail;
/*     */   }
/*     */   
/*     */   public String getCenterCode() {
/*  74 */     return this.centerCode;
/*     */   }
/*     */   
/*     */   public void setCenterCode(String centerCode) {
/*  78 */     this.centerCode = centerCode;
/*     */   }
/*     */   
/*     */   public int getVersion() {
/*  82 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setVersion(int version) {
/*  86 */     this.version = version;
/*     */   }
/*     */   
/*     */   public boolean isDeleted() {
/*  90 */     return this.deleted;
/*     */   }
/*     */   
/*     */   public void setDeleted(boolean deleted) {
/*  94 */     this.deleted = deleted;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  98 */     if (this == o)
/*  99 */       return true; 
/* 100 */     if (o != null && getClass() == o.getClass()) {
/* 101 */       UnitSynchronize unit = (UnitSynchronize)o;
/* 102 */       if (this.code != null) {
/* 103 */         if (!this.code.equals(unit.code)) {
/* 104 */           return false;
/*     */         }
/* 106 */       } else if (unit.code != null) {
/* 107 */         return false;
/*     */       } 
/*     */       
/* 110 */       return true;
/*     */     } 
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 117 */     int result = (this.code != null) ? this.code.hashCode() : 0;
/* 118 */     result = 31 * result + ((this.name != null) ? this.name.hashCode() : 0);
/* 119 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 123 */     StringBuilder sb = new StringBuilder("Unit{");
/* 124 */     sb.append("id='").append(this.id).append('\'');
/* 125 */     sb.append(", code='").append(this.code).append('\'');
/* 126 */     sb.append(", name='").append(this.name).append('\'');
/* 127 */     sb.append(", mail='").append(this.mail).append('\'');
/* 128 */     sb.append(", pid='").append(this.pid).append('\'');
/* 129 */     sb.append(", version='").append(this.version).append('\'');
/* 130 */     sb.append(", centerCode='").append(this.centerCode).append('\'');
/* 131 */     sb.append(", deleted='").append(this.deleted).append('\'');
/* 132 */     sb.append(", modifyDate='").append(this.modifyDate).append('\'');
/* 133 */     sb.append(", isAgency='").append(this.isAgency).append('\'');
/* 134 */     sb.append('}');
/* 135 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\model\UnitSynchronize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */