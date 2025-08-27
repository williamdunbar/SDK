/*     */ package com.vpcp.services.request;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GetReceivedEdocListRequest
/*     */   extends Request
/*     */ {
/*     */   private String serviceType;
/*     */   private String messageType;
/*     */   private String username;
/*     */   private String password;
/*     */   private String fromDate;
/*     */   private String toDate;
/*     */   
/*     */   public GetReceivedEdocListRequest(String serviceType, String messageType, String username, String password, String fromDate, String toDate) {
/*  16 */     this.serviceType = serviceType;
/*  17 */     this.messageType = messageType;
/*  18 */     this.username = username;
/*  19 */     this.password = password;
/*  20 */     this.fromDate = fromDate;
/*  21 */     this.toDate = toDate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GetReceivedEdocListRequest() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Validator validate() {
/*  31 */     Validator validator = new Validator();
/*  32 */     boolean checked = true;
/*  33 */     if (Validator.isEmpty(this.serviceType)) {
/*  34 */       checked = false;
/*  35 */       ValidateMessage message = new ValidateMessage(this.serviceType, "Invalid serviceType: requited not empty");
/*  36 */       validator.addMessage(message);
/*     */     } 
/*  38 */     if (Validator.isEmpty(this.messageType)) {
/*  39 */       checked = false;
/*  40 */       ValidateMessage message = new ValidateMessage(this.messageType, "Invalid messageType: requited not empty");
/*  41 */       validator.addMessage(message);
/*     */     }
/*  43 */     else if (!VALUES.contains(this.messageType)) {
/*  44 */       checked = false;
/*  45 */       ValidateMessage message = new ValidateMessage(this.messageType, "Invalid messageType: value in (edoc,status)");
/*  46 */       validator.addMessage(message);
/*     */     } 
/*     */ 
/*     */     
/*  50 */     if (Validator.isEmpty(this.username)) {
/*  51 */       checked = false;
/*  52 */       ValidateMessage message = new ValidateMessage(this.username, "Invalid username: requited not empty");
/*  53 */       validator.addMessage(message);
/*     */     } 
/*  55 */     if (Validator.isEmpty(this.password)) {
/*  56 */       checked = false;
/*  57 */       ValidateMessage message = new ValidateMessage(this.password, "Invalid password: requited not empty");
/*  58 */       validator.addMessage(message);
/*     */     } 
/*     */     
/*  61 */     validator.setChecked(checked);
/*  62 */     return validator;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getServiceType() {
/*  67 */     return this.serviceType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServiceType(String serviceType) {
/*  72 */     this.serviceType = serviceType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessageType() {
/*  77 */     return this.messageType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMessageType(String messageType) {
/*  82 */     this.messageType = messageType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsername() {
/*  87 */     return this.username;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUsername(String username) {
/*  92 */     this.username = username;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPassword() {
/*  97 */     return this.password;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassword(String password) {
/* 102 */     this.password = password;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFromDate() {
/* 107 */     return this.fromDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFromDate(String fromDate) {
/* 112 */     this.fromDate = fromDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getToDate() {
/* 117 */     return this.toDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setToDate(String toDate) {
/* 122 */     this.toDate = toDate;
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\request\GetReceivedEdocListRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */