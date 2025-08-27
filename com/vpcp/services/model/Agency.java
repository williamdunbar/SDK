/*     */ package com.vpcp.services.model;
/*     */ 
/*     */ public class Agency
/*     */ {
/*     */   private String id;
/*     */   private String pid;
/*     */   private String code;
/*     */   private String name;
/*     */   private String mail;
/*     */   private String oldCode;
/*     */   private String code102;
/*     */   
/*     */   public String getCode() {
/*  14 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  18 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  22 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  26 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getMail() {
/*  30 */     return this.mail;
/*     */   }
/*     */   
/*     */   public void setMail(String email) {
/*  34 */     this.mail = email;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  38 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  42 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getPid() {
/*  46 */     return this.pid;
/*     */   }
/*     */   
/*     */   public void setPid(String pid) {
/*  50 */     this.pid = pid;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOldCode() {
/*  55 */     return this.oldCode;
/*     */   }
/*     */   
/*     */   public void setOldCode(String oldCode) {
/*  59 */     this.oldCode = oldCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCode102() {
/*  65 */     return this.code102;
/*     */   }
/*     */   
/*     */   public void setCode102(String code102) {
/*  69 */     this.code102 = code102;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  73 */     if (this == o)
/*  74 */       return true; 
/*  75 */     if (o != null && getClass() == o.getClass()) {
/*  76 */       Agency agency = (Agency)o;
/*  77 */       if (this.code != null) {
/*  78 */         if (!this.code.equals(agency.code)) {
/*  79 */           return false;
/*     */         }
/*  81 */       } else if (agency.code != null) {
/*  82 */         return false;
/*     */       } 
/*     */       
/*  85 */       if (this.name != null) {
/*  86 */         if (this.name.equals(agency.name)) {
/*  87 */           return true;
/*     */         }
/*  89 */       } else if (agency.name == null) {
/*  90 */         return true;
/*     */       } 
/*     */       
/*  93 */       return false;
/*     */     } 
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 100 */     int result = (this.code != null) ? this.code.hashCode() : 0;
/* 101 */     result = 31 * result + ((this.name != null) ? this.name.hashCode() : 0);
/* 102 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     StringBuilder sb = new StringBuilder("Agency{");
/* 107 */     sb.append("id='").append(this.id).append('\'');
/* 108 */     sb.append(", code='").append(this.code).append('\'');
/* 109 */     sb.append(", name='").append(this.name).append('\'');
/* 110 */     sb.append(", mail='").append(this.mail).append('\'');
/* 111 */     sb.append(", pid='").append(this.pid).append('\'');
/* 112 */     sb.append('}');
/* 113 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\model\Agency.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */