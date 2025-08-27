/*    */ package com.vpcp.services.utils;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ import java.util.Comparator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Parameter
/*    */   implements Comparator<Parameter>, Map.Entry<String, String>
/*    */ {
/*    */   private final String key;
/*    */   private String value;
/*    */   
/*    */   public Parameter(String key) {
/* 19 */     this(key, null);
/*    */   }
/*    */   
/*    */   public Parameter(String key, String value) {
/* 23 */     this.key = key;
/* 24 */     this.value = value;
/*    */   }
/*    */   
/*    */   public final String getKey() {
/* 28 */     return this.key;
/*    */   }
/*    */   
/*    */   public final String getValue() {
/* 32 */     return this.value;
/*    */   }
/*    */   
/*    */   public final String setValue(String var1) {
/*    */     String var2;
/*    */     try {
/* 38 */       var2 = this.value;
/*    */     } finally {
/* 40 */       this.value = var1;
/*    */     } 
/*    */     
/* 43 */     return var2;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/* 47 */     return Objects.hashCode(new Object[] { this.key, this.value });
/*    */   }
/*    */   
/*    */   public final boolean equals(Object var1) {
/* 51 */     if (this == var1)
/* 52 */       return true; 
/* 53 */     if (var1 != null && var1 instanceof Parameter) {
/* 54 */       Parameter var2 = (Parameter)var1;
/* 55 */       return (Objects.equal(this.key, var2.key) && Objects.equal(this.value, var2.value));
/*    */     } 
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public final int compare(Parameter var1, Parameter var2) {
/* 62 */     return var1.toString().compareTo(var2.toString());
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\service\\utils\Parameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */