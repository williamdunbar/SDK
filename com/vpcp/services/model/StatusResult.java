/*     */ package com.vpcp.services.model;
/*     */ 
/*     */ 
/*     */ public class StatusResult
/*     */ {
/*     */   private String serviceType;
/*     */   private String receiverDocId;
/*     */   private String toCode;
/*     */   private String receiveStatus;
/*     */   private String title;
/*     */   private String messageType;
/*     */   private String notation;
/*     */   private String receivedTime;
/*     */   private String sentTime;
/*     */   private String sendStatus;
/*     */   private String sendStatusDesc;
/*     */   private String senderDocId;
/*     */   private String fromCode;
/*     */   private String receiveStatusDesc;
/*     */   
/*     */   public String getServiceType() {
/*  22 */     return this.serviceType;
/*     */   }
/*     */   
/*     */   public void setServiceType(String serviceType) {
/*  26 */     this.serviceType = serviceType;
/*     */   }
/*     */   
/*     */   public String getReceiverDocId() {
/*  30 */     return this.receiverDocId;
/*     */   }
/*     */   
/*     */   public void setReceiverDocId(String receiverDocId) {
/*  34 */     this.receiverDocId = receiverDocId;
/*     */   }
/*     */   
/*     */   public String getToCode() {
/*  38 */     return this.toCode;
/*     */   }
/*     */   
/*     */   public void setToCode(String toCode) {
/*  42 */     this.toCode = toCode;
/*     */   }
/*     */   
/*     */   public String getReceiveStatus() {
/*  46 */     return this.receiveStatus;
/*     */   }
/*     */   
/*     */   public void setReceiveStatus(String receiveStatus) {
/*  50 */     this.receiveStatus = receiveStatus;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  54 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/*  58 */     this.title = title;
/*     */   }
/*     */   
/*     */   public String getMessageType() {
/*  62 */     return this.messageType;
/*     */   }
/*     */   
/*     */   public void setMessageType(String messageType) {
/*  66 */     this.messageType = messageType;
/*     */   }
/*     */   
/*     */   public String getNotation() {
/*  70 */     return this.notation;
/*     */   }
/*     */   
/*     */   public void setNotation(String notation) {
/*  74 */     this.notation = notation;
/*     */   }
/*     */   
/*     */   public String getReceivedTime() {
/*  78 */     return this.receivedTime;
/*     */   }
/*     */   
/*     */   public void setReceivedTime(String receivedTime) {
/*  82 */     this.receivedTime = receivedTime;
/*     */   }
/*     */   
/*     */   public String getSentTime() {
/*  86 */     return this.sentTime;
/*     */   }
/*     */   
/*     */   public void setSentTime(String sentTime) {
/*  90 */     this.sentTime = sentTime;
/*     */   }
/*     */   
/*     */   public String getSendStatus() {
/*  94 */     return this.sendStatus;
/*     */   }
/*     */   
/*     */   public void setSendStatus(String sendStatus) {
/*  98 */     this.sendStatus = sendStatus;
/*     */   }
/*     */   
/*     */   public String getSendStatusDesc() {
/* 102 */     return this.sendStatusDesc;
/*     */   }
/*     */   
/*     */   public void setSendStatusDesc(String sendStatusDesc) {
/* 106 */     this.sendStatusDesc = sendStatusDesc;
/*     */   }
/*     */   
/*     */   public String getSenderDocId() {
/* 110 */     return this.senderDocId;
/*     */   }
/*     */   
/*     */   public void setSenderDocId(String senderDocId) {
/* 114 */     this.senderDocId = senderDocId;
/*     */   }
/*     */   
/*     */   public String getFromCode() {
/* 118 */     return this.fromCode;
/*     */   }
/*     */   
/*     */   public void setFromCode(String fromCode) {
/* 122 */     this.fromCode = fromCode;
/*     */   }
/*     */   
/*     */   public String getReceiveStatusDesc() {
/* 126 */     return this.receiveStatusDesc;
/*     */   }
/*     */   
/*     */   public void setReceiveStatusDesc(String receiveStatusDesc) {
/* 130 */     this.receiveStatusDesc = receiveStatusDesc;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 134 */     StringBuilder sb = new StringBuilder("StatusResult{");
/* 135 */     sb.append("serviceType='").append(this.serviceType).append('\'');
/* 136 */     sb.append(", receiverDocId='").append(this.receiverDocId).append('\'');
/* 137 */     sb.append(", toCode='").append(this.toCode).append('\'');
/* 138 */     sb.append(", receiveStatus='").append(this.receiveStatus).append('\'');
/* 139 */     sb.append(", title='").append(this.title).append('\'');
/* 140 */     sb.append(", messageType='").append(this.messageType).append('\'');
/* 141 */     sb.append(", notation='").append(this.notation).append('\'');
/* 142 */     sb.append(", receivedTime='").append(this.receivedTime).append('\'');
/* 143 */     sb.append(", sentTime='").append(this.sentTime).append('\'');
/* 144 */     sb.append(", sendStatus='").append(this.sendStatus).append('\'');
/* 145 */     sb.append(", sendStatusDesc='").append(this.sendStatusDesc).append('\'');
/* 146 */     sb.append(", senderDocId='").append(this.senderDocId).append('\'');
/* 147 */     sb.append(", fromCode='").append(this.fromCode).append('\'');
/* 148 */     sb.append(", receiveStatusDesc='").append(this.receiveStatusDesc).append('\'');
/* 149 */     sb.append('}');
/* 150 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\model\StatusResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */