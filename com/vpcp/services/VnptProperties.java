/*     */ package com.vpcp.services;
/*     */ 
/*     */ import com.vpcp.services.exception.ConfigureException;
/*     */ import com.vpcp.services.utils.StringUtils;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class VnptProperties
/*     */ {
/*  16 */   private String endpoint = "";
/*  17 */   private String protocol = "HTTP";
/*  18 */   private String host = "";
/*  19 */   private int port = 80;
/*     */   
/*  21 */   private String systemId = "";
/*  22 */   private String secret = "";
/*  23 */   private String storePathDir = "";
/*  24 */   private String logError = "";
/*     */   
/*  26 */   private int retry = 3;
/*  27 */   private int maxConnection = 10;
/*     */   
/*  29 */   private int isLog = 0;
/*     */   
/*     */   public int getIsLog() {
/*  32 */     return this.isLog;
/*     */   }
/*     */   
/*     */   public void setIsLog(int isLog) {
/*  36 */     this.isLog = isLog;
/*     */   }
/*     */   
/*     */   public VnptProperties(String fileConfig) {
/*  40 */     Properties configure = new Properties();
/*  41 */     InputStream stream = null;
/*     */     try {
/*  43 */       if (StringUtils.isNullOrEmpty(fileConfig)) {
/*  44 */         throw new ConfigureException("File config 'collaboration.properties' not found");
/*     */       }
/*  46 */       stream = new FileInputStream(fileConfig);
/*     */ 
/*     */       
/*     */       try {
/*  50 */         configure.load(stream);
/*  51 */         this.protocol = configure.getProperty("client.protocol");
/*  52 */         if (StringUtils.isNullOrEmpty(this.protocol)) {
/*  53 */           throw new ConfigureException("configure client.protocol not found");
/*     */         }
/*  55 */         this.endpoint = configure.getProperty("service.endpoint");
/*  56 */         if (StringUtils.isNullOrEmpty(this.endpoint)) {
/*  57 */           throw new ConfigureException("configure service.endpoint not found");
/*     */         }
/*  59 */         this.storePathDir = configure.getProperty("store-path-dir");
/*  60 */         if (StringUtils.isNullOrEmpty(this.storePathDir)) {
/*  61 */           throw new ConfigureException("configure store-path-dir not found");
/*     */         }
/*     */         
/*  64 */         this.logError = configure.getProperty("log-error");
/*     */         
/*  66 */         if (StringUtils.isNullOrEmpty(this.logError)) {
/*  67 */           throw new ConfigureException("log-error");
/*     */         }
/*  69 */         File file = new File(this.logError);
/*  70 */         boolean isDirectory = file.isDirectory();
/*  71 */         if (!isDirectory) {
/*  72 */           throw new ConfigureException("log-error folder not found");
/*     */         }
/*     */ 
/*     */         
/*  76 */         this.systemId = configure.getProperty("systemid");
/*  77 */         if (StringUtils.isNullOrEmpty(this.systemId)) {
/*  78 */           throw new ConfigureException("configure systemId not found");
/*     */         }
/*     */         
/*  81 */         this.secret = configure.getProperty("secret");
/*  82 */         if (StringUtils.isNullOrEmpty(this.secret)) {
/*  83 */           throw new ConfigureException("configure secret not found");
/*     */         }
/*     */         
/*  86 */         this.retry = Integer.parseInt(configure.getProperty("client.max-error-retry").trim());
/*  87 */         this.maxConnection = Integer.parseInt(configure.getProperty("client.max-connection").trim());
/*  88 */         this.isLog = Integer.parseInt(configure.getProperty("isLog").trim());
/*     */         
/*  90 */         if (this.retry <= 1) {
/*  91 */           throw new ConfigureException("configure retry must > 2");
/*     */         }
/*     */         
/*  94 */         if (this.retry > 5) {
/*  95 */           throw new ConfigureException("configure retry must <= 5");
/*     */         }
/*     */         
/*  98 */         if (this.maxConnection < 1) {
/*  99 */           throw new ConfigureException("configure maxConnection must > 0");
/*     */         }
/*     */         
/* 102 */         if (this.maxConnection > 10) {
/* 103 */           throw new ConfigureException("configure maxConnection must <= 10");
/*     */         }
/*     */ 
/*     */         
/* 107 */         this.endpoint = configure.getProperty("service.endpoint").trim();
/* 108 */         this.endpoint = this.endpoint.trim();
/*     */         
/* 110 */         URL url = new URL(this.endpoint);
/* 111 */         this.protocol = url.getProtocol();
/* 112 */         this.host = url.getHost();
/* 113 */         this.port = url.getPort();
/*     */         
/* 115 */         this.protocol = this.protocol.trim();
/* 116 */         this.storePathDir = this.storePathDir.trim();
/* 117 */         this.systemId = this.systemId.trim();
/* 118 */         this.secret = this.secret.trim();
/* 119 */       } catch (IOException e) {
/* 120 */         e.printStackTrace();
/*     */       } 
/* 122 */     } catch (FileNotFoundException e1) {
/* 123 */       throw new ConfigureException("File config 'collaboration.properties' errors");
/*     */     } 
/*     */   }
/*     */   
/*     */   public VnptProperties(String endpoint, String systemId, String secret, String storePathDir, int maxConnection, int retry) {
/* 128 */     if (StringUtils.isNullOrEmpty(systemId)) {
/* 129 */       throw new ConfigureException("configure systemId not found");
/*     */     }
/*     */     
/* 132 */     if (StringUtils.isNullOrEmpty(endpoint)) {
/* 133 */       throw new ConfigureException("configure endpoint not found");
/*     */     }
/*     */     
/* 136 */     if (StringUtils.isNullOrEmpty(secret)) {
/* 137 */       throw new ConfigureException("configure secret not found");
/*     */     }
/*     */     
/* 140 */     if (StringUtils.isNullOrEmpty(storePathDir)) {
/* 141 */       throw new ConfigureException("configure storePathDir not found");
/*     */     }
/*     */     
/* 144 */     if (retry <= 1) {
/* 145 */       throw new ConfigureException("configure retry must > 2");
/*     */     }
/*     */     
/* 148 */     if (retry > 5) {
/* 149 */       throw new ConfigureException("configure retry must <= 5");
/*     */     }
/*     */     
/* 152 */     if (maxConnection < 1) {
/* 153 */       throw new ConfigureException("configure maxConnection must > 0");
/*     */     }
/*     */     
/* 156 */     if (maxConnection > 10) {
/* 157 */       throw new ConfigureException("configure maxConnection must <= 10");
/*     */     }
/*     */     
/* 160 */     this.endpoint = endpoint;
/* 161 */     this.systemId = systemId;
/* 162 */     this.secret = secret;
/* 163 */     this.storePathDir = storePathDir;
/* 164 */     this.maxConnection = maxConnection;
/* 165 */     this.retry = retry;
/*     */     
/*     */     try {
/* 168 */       URL url = new URL(endpoint);
/* 169 */       this.protocol = url.getProtocol();
/* 170 */       this.host = url.getHost();
/* 171 */       this.port = url.getPort();
/* 172 */     } catch (MalformedURLException e) {
/* 173 */       throw new ConfigureException("configure endpoint not found");
/*     */     } 
/*     */   }
/*     */   
/*     */   public VnptProperties(String endpoint, String systemId, String secret, String storePathDir, int maxConnection, int retry, String logError, int isLog) {
/* 178 */     if (StringUtils.isNullOrEmpty(systemId)) {
/* 179 */       throw new ConfigureException("configure systemId not found");
/*     */     }
/*     */     
/* 182 */     if (StringUtils.isNullOrEmpty(endpoint)) {
/* 183 */       throw new ConfigureException("configure endpoint not found");
/*     */     }
/*     */     
/* 186 */     if (StringUtils.isNullOrEmpty(secret)) {
/* 187 */       throw new ConfigureException("configure secret not found");
/*     */     }
/*     */     
/* 190 */     if (StringUtils.isNullOrEmpty(storePathDir)) {
/* 191 */       throw new ConfigureException("configure storePathDir not found");
/*     */     }
/*     */     
/* 194 */     if (StringUtils.isNullOrEmpty(logError)) {
/* 195 */       throw new ConfigureException("configure logError not found");
/*     */     }
/* 197 */     File file = new File(logError);
/* 198 */     boolean isDirectory = file.isDirectory();
/* 199 */     if (!isDirectory) {
/* 200 */       throw new ConfigureException("log-error folder not found");
/*     */     }
/*     */ 
/*     */     
/* 204 */     if (retry <= 1) {
/* 205 */       throw new ConfigureException("configure retry must > 2");
/*     */     }
/*     */     
/* 208 */     if (retry > 5) {
/* 209 */       throw new ConfigureException("configure retry must <= 5");
/*     */     }
/*     */     
/* 212 */     if (maxConnection < 1) {
/* 213 */       throw new ConfigureException("configure maxConnection must > 0");
/*     */     }
/*     */     
/* 216 */     if (maxConnection > 10) {
/* 217 */       throw new ConfigureException("configure maxConnection must <= 10");
/*     */     }
/*     */     
/* 220 */     this.endpoint = endpoint;
/* 221 */     this.systemId = systemId;
/* 222 */     this.secret = secret;
/* 223 */     this.storePathDir = storePathDir;
/* 224 */     this.maxConnection = maxConnection;
/* 225 */     this.retry = retry;
/* 226 */     this.logError = logError;
/* 227 */     this.isLog = isLog;
/*     */     
/*     */     try {
/* 230 */       URL url = new URL(endpoint);
/* 231 */       this.protocol = url.getProtocol();
/* 232 */       this.host = url.getHost();
/* 233 */       this.port = url.getPort();
/* 234 */     } catch (MalformedURLException e) {
/* 235 */       throw new ConfigureException("configure endpoint not found");
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getEndpoint() {
/* 240 */     return this.endpoint;
/*     */   }
/*     */   
/*     */   public void setEndpoint(String endpoint) {
/* 244 */     this.endpoint = endpoint;
/*     */   }
/*     */   
/*     */   public String getProtocol() {
/* 248 */     return this.protocol;
/*     */   }
/*     */   
/*     */   public void setProtocol(String protocol) {
/* 252 */     this.protocol = protocol;
/*     */   }
/*     */   
/*     */   public String getHost() {
/* 256 */     return this.host;
/*     */   }
/*     */   
/*     */   public void setHost(String host) {
/* 260 */     this.host = host;
/*     */   }
/*     */   
/*     */   public int getPort() {
/* 264 */     return this.port;
/*     */   }
/*     */   
/*     */   public void setPort(int port) {
/* 268 */     this.port = port;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/* 272 */     return this.systemId;
/*     */   }
/*     */   
/*     */   public void setSystemId(String systemId) {
/* 276 */     this.systemId = systemId;
/*     */   }
/*     */   
/*     */   public String getSecret() {
/* 280 */     return this.secret;
/*     */   }
/*     */   
/*     */   public void setSecret(String secret) {
/* 284 */     this.secret = secret;
/*     */   }
/*     */   
/*     */   public String getStorePathDir() {
/* 288 */     return this.storePathDir;
/*     */   }
/*     */   
/*     */   public void setStorePathDir(String storePathDir) {
/* 292 */     this.storePathDir = storePathDir;
/*     */   }
/*     */   
/*     */   public int getRetry() {
/* 296 */     return this.retry;
/*     */   }
/*     */   
/*     */   public void setRetry(int retry) {
/* 300 */     this.retry = retry;
/*     */   }
/*     */   
/*     */   public int getMaxConnection() {
/* 304 */     return this.maxConnection;
/*     */   }
/*     */   
/*     */   public void setMaxConnection(int maxConnection) {
/* 308 */     this.maxConnection = maxConnection;
/*     */   }
/*     */   
/*     */   public String getLogError() {
/* 312 */     return this.logError;
/*     */   }
/*     */   
/*     */   public void setLogError(String logError) {
/* 316 */     this.logError = logError;
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\VnptProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */