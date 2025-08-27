/*    */ package com.vpcp.services.model;
/*    */ 
/*    */ public class BasicResult
/*    */ {
/*    */   private String status;
/*    */   
/*    */   public String getStatus() {
/*  8 */     return this.status;
/*    */   } private String errorCode; private String errorDesc;
/*    */   public void setStatus(String status) {
/* 11 */     this.status = status;
/*    */   }
/*    */   public String getErrorCode() {
/* 14 */     return this.errorCode;
/*    */   }
/*    */   public void setErrorCode(String errorCode) {
/* 17 */     this.errorCode = errorCode;
/*    */   }
/*    */   public String getErrorDesc() {
/* 20 */     return this.errorDesc;
/*    */   }
/*    */   public void setErrorDesc(String errorDesc) {
/* 23 */     this.errorDesc = errorDesc;
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\model\BasicResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */