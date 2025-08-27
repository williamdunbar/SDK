/*     */ package com.vpcp.services.utils;
/*     */ 
/*     */ import com.google.common.base.Throwables;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.BitSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FastUrlEncoder
/*     */ {
/*  17 */   private static final char[] a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 
/*  18 */       'E', 'F' };
/*  19 */   private static final BitSet b = new BitSet(256);
/*     */ 
/*     */   
/*     */   private FastUrlEncoder() {
/*  23 */     throw new AssertionError("An FastUrlEncoder utility class must not be instantiated.");
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(String var0, String var1, BitSet var2, boolean var3, Appendable var4) throws UnsupportedEncodingException, IOException {
/*  28 */     byte[] var9 = var0.getBytes(var1);
/*  29 */     boolean var5 = false;
/*  30 */     int var6 = 0;
/*     */     
/*  32 */     for (int var7 = 0; var7 < var9.length; var7++) {
/*     */       int var8;
/*  34 */       if ((var8 = var9[var7]) < 0) {
/*  35 */         var8 += 256;
/*     */       }
/*     */       
/*  38 */       if (var2.get(var8)) {
/*  39 */         var4.append((char)var8);
/*  40 */         var6++;
/*  41 */       } else if (var3 && var8 == 32) {
/*  42 */         var5 = true;
/*  43 */         var4.append('+');
/*  44 */         var6++;
/*     */       } else {
/*  46 */         var4.append('%');
/*  47 */         var4.append(a[var8 >> 4]);
/*  48 */         var4.append(a[var8 & 0xF]);
/*  49 */         var6 += 3;
/*     */       } 
/*     */     } 
/*     */     
/*  53 */     if (!var5 && var6 == var0.length()) {
/*  54 */       return false;
/*     */     }
/*  56 */     return true;
/*     */   }
/*     */   
/*     */   private static String a(String var0, String var1, BitSet var2, boolean var3) throws UnsupportedEncodingException {
/*     */     boolean var6;
/*  61 */     StringBuilder var4 = new StringBuilder(var0.length() << 1);
/*     */ 
/*     */     
/*     */     try {
/*  65 */       var6 = a(var0, var1, var2, var3, var4);
/*  66 */     } catch (IOException var5) {
/*  67 */       Throwables.propagateIfInstanceOf(var5, UnsupportedEncodingException.class);
/*  68 */       throw new AssertionError(var5);
/*     */     } 
/*     */     
/*  71 */     return var6 ? var4.toString() : var0;
/*     */   }
/*     */   
/*     */   public static String urlEncode(String var0, String var1) throws UnsupportedEncodingException {
/*  75 */     return a(var0, var1, b, true);
/*     */   }
/*     */   
/*     */   public static String uriEncode(String var0, String var1) throws UnsupportedEncodingException {
/*  79 */     return a(var0, var1, c, false);
/*     */   }
/*     */   
/*     */   static {
/*     */     int var0;
/*  84 */     for (var0 = 48; var0 <= 57; var0++) {
/*  85 */       b.set(var0);
/*     */     }
/*     */     
/*  88 */     for (var0 = 65; var0 <= 90; var0++) {
/*  89 */       b.set(var0);
/*     */     }
/*     */     
/*  92 */     for (var0 = 97; var0 <= 122; var0++) {
/*  93 */       b.set(var0);
/*     */     }
/*     */     
/*  96 */     b.set(45);
/*  97 */     b.set(95);
/*  98 */     b.set(46);
/*  99 */     b.set(42);
/* 100 */   } private static final BitSet c = new BitSet(256);
/*     */   static {
/* 102 */     for (var0 = 48; var0 <= 57; var0++) {
/* 103 */       c.set(var0);
/*     */     }
/*     */     
/* 106 */     for (var0 = 65; var0 <= 90; var0++) {
/* 107 */       c.set(var0);
/*     */     }
/*     */     
/* 110 */     for (var0 = 97; var0 <= 122; var0++) {
/* 111 */       c.set(var0);
/*     */     }
/*     */     
/* 114 */     c.set(45);
/* 115 */     c.set(95);
/* 116 */     c.set(46);
/* 117 */     c.set(126);
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\service\\utils\FastUrlEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */