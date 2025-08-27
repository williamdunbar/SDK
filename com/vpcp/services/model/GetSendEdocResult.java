/*    */ package com.vpcp.services.model;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class GetSendEdocResult extends BasicResult {
/*    */   private List<StatusResult> statusResult;
/*    */   
/*    */   public List<StatusResult> getStatusResult() {
/*  9 */     return this.statusResult;
/*    */   }
/*    */   
/*    */   public void setStatusResult(List<StatusResult> statusResult) {
/* 13 */     this.statusResult = statusResult;
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\model\GetSendEdocResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */