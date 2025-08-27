/*    */ package com.vpcp.services.exception;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConfigureException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public ConfigureException() {}
/*    */   
/* 19 */   protected static final String NL = System.getProperty("line.separator");
/*    */   
/*    */   public ConfigureException(String var1) {
/* 22 */     super(var1);
/*    */   }
/*    */   
/*    */   public ConfigureException(String var1, Throwable var2) {
/* 26 */     super(var1, var2);
/*    */   }
/*    */   
/*    */   public void printStackTrace() {
/* 30 */     System.err.println(this);
/*    */   }
/*    */   
/*    */   public void printStackTrace(PrintStream var1) {
/* 34 */     var1.println(this);
/*    */   }
/*    */   
/*    */   public void printStackTrace(PrintWriter var1) {
/* 38 */     var1.println(this);
/*    */   }
/*    */   
/*    */   protected void appendExceptionTo(Appendable var1, Throwable var2) throws IOException {
/* 42 */     var1.append(">>> Type: ").append(var2.getClass().getName()).append(NL);
/* 43 */     var1.append(">>> Message Configure Validate:").append(NL).append("***************************************").append(NL).append(var2.getMessage()).append(NL);
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\exception\ConfigureException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */