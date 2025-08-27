/*    */ package com.vpcp.services.request;
/*    */ 
/*    */ import com.vpcp.services.utils.StringUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Validator
/*    */ {
/*    */   private boolean checked;
/* 14 */   private List<ValidateMessage> messages = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public static boolean isEmpty(String value) {
/* 18 */     return StringUtils.isNullOrEmpty(value);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isEmail(String value) {
/* 24 */     String EMAIL_PATTERN = 
/* 25 */       "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
/*    */     
/* 27 */     Pattern pattern = Pattern.compile(EMAIL_PATTERN);
/* 28 */     Matcher matcher = pattern.matcher(value);
/* 29 */     return matcher.matches();
/*    */   }
/*    */   
/*    */   public boolean isChecked() {
/* 33 */     return this.checked;
/*    */   }
/*    */   
/*    */   public void setChecked(boolean checked) {
/* 37 */     this.checked = checked;
/*    */   }
/*    */   
/*    */   public List<ValidateMessage> getMessages() {
/* 41 */     return this.messages;
/*    */   }
/*    */   
/*    */   public void setMessages(List<ValidateMessage> messages) {
/* 45 */     this.messages = messages;
/*    */   }
/*    */   
/*    */   public void addMessage(ValidateMessage message) {
/* 49 */     this.messages.add(message);
/*    */   }
/*    */   
/* 52 */   protected static final String NL = System.getProperty("line.separator");
/*    */   public String getMessage() {
/* 54 */     StringBuffer mBuffer = new StringBuffer();
/* 55 */     for (ValidateMessage validateMessage : this.messages) {
/* 56 */       mBuffer.append(validateMessage.getMessage());
/* 57 */       mBuffer.append(NL);
/*    */     } 
/* 59 */     return mBuffer.toString();
/*    */   }
/*    */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\request\Validator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */