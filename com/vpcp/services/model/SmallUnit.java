/*    */ package com.vpcp.services.model;
/*    */ 
/*    */ public class SmallUnit {
/*    */   private String id;
/*    */   private String name;
/*    */   
/*    */   public String getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 12 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 20 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 24 */     StringBuilder sb = new StringBuilder("SmallUnit{");
/* 25 */     sb.append("id='").append(this.id).append('\'');
/* 26 */     sb.append(", name='").append(this.name).append('\'');
/* 27 */     sb.append('}');
/* 28 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\model\SmallUnit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */