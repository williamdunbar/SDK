/*    */ package com.vpcp.services.exception;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RequestException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 13 */   protected static final String NL = System.getProperty("line.separator");
/*    */   
/*    */   public RequestException(String var1) {
/* 16 */     super(var1);
/*    */   }
/*    */   
/*    */   public RequestException(String var1, Throwable var2) {
/* 20 */     super(var1, var2);
/*    */   }
/*    */   
/*    */   public void printStackTrace() {
/* 24 */     System.err.println(this);
/*    */   }
/*    */   
/*    */   public void printStackTrace(PrintStream var1) {
/* 28 */     var1.println(this);
/*    */   }
/*    */   
/*    */   public void printStackTrace(PrintWriter var1) {
/* 32 */     var1.println(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 36 */     StringBuilder message = new StringBuilder();
/*    */     
/* 38 */     for (Object var2 = this; var2 != null; var2 = ((Throwable)var2).getCause()) {
/* 39 */       message.append(NL);
/* 40 */       message.append((var2 == this) ? "Exception:" : "Caused By:").append(NL).append("----------").append(NL);
/* 41 */       if (var2 instanceof AssertionError || var2 instanceof IllegalArgumentException || 
/* 42 */         var2 instanceof IllegalStateException || var2 instanceof NullPointerException) {
/* 43 */         message.append("**************************************************************").append(NL)
/* 44 */           .append("**                                                          **").append(NL)
/* 45 */           .append("**   PLEASE SEND THIS STACK TRACE TO thang24011983@gmail.com   **").append(NL)
/* 46 */           .append("**           HELP US FIX BUGS OR IMPROVE PRODUCT            **").append(NL)
/* 47 */           .append("**                                                          **").append(NL)
/* 48 */           .append("**************************************************************").append(NL);
/*    */       }
/*    */       
/*    */       try {
/* 52 */         appendExceptionTo(message, (Throwable)var2);
/* 53 */       } catch (IOException var7) {
/* 54 */         throw new AssertionError(var7);
/*    */       } 
/*    */       
/*    */       StackTraceElement[] var3;
/* 58 */       if ((var3 = ((Throwable)var2).getStackTrace()) != null && var3.length > 0) {
/* 59 */         message.append(">>> Stack trace: ").append(NL);
/* 60 */         int var4 = (var3 = var3).length;
/*    */         
/* 62 */         for (int var5 = 0; var5 < var4; var5++) {
/* 63 */           StackTraceElement var6 = var3[var5];
/* 64 */           message.append(">>>        at ").append(var6.getClassName()).append('.').append(var6.getMethodName())
/* 65 */             .append('(').append(var6.getFileName()).append(':')
/* 66 */             .append(Math.max(1, var6.getLineNumber())).append(')').append(NL);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 71 */     return message.toString();
/*    */   }
/*    */   
/*    */   protected void appendExceptionTo(Appendable var1, Throwable var2) throws IOException {
/* 75 */     var1.append(">>> Type: ").append(var2.getClass().getName()).append(NL);
/* 76 */     var1.append(">>> Message Request Validate:").append(NL).append("***************************************").append(NL).append(var2.getMessage()).append(NL);
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\exception\RequestException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */