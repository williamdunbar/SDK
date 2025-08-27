/*    */ package com.vpcp.services.request;
/*    */ 
/*    */ 
/*    */ public class ValidateMessage
/*    */ {
/*    */   private String field;
/*    */   private String message;
/*    */   
/*    */   public ValidateMessage() {}
/*    */   
/*    */   public ValidateMessage(String field, String message) {
/* 12 */     this.field = field;
/* 13 */     this.message = message;
/*    */   }
/*    */   
/*    */   public String getField() {
/* 17 */     return this.field;
/*    */   }
/*    */   
/*    */   public void setField(String field) {
/* 21 */     this.field = field;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 28 */     return this.message;
/*    */   }
/*    */   
/*    */   public void setMessage(String message) {
/* 32 */     this.message = message;
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\request\ValidateMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */