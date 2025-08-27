/*     */ package com.vpcp.services;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.List;
/*     */ import org.apache.http.Header;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultipartUtility
/*     */ {
/*     */   private final String boundary;
/*     */   private static final String LINE_FEED = "\r\n";
/*     */   private HttpURLConnection httpConn;
/*     */   private String charset;
/*     */   private OutputStream outputStream;
/*     */   private PrintWriter writer;
/*     */   
/*     */   public MultipartUtility(String requestURL, String charset, List<Header> headers) throws IOException {
/*  36 */     this.charset = charset;
/*     */     
/*  38 */     this.boundary = "===" + System.currentTimeMillis() + "===";
/*     */     
/*  40 */     URL url = new URL(requestURL);
/*  41 */     this.httpConn = (HttpURLConnection)url.openConnection();
/*  42 */     this.httpConn.setUseCaches(false);
/*  43 */     this.httpConn.setDoOutput(true);
/*  44 */     this.httpConn.setDoInput(true);
/*  45 */     this.httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.boundary);
/*  46 */     for (Header header : headers) {
/*  47 */       System.out.println(String.valueOf(header.getName()) + ":" + header.getValue());
/*  48 */       this.httpConn.setRequestProperty(header.getName(), header.getValue());
/*     */     } 
/*     */     
/*  51 */     this.outputStream = this.httpConn.getOutputStream();
/*  52 */     this.writer = new PrintWriter(new OutputStreamWriter(this.outputStream, charset), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFormField(String name, String value) {
/*  62 */     this.writer.append("--" + this.boundary).append("\r\n");
/*  63 */     this.writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append("\r\n");
/*  64 */     this.writer.append("Content-Type: text/plain; charset=" + this.charset).append("\r\n");
/*  65 */     this.writer.append("\r\n");
/*  66 */     this.writer.append(value).append("\r\n");
/*  67 */     this.writer.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFilePart(String fieldName, File uploadFile) throws IOException {
/*  78 */     FileInputStream inputStream = new FileInputStream(uploadFile);
/*  79 */     byte[] buffer = new byte[4096];
/*  80 */     int bytesRead = -1;
/*  81 */     while ((bytesRead = inputStream.read(buffer)) != -1) {
/*  82 */       this.outputStream.write(buffer, 0, bytesRead);
/*     */     }
/*  84 */     this.outputStream.flush();
/*  85 */     inputStream.close();
/*     */     
/*  87 */     this.writer.append("\r\n");
/*  88 */     this.writer.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInputStreamPart(String fieldName, InputStream inputStream) throws IOException {
/*  99 */     byte[] buffer = new byte[4096];
/* 100 */     int bytesRead = -1;
/* 101 */     while ((bytesRead = inputStream.read(buffer)) != -1) {
/* 102 */       this.outputStream.write(buffer, 0, bytesRead);
/*     */     }
/* 104 */     this.outputStream.flush();
/* 105 */     inputStream.close();
/*     */     
/* 107 */     this.writer.append("\r\n");
/* 108 */     this.writer.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addHeaderField(String name, String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String finish() throws IOException {
/* 128 */     StringBuffer response = new StringBuffer();
/*     */     
/* 130 */     int status = this.httpConn.getResponseCode();
/* 131 */     String inputEncoding = "US-ASCII";
/* 132 */     if (status == 200 || status == 201) {
/* 133 */       BufferedReader reader = new BufferedReader(new InputStreamReader(this.httpConn.getInputStream(), Charset.forName(inputEncoding)));
/* 134 */       String line = null;
/*     */       try {
/* 136 */         while ((line = reader.readLine()) != null) {
/* 137 */           System.out.println("line---------->" + line);
/* 138 */           response.append(line);
/*     */         }
/*     */       
/* 141 */       } catch (Exception e) {
/* 142 */         e.printStackTrace();
/*     */       } finally {
/*     */         
/* 145 */         reader.close();
/* 146 */         this.httpConn.disconnect();
/*     */       } 
/*     */     } else {
/* 149 */       BufferedReader reader = new BufferedReader(new InputStreamReader(this.httpConn.getErrorStream()));
/* 150 */       String line = null;
/* 151 */       while ((line = reader.readLine()) != null) {
/* 152 */         response.append(line);
/*     */       }
/* 154 */       reader.close();
/* 155 */       this.httpConn.disconnect();
/*     */     } 
/* 157 */     System.out.println(response.toString());
/* 158 */     return response.toString();
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\MultipartUtility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */