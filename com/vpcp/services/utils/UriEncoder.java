/*    */ package com.vpcp.services.utils;
/*    */ 
/*    */ import com.google.common.base.Charsets;
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.URLDecoder;
/*    */ import java.nio.charset.Charset;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UriEncoder
/*    */ {
/*    */   private UriEncoder() {
/* 21 */     throw new AssertionError("UriEncoder utility class must not be instantiated.");
/*    */   }
/*    */   
/*    */   public static String encode(String var0) {
/* 25 */     return encode(var0, DEFAULT_ENCODING);
/*    */   }
/*    */   
/*    */   public static String encode(String var0, Charset var1) {
/* 29 */     Preconditions.checkNotNull(var0);
/* 30 */     Preconditions.checkNotNull(var1);
/*    */     
/*    */     try {
/* 33 */       return FastUrlEncoder.uriEncode(var0, var1.name());
/* 34 */     } catch (UnsupportedEncodingException var2) {
/* 35 */       throw new AssertionError(var2);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String decode(String var0) {
/* 40 */     return decode(var0, DEFAULT_ENCODING);
/*    */   }
/*    */   
/*    */   public static String decode(String var0, Charset var1) {
/* 44 */     Preconditions.checkNotNull(var0);
/* 45 */     Preconditions.checkNotNull(var1);
/*    */     
/*    */     try {
/* 48 */       return URLDecoder.decode(var0, var1.name());
/* 49 */     } catch (UnsupportedEncodingException var2) {
/* 50 */       throw new AssertionError(var2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 55 */   public static final Charset DEFAULT_ENCODING = Charsets.UTF_8;
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\service\\utils\UriEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */