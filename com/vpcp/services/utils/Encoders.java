/*     */ package com.vpcp.services.utils;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.io.BaseEncoding;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Encoders
/*     */ {
/*  20 */   private static final char[] a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 
/*  21 */       'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */   
/*     */   private Encoders() {
/*  24 */     throw new AssertionError("The Encoders utility must not be instantiated.");
/*     */   }
/*     */   
/*     */   public static byte[] utf8encode(String var0) {
/*  28 */     if (var0 == null) {
/*  29 */       return null;
/*     */     }
/*  31 */     return (var0.length() == 0) ? new byte[0] : var0.getBytes(Charsets.UTF_8);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String utf8decode(byte[] var0) {
/*  36 */     if (var0 == null) {
/*  37 */       return null;
/*     */     }
/*  39 */     return (var0.length == 0) ? "" : new String(var0, Charsets.UTF_8);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] base64Encode(byte[] var0) {
/*  44 */     return utf8encode(BaseEncoding.base64().encode(var0));
/*     */   }
/*     */   
/*     */   public static byte[] base64Decode(byte[] var0) {
/*  48 */     return BaseEncoding.base64().decode(utf8decode(var0));
/*     */   }
/*     */   
/*     */   public static String base64EncodeText(byte[] var0) {
/*  52 */     return BaseEncoding.base64().encode(var0);
/*     */   }
/*     */   
/*     */   public static String base64UrlEncodeText(byte[] var0) {
/*  56 */     return BaseEncoding.base64Url().encode(var0);
/*     */   }
/*     */   
/*     */   public static byte[] base64DecodeText(String var0) {
/*  60 */     return BaseEncoding.base64().decode(var0);
/*     */   }
/*     */   
/*     */   public static byte[] base64UrlDecodeText(String var0) {
/*  64 */     return BaseEncoding.base64Url().decode(var0);
/*     */   }
/*     */   
/*     */   public static String bytesToHex(byte[] var0) {
/*  68 */     return bytesToHex(var0, true);
/*     */   }
/*     */   
/*     */   public static OutputStream createMimeEncodingStream(OutputStream var0) {
/*  72 */     return BaseEncoding.base64().withSeparator("\r\n", 76).encodingStream(new OutputStreamWriter(var0));
/*     */   }
/*     */   
/*     */   public static InputStream createMimeDecodingStream(InputStream var0) {
/*  76 */     return BaseEncoding.base64().withSeparator("\r\n", 76).decodingStream(new InputStreamReader(var0));
/*     */   }
/*     */   
/*     */   public static OutputStream createPemEncodingStream(OutputStream var0) {
/*  80 */     return BaseEncoding.base64().withSeparator("\r\n", 64).encodingStream(new OutputStreamWriter(var0));
/*     */   }
/*     */   
/*     */   public static InputStream createPemDecodingStream(InputStream var0) {
/*  84 */     return BaseEncoding.base64().withSeparator("\r\n", 64).decodingStream(new InputStreamReader(var0));
/*     */   }
/*     */   
/*     */   public static String bytesToHex(byte[] var0, boolean var1) {
/*  88 */     char[] var2 = new char[var0.length << 1];
/*  89 */     int var3 = var1 ? 0 : 16;
/*     */     
/*  91 */     for (int var4 = 0; var4 < var0.length; var4++) {
/*  92 */       int var5 = var0[var4] & 0xFF;
/*  93 */       var2[var4 << 1] = a[var5 >>> 4 | var3];
/*  94 */       var2[(var4 << 1) + 1] = a[var5 & 0xF | var3];
/*     */     } 
/*     */     
/*  97 */     return new String(var2);
/*     */   }
/*     */   
/*     */   public static byte[] hexToBytes(String var0) {
/*     */     int var1;
/* 102 */     if ((var1 = var0.length()) % 2 != 0) {
/* 103 */       throw new IllegalArgumentException("The hex value: [" + var0 + "] is not valid.");
/*     */     }
/* 105 */     byte[] var2 = new byte[var1 / 2];
/*     */     
/* 107 */     for (int var3 = 0; var3 < var1; var3 += 2) {
/* 108 */       var2[var3 / 2] = 
/* 109 */         (byte)(Character.digit(var0.charAt(var3), 16) << 4 | Character.digit(var0.charAt(var3 + 1), 16));
/*     */     }
/*     */     
/* 112 */     return var2;
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\service\\utils\Encoders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */