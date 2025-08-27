/*    */ package com.vpcp.services.request;
/*    */ 
/*    */ public class GetEdocRequest
/*    */   extends Request
/*    */ {
/*    */   private String docId;
/*    */   private String filePath;
/*    */   private String username;
/*    */   private String password;
/*    */   
/*    */   public GetEdocRequest() {}
/*    */   
/*    */   public GetEdocRequest(String docId, String filePath, String username, String password) {
/* 14 */     this.docId = docId;
/* 15 */     this.filePath = filePath;
/* 16 */     this.username = username;
/* 17 */     this.password = password;
/*    */   }
/*    */ 
/*    */   
/*    */   public Validator validate() {
/* 22 */     Validator validator = new Validator();
/* 23 */     boolean checked = true;
/* 24 */     if (Validator.isEmpty(this.docId)) {
/* 25 */       checked = false;
/* 26 */       ValidateMessage message = new ValidateMessage(this.docId, "Invalid docId: requited not empty");
/* 27 */       validator.addMessage(message);
/*    */     } 
/* 29 */     if (Validator.isEmpty(this.username)) {
/* 30 */       checked = false;
/* 31 */       ValidateMessage message = new ValidateMessage(this.username, "Invalid username: requited not empty");
/* 32 */       validator.addMessage(message);
/*    */     } 
/* 34 */     if (Validator.isEmpty(this.password)) {
/* 35 */       checked = false;
/* 36 */       ValidateMessage message = new ValidateMessage(this.password, "Invalid password: requited not empty");
/* 37 */       validator.addMessage(message);
/*    */     } 
/*    */     
/* 40 */     validator.setChecked(checked);
/* 41 */     return validator;
/*    */   }
/*    */   
/*    */   public String getDocId() {
/* 45 */     return this.docId;
/*    */   }
/*    */   
/*    */   public void setDocId(String docId) {
/* 49 */     this.docId = docId;
/*    */   }
/*    */   
/*    */   public String getFilePath() {
/* 53 */     return this.filePath;
/*    */   }
/*    */   
/*    */   public void setFilePath(String filePath) {
/* 57 */     this.filePath = filePath;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 61 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 65 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 69 */     return this.password;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 73 */     this.password = password;
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\request\GetEdocRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */