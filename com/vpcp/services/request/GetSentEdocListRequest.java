/*     */ package com.vpcp.services.request;
/*     */ 
/*     */ public class GetSentEdocListRequest
/*     */   extends Request
/*     */ {
/*     */   private String serviceType;
/*     */   private String messageType;
/*     */   private String username;
/*     */   private String password;
/*     */   private String fromDate;
/*     */   private String toDate;
/*     */   
/*     */   public GetSentEdocListRequest(String serviceType, String messageType, String username, String password, String fromDate, String toDate) {
/*  14 */     this.serviceType = serviceType;
/*  15 */     this.messageType = messageType;
/*  16 */     this.username = username;
/*  17 */     this.password = password;
/*  18 */     this.fromDate = fromDate;
/*  19 */     this.toDate = toDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GetSentEdocListRequest() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Validator validate() {
/*  29 */     Validator validator = new Validator();
/*  30 */     boolean checked = true;
/*  31 */     if (Validator.isEmpty(this.serviceType)) {
/*  32 */       checked = false;
/*  33 */       ValidateMessage message = new ValidateMessage(this.serviceType, "Invalid serviceType: requited not empty");
/*  34 */       validator.addMessage(message);
/*     */     } 
/*  36 */     if (Validator.isEmpty(this.messageType)) {
/*  37 */       checked = false;
/*  38 */       ValidateMessage message = new ValidateMessage(this.messageType, "Invalid messageType: requited not empty");
/*  39 */       validator.addMessage(message);
/*     */     
/*     */     }
/*  42 */     else if (!VALUES.contains(this.messageType)) {
/*  43 */       checked = false;
/*  44 */       ValidateMessage message = new ValidateMessage(this.messageType, "Invalid messageType: value in (edoc,status)");
/*  45 */       validator.addMessage(message);
/*     */     } 
/*     */     
/*  48 */     if (Validator.isEmpty(this.username)) {
/*  49 */       checked = false;
/*  50 */       ValidateMessage message = new ValidateMessage(this.username, "Invalid username: requited not empty");
/*  51 */       validator.addMessage(message);
/*     */     } 
/*  53 */     if (Validator.isEmpty(this.password)) {
/*  54 */       checked = false;
/*  55 */       ValidateMessage message = new ValidateMessage(this.password, "Invalid password: requited not empty");
/*  56 */       validator.addMessage(message);
/*     */     } 
/*     */     
/*  59 */     validator.setChecked(checked);
/*  60 */     return validator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServiceType() {
/*  66 */     return this.serviceType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServiceType(String serviceType) {
/*  71 */     this.serviceType = serviceType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessageType() {
/*  76 */     return this.messageType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageType(String messageType) {
/*  81 */     this.messageType = messageType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsername() {
/*  86 */     return this.username;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUsername(String username) {
/*  91 */     this.username = username;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPassword() {
/*  96 */     return this.password;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassword(String password) {
/* 101 */     this.password = password;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFromDate() {
/* 106 */     return this.fromDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFromDate(String fromDate) {
/* 111 */     this.fromDate = fromDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToDate() {
/* 116 */     return this.toDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToDate(String toDate) {
/* 121 */     this.toDate = toDate;
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\request\GetSentEdocListRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */