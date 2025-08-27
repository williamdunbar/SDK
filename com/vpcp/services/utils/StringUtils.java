/*    */ package com.vpcp.services.utils;
/*    */ 
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StringUtils
/*    */ {
/*    */   public static boolean hasLength(String value) {
/* 15 */     return !isNullOrEmpty(value);
/*    */   }
/*    */   
/*    */   public static boolean isNullOrEmpty(String value) {
/* 19 */     return !(value != null && !"".equals(value));
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 23 */     Date d = new Date();
/* 24 */     System.out.println((new SimpleDateFormat("ddMMyyyy'T'HHmmss+SSSS")).format(d));
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\service\\utils\StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */