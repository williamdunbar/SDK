/*    */ package com.vpcp.services.request;
/*    */ 
/*    */ public class AgencyRequest
/*    */   extends Request
/*    */ {
/*    */   private String id;
/*    */   private String pid;
/*    */   private String code;
/*    */   private String name;
/*    */   private String mail;
/*    */   
/*    */   public AgencyRequest() {}
/*    */   
/*    */   public AgencyRequest(String id, String pid, String code, String name, String email) {
/* 15 */     this.id = id;
/* 16 */     this.code = code;
/* 17 */     this.pid = pid;
/* 18 */     this.name = name;
/* 19 */     this.mail = email;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 23 */     return this.id;
/*    */   }
/*    */   public void setId(String id) {
/* 26 */     this.id = id;
/*    */   }
/*    */   public String getPid() {
/* 29 */     return this.pid;
/*    */   }
/*    */   public void setPid(String pid) {
/* 32 */     this.pid = pid;
/*    */   }
/*    */   public String getCode() {
/* 35 */     return this.code;
/*    */   }
/*    */   public void setCode(String code) {
/* 38 */     this.code = code;
/*    */   }
/*    */   public String getName() {
/* 41 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 44 */     this.name = name;
/*    */   }
/*    */   public String getMail() {
/* 47 */     return this.mail;
/*    */   }
/*    */   public void setMail(String mail) {
/* 50 */     this.mail = mail;
/*    */   }
/*    */ 
/*    */   
/*    */   public Validator validate() {
/* 55 */     Validator validator = new Validator();
/* 56 */     boolean checked = true;
/* 57 */     if (Validator.isEmpty(this.code)) {
/* 58 */       checked = false;
/* 59 */       ValidateMessage message = new ValidateMessage(this.code, "Invalid code: requited not empty");
/* 60 */       validator.addMessage(message);
/*    */     } 
/* 62 */     if (Validator.isEmpty(this.name)) {
/* 63 */       checked = false;
/* 64 */       ValidateMessage message = new ValidateMessage(this.name, "Invalid name: requited not empty");
/* 65 */       validator.addMessage(message);
/*    */     } 
/* 67 */     if (Validator.isEmpty(this.mail)) {
/* 68 */       checked = false;
/* 69 */       ValidateMessage message = new ValidateMessage(this.mail, "Invalid email: requited not empty");
/* 70 */       validator.addMessage(message);
/*    */     } 
/* 72 */     if (!Validator.isEmail(this.mail)) {
/* 73 */       checked = false;
/* 74 */       ValidateMessage message = new ValidateMessage(this.mail, "Invalid mail: don't well format email");
/* 75 */       validator.addMessage(message);
/*    */     } 
/* 77 */     validator.setChecked(checked);
/* 78 */     return validator;
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\request\AgencyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */