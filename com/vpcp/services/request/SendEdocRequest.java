/*    */ package com.vpcp.services.request;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ public class SendEdocRequest
/*    */   extends Request
/*    */ {
/*    */   private File fileEdXML;
/*    */   private String username;
/*    */   private String password;
/*    */   private String messageType;
/*    */   private String serviceType;
/*    */   
/*    */   public SendEdocRequest() {}
/*    */   
/*    */   public SendEdocRequest(String username, String password, String messageType, String serviceType, File fileEdXML) {
/* 18 */     this.username = username;
/* 19 */     this.password = password;
/* 20 */     this.messageType = messageType;
/* 21 */     this.serviceType = serviceType;
/*    */   }
/*    */ 
/*    */   
/*    */   public Validator validate() {
/* 26 */     Validator validator = new Validator();
/* 27 */     boolean checked = true;
/* 28 */     if (Validator.isEmpty(this.serviceType)) {
/* 29 */       checked = false;
/* 30 */       ValidateMessage message = new ValidateMessage(this.serviceType, "Invalid serviceType: requited not empty");
/* 31 */       validator.addMessage(message);
/*    */     } 
/* 33 */     if (Validator.isEmpty(this.messageType)) {
/* 34 */       checked = false;
/* 35 */       ValidateMessage message = new ValidateMessage(this.messageType, "Invalid messageType: requited not empty");
/* 36 */       validator.addMessage(message);
/*    */     } 
/* 38 */     if (Validator.isEmpty(this.username)) {
/* 39 */       checked = false;
/* 40 */       ValidateMessage message = new ValidateMessage(this.username, "Invalid username: requited not empty");
/* 41 */       validator.addMessage(message);
/*    */     } 
/* 43 */     if (Validator.isEmpty(this.password)) {
/* 44 */       checked = false;
/* 45 */       ValidateMessage message = new ValidateMessage(this.password, "Invalid password: requited not empty");
/* 46 */       validator.addMessage(message);
/*    */     } 
/*    */     
/* 49 */     validator.setChecked(checked);
/* 50 */     return validator;
/*    */   }
/*    */ 
/*    */   
/*    */   public File getFileEdXML() {
/* 55 */     return this.fileEdXML;
/*    */   }
/*    */   
/*    */   public void setFileEdXML(File fileEdXML) {
/* 59 */     this.fileEdXML = fileEdXML;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 63 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 67 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 71 */     return this.password;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 75 */     this.password = password;
/*    */   }
/*    */   
/*    */   public String getMessageType() {
/* 79 */     return this.messageType;
/*    */   }
/*    */   
/*    */   public void setMessageType(String messageType) {
/* 83 */     this.messageType = messageType;
/*    */   }
/*    */   
/*    */   public String getServiceType() {
/* 87 */     return this.serviceType;
/*    */   }
/*    */   
/*    */   public void setServiceType(String serviceType) {
/* 91 */     this.serviceType = serviceType;
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\request\SendEdocRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */