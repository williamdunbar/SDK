/*     */ package com.vpcp.services;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.vpcp.services.exception.ConfigureException;
/*     */ import com.vpcp.services.model.GetEdocResult;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.crypto.Mac;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.commons.codec.binary.Hex;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.message.BasicHeader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class VnptService
/*     */ {
/*  33 */   protected String authorization = "";
/*  34 */   protected String user_Agent = "JAVASDKAgent/1.0.0 XRoad/VPCP";
/*  35 */   protected String version = "3.0.201608";
/*  36 */   protected String accept = "application/xml";
/*  37 */   protected String content_Type = "application/json";
/*     */   
/*  39 */   protected String protocol = "HTTP";
/*  40 */   protected String application = "";
/*  41 */   protected String endpoint = "";
/*  42 */   protected String storePathDir = "";
/*  43 */   protected String systemId = "";
/*  44 */   protected String secret = "";
/*     */   
/*     */   protected HashMap<String, String> headers;
/*     */   HttpConnection httpConnection;
/*     */   protected VnptProperties properties;
/*     */   
/*     */   public VnptService(VnptProperties properties) {
/*  51 */     if (properties == null) {
/*  52 */       throw new ConfigureException("VnptProperties must initialization");
/*     */     }
/*  54 */     this.headers = new HashMap<>();
/*  55 */     Date d = new Date();
/*  56 */     this.headers.put("timestamp", (new SimpleDateFormat("ddMMyyyy'T'HHmmss+SSSS")).format(d));
/*  57 */     this.properties = properties;
/*  58 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*  62 */     if (this.properties != null) {
/*  63 */       this.protocol = this.properties.getProtocol();
/*  64 */       this.endpoint = this.properties.getEndpoint();
/*  65 */       this.storePathDir = this.properties.getStorePathDir();
/*  66 */       this.systemId = this.properties.getSystemId();
/*  67 */       this.secret = this.properties.getSecret();
/*  68 */       this.headers.put("systemid", this.systemId);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createHttpConnection(String urlRequest, String method) {
/*  73 */     this.httpConnection = new HttpConnection(this.properties, urlRequest, method);
/*     */   }
/*     */ 
/*     */   
/*     */   public String execute(String urlRequest, String method, HashMap<String, String> paras, List<Header> headers) throws Exception {
/*  78 */     createHttpConnection(urlRequest, method);
/*  79 */     String content = this.httpConnection.execute(paras, addXroadVnptHashHeader());
/*  80 */     return content;
/*     */   }
/*     */ 
/*     */   
/*     */   public String execute(String urlRequest, String method, HashMap<String, String> paras, List<Header> headers, InputStream content) throws Exception {
/*  85 */     createHttpConnection(urlRequest, method);
/*  86 */     String result = this.httpConnection.execute(paras, addXroadVnptHashHeader(), content);
/*  87 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String execute(String urlRequest, String method, HashMap<String, String> paras, List<Header> headers, String content) throws Exception {
/*  93 */     createHttpConnection(urlRequest, method);
/*  94 */     String result = this.httpConnection.execute(paras, addXroadVnptHashHeader(), content);
/*  95 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String executeSendFileDoc(String urlRequest, String method, HashMap<String, String> paras, List<Header> headers, InputStream content) throws Exception {
/* 101 */     createHttpConnection(urlRequest, method);
/* 102 */     String result = this.httpConnection.executeSendFileDoc(paras, addXroadVnptHashHeader(), content);
/* 103 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String executeJson(String urlRequest, String method, HashMap<String, String> paras, List<Header> headers, String content) throws Exception {
/* 108 */     createHttpConnection(urlRequest, method);
/* 109 */     String result = this.httpConnection.sendPostJson(paras, addXroadVnptHashHeader(), content);
/* 110 */     return result;
/*     */   }
/*     */   
/*     */   public GetEdocResult dowload(String urlRequest, HashMap<String, String> paras, List<Header> headers, String docId, String filefolder) {
/* 114 */     createHttpConnection(urlRequest, "GET");
/* 115 */     GetEdocResult result = null;
/*     */     try {
/* 117 */       result = this.httpConnection.download(paras, addXroadVnptHashHeader(), docId, filefolder);
/* 118 */     } catch (Exception e) {
/*     */       
/* 120 */       e.printStackTrace();
/*     */     } 
/* 122 */     return result;
/*     */   }
/*     */   
/*     */   public List<Header> getListHeader() {
/* 126 */     List<Header> liHeaders = new ArrayList<>();
/* 127 */     Iterator<String> keys = this.headers.keySet().iterator();
/* 128 */     while (keys.hasNext()) {
/* 129 */       String key = keys.next();
/* 130 */       String value = this.headers.get(key);
/* 131 */       liHeaders.add(new BasicHeader(key, value));
/* 132 */       System.out.println(String.valueOf(key) + ":" + value);
/*     */     } 
/* 134 */     return liHeaders;
/*     */   }
/*     */   
/*     */   private List<Header> addXroadVnptHashHeader() {
/* 138 */     List<Header> tmp = new ArrayList<>();
/* 139 */     String vnptHeader = "";
/* 140 */     String vnptHeaderKey = "";
/* 141 */     JsonObject jsonObject = new JsonObject();
/*     */     
/* 143 */     List<Header> lisHeaders = getListHeader();
/* 144 */     for (Header header : lisHeaders) {
/* 145 */       if (header.getName().equals("Content-Type")) {
/* 146 */         tmp.add(new BasicHeader(header.getName(), header.getValue()));
/*     */       }
/* 148 */       jsonObject.addProperty("XroadVnpt-" + header.getName(), header.getValue());
/* 149 */       tmp.add(new BasicHeader("XroadVnpt-" + header.getName(), header.getValue()));
/* 150 */       vnptHeaderKey = String.valueOf(vnptHeaderKey) + header.getName() + ";";
/*     */     } 
/* 152 */     if (vnptHeaderKey != null && vnptHeaderKey.endsWith(";"))
/* 153 */       vnptHeaderKey = vnptHeaderKey.substring(0, vnptHeaderKey.length() - 1); 
/* 154 */     vnptHeader = jsonObject.toString();
/* 155 */     System.out.println("vnptHeader:" + vnptHeader);
/*     */     try {
/* 157 */       Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
/* 158 */       SecretKeySpec secret_key = new SecretKeySpec(this.secret.getBytes("UTF-8"), "HmacSHA256");
/* 159 */       sha256_HMAC.init(secret_key);
/* 160 */       String hash = Hex.encodeHexString(sha256_HMAC.doFinal(vnptHeader.getBytes("UTF-8")));
/* 161 */       System.out.println("XroadVnpt-sign-header-key: " + vnptHeaderKey);
/* 162 */       System.out.println("XroadVnpt-sign-header:" + vnptHeader);
/* 163 */       System.out.println("XroadVnpt-hash-header: " + hash);
/* 164 */       tmp.add(new BasicHeader("XroadVnpt-sign-header-key", vnptHeaderKey));
/* 165 */       tmp.add(new BasicHeader("XroadVnpt-sign-header", vnptHeader));
/* 166 */       tmp.add(new BasicHeader("XroadVnpt-hash-header", hash));
/* 167 */       tmp.add(new BasicHeader("User-Agent", this.user_Agent));
/* 168 */       tmp.add(new BasicHeader("XroadVnpt-version", this.version));
/* 169 */       return tmp;
/* 170 */     } catch (UnsupportedEncodingException e) {
/* 171 */       e.printStackTrace();
/* 172 */     } catch (NoSuchAlgorithmException e) {
/* 173 */       e.printStackTrace();
/* 174 */     } catch (InvalidKeyException e) {
/* 175 */       e.printStackTrace();
/*     */     } 
/* 177 */     return null;
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\VnptService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */