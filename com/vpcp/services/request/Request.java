/*    */ package com.vpcp.services.request;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ public abstract class Request
/*    */ {
/* 11 */   public static final String[] messageTypeValue = new String[] { "edoc", "status" };
/*    */   
/* 13 */   public static final Set<String> VALUES = new HashSet<>(Arrays.asList(
/* 14 */         messageTypeValue));
/*    */ 
/*    */   
/*    */   public String toString() {
/* 18 */     Gson gson = new Gson();
/* 19 */     String json = gson.toJson(this);
/* 20 */     return json;
/*    */   }
/*    */   
/*    */   public abstract Validator validate();
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\request\Request.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */