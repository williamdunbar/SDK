/*     */ package com.vpcp.services;
/*     */ 
/*     */ import com.vpcp.services.model.GetEdocResult;
/*     */ import com.vpcp.services.utils.FileUtil;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.NameValuePair;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*     */ import org.apache.http.client.methods.CloseableHttpResponse;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.client.methods.HttpUriRequest;
/*     */ import org.apache.http.client.utils.URIBuilder;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.entity.StringEntity;
/*     */ import org.apache.http.entity.mime.HttpMultipartMode;
/*     */ import org.apache.http.entity.mime.MultipartEntityBuilder;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.message.BasicNameValuePair;
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
/*     */ 
/*     */ public class HttpConnection
/*     */ {
/*     */   private String url;
/*     */   private String method;
/*     */   private VnptProperties properties;
/*     */   
/*     */   public HttpConnection(VnptProperties properties, String url, String method) {
/*  54 */     this.properties = properties;
/*  55 */     this.url = url;
/*  56 */     this.method = method;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String sendGet(HashMap<String, String> paras, List<Header> headers) throws Exception {
/*  61 */     HttpClientFactory factory = new HttpClientFactory(this.properties);
/*  62 */     HttpClient client = factory.getHttpsClient(headers);
/*     */     
/*  64 */     URIBuilder builder = new URIBuilder(this.url);
/*  65 */     if (paras != null) {
/*  66 */       Iterator<String> iterator = paras.keySet().iterator();
/*  67 */       while (iterator.hasNext()) {
/*  68 */         String key = iterator.next();
/*  69 */         System.out.println(paras.get(key));
/*  70 */         builder.setParameter(key, paras.get(key));
/*     */       } 
/*     */     } 
/*  73 */     URI uri = builder.build();
/*  74 */     HttpGet httpget = new HttpGet(uri);
/*  75 */     System.out.println(httpget.getURI());
/*     */     
/*  77 */     HttpGet request = new HttpGet(httpget.getURI());
/*  78 */     HttpResponse response = client.execute((HttpUriRequest)request);
/*     */     
/*  80 */     System.out.println("\nSending 'GET' request to URL : " + this.url);
/*  81 */     System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
/*  82 */     BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
/*     */     
/*  84 */     StringBuffer result = new StringBuffer();
/*  85 */     String line = "";
/*  86 */     while ((line = rd.readLine()) != null) {
/*  87 */       result.append(line);
/*     */     }
/*     */     
/*  90 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendPost(HashMap<String, String> paras, List<Header> headers) throws Exception {
/*  96 */     HttpClientFactory factory = new HttpClientFactory(this.properties);
/*  97 */     HttpClient client = factory.getHttpsClient(headers);
/*  98 */     StringBuffer result = new StringBuffer();
/*  99 */     HttpPost post = new HttpPost(this.url);
/*     */     
/* 101 */     List<NameValuePair> urlParameters = new ArrayList<>();
/* 102 */     if (paras != null) {
/* 103 */       Iterator<String> iterator = paras.keySet().iterator();
/* 104 */       while (iterator.hasNext()) {
/* 105 */         String key = iterator.next();
/* 106 */         urlParameters.add(new BasicNameValuePair(key, paras.get(key)));
/*     */       } 
/*     */     } 
/*     */     try {
/* 110 */       post.setEntity((HttpEntity)new UrlEncodedFormEntity(urlParameters));
/* 111 */       HttpResponse response = client.execute((HttpUriRequest)post);
/* 112 */       System.out.println("\nSending 'POST' request to URL : " + this.url);
/* 113 */       System.out.println("-->Post parameters : " + post.getEntity());
/* 114 */       System.out.println("-->Response Code : " + response.getStatusLine().getStatusCode());
/* 115 */       BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
/* 116 */       String line = "";
/* 117 */       while ((line = rd.readLine()) != null) {
/* 118 */         result.append(line);
/*     */       }
/* 120 */     } catch (UnsupportedEncodingException e) {
/* 121 */       e.printStackTrace();
/* 122 */     } catch (ClientProtocolException e) {
/* 123 */       e.printStackTrace();
/* 124 */     } catch (IOException e) {
/* 125 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 128 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String sendPostMultipartFileDoc(HashMap<String, String> paras, List<Header> headers, InputStream content) throws Exception {
/* 134 */     String charset = "UTF-8";
/*     */     
/* 136 */     URIBuilder uri = new URIBuilder(this.url);
/* 137 */     if (paras != null) {
/* 138 */       Iterator<String> iterator = paras.keySet().iterator();
/* 139 */       while (iterator.hasNext()) {
/* 140 */         String key = iterator.next();
/* 141 */         uri.setParameter(key, paras.get(key));
/* 142 */         System.out.println("para:-->" + key + ":" + (String)paras.get(key));
/*     */       } 
/*     */     } 
/*     */     
/* 146 */     for (Header header : headers) {
/* 147 */       System.out.println("header:-->" + header.getName() + ":" + header.getValue());
/*     */     }
/* 149 */     MultipartUtility multipart = new MultipartUtility(uri.toString(), charset, headers);
/*     */     
/* 151 */     multipart.addInputStreamPart("file_doc", content);
/* 152 */     String response = multipart.finish();
/* 153 */     System.out.println("response: " + response);
/* 154 */     return response;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendPostMultipart(HashMap<String, String> paras, List<Header> headers, InputStream content) throws Exception {
/* 160 */     HttpClientFactory factory = new HttpClientFactory(this.properties);
/* 161 */     CloseableHttpClient client = (CloseableHttpClient)factory.getHttpsClient(headers);
/* 162 */     MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
/* 163 */     multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
/* 164 */     URIBuilder uri = new URIBuilder(this.url);
/*     */     
/* 166 */     if (paras != null) {
/* 167 */       Iterator<String> iterator = paras.keySet().iterator();
/* 168 */       while (iterator.hasNext()) {
/* 169 */         String key = iterator.next();
/* 170 */         uri.setParameter(key, paras.get(key));
/*     */       } 
/*     */     } 
/* 173 */     StringEntity stringEntity = new StringEntity("{}");
/* 174 */     HttpPost httpPost = new HttpPost(uri.build());
/* 175 */     httpPost.setEntity((HttpEntity)stringEntity);
/* 176 */     CloseableHttpResponse closeableHttpResponse = client.execute((HttpUriRequest)httpPost);
/*     */     
/* 178 */     System.out.println("\nSending 'POST' request to URL : " + this.url);
/* 179 */     System.out.println("Post  parameters : " + closeableHttpResponse.getEntity());
/* 180 */     System.out.println("Response Code : " + closeableHttpResponse.getStatusLine().getStatusCode());
/*     */     
/* 182 */     BufferedReader rd = new BufferedReader(new InputStreamReader(closeableHttpResponse.getEntity().getContent(), "UTF-8"));
/*     */ 
/*     */ 
/*     */     
/* 186 */     StringBuffer result = new StringBuffer();
/* 187 */     String line = "";
/*     */     
/* 189 */     while ((line = rd.readLine()) != null)
/*     */     {
/* 191 */       result.append(line);
/*     */     }
/* 193 */     client.close();
/* 194 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public GetEdocResult download(HashMap<String, String> paras, List<Header> headers, String docId, String filefolder) throws Exception {
/* 199 */     GetEdocResult getEdocResult = new GetEdocResult();
/* 200 */     getEdocResult.setData("");
/* 201 */     getEdocResult.setErrorCode("100");
/* 202 */     getEdocResult.setFilePath("");
/* 203 */     getEdocResult.setStatus("FAIL");
/*     */     try {
/* 205 */       HttpClientFactory factory = new HttpClientFactory(this.properties);
/* 206 */       CloseableHttpClient client = (CloseableHttpClient)factory.getHttpsClient(headers);
/* 207 */       MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
/* 208 */       multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
/* 209 */       URIBuilder uri = new URIBuilder(this.url);
/*     */       
/* 211 */       if (paras != null) {
/* 212 */         Iterator<String> iterator = paras.keySet().iterator();
/* 213 */         while (iterator.hasNext()) {
/* 214 */           String key = iterator.next();
/* 215 */           uri.setParameter(key, paras.get(key));
/*     */         } 
/*     */       } 
/*     */       
/* 219 */       int year = Calendar.getInstance().get(1);
/* 220 */       int month = Calendar.getInstance().get(2) + 1;
/* 221 */       String filePath = String.valueOf(filefolder) + "/" + year + "/" + month;
/* 222 */       if (!FileUtil.exist(filePath)) {
/* 223 */         FileUtil.mkdirs(filePath);
/*     */       }
/* 225 */       filePath = String.valueOf(filePath) + "/" + docId + ".edxml";
/* 226 */       File fileDownload = new File(filePath);
/* 227 */       HttpGet httpGet = new HttpGet(uri.build());
/* 228 */       CloseableHttpResponse closeableHttpResponse = client.execute((HttpUriRequest)httpGet);
/* 229 */       InputStream is = closeableHttpResponse.getEntity().getContent();
/* 230 */       FileOutputStream fos = new FileOutputStream(fileDownload);
/*     */       int inByte;
/* 232 */       while ((inByte = is.read()) != -1) {
/* 233 */         fos.write(inByte);
/*     */       }
/* 235 */       is.close();
/* 236 */       fos.flush();
/* 237 */       fos.close();
/*     */       
/* 239 */       if (fileDownload.exists() && fileDownload.length() > 0L) {
/* 240 */         getEdocResult = new GetEdocResult();
/* 241 */         getEdocResult.setData("");
/* 242 */         getEdocResult.setErrorCode("0");
/* 243 */         getEdocResult.setFilePath(filePath);
/* 244 */         getEdocResult.setStatus("SUCCESS");
/*     */       }
/*     */     
/* 247 */     } catch (Exception e) {
/* 248 */       getEdocResult = new GetEdocResult();
/* 249 */       getEdocResult.setData("");
/* 250 */       getEdocResult.setErrorCode("100");
/* 251 */       getEdocResult.setFilePath("");
/* 252 */       getEdocResult.setStatus("FAIL");
/*     */     } 
/*     */     
/* 255 */     return getEdocResult;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendPostMultipart(HashMap<String, String> paras, List<Header> headers, String content) throws Exception {
/* 261 */     HttpClientFactory factory = new HttpClientFactory(this.properties);
/* 262 */     CloseableHttpClient client = (CloseableHttpClient)factory.getHttpsClient(headers);
/*     */     
/* 264 */     MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
/* 265 */     multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
/* 266 */     URIBuilder uri = new URIBuilder(this.url);
/* 267 */     if (paras != null) {
/* 268 */       Iterator<String> iterator = paras.keySet().iterator();
/* 269 */       while (iterator.hasNext()) {
/* 270 */         String key = iterator.next();
/* 271 */         uri.setParameter(key, paras.get(key));
/*     */       } 
/*     */     } 
/*     */     
/* 275 */     if (content != null) {
/* 276 */       multipartEntityBuilder.addBinaryBody("content", content.getBytes());
/*     */     }
/* 278 */     HttpEntity entity = multipartEntityBuilder.build();
/* 279 */     HttpPost httpPost = new HttpPost(uri.build());
/* 280 */     httpPost.setEntity(entity);
/* 281 */     CloseableHttpResponse closeableHttpResponse = client.execute((HttpUriRequest)httpPost);
/*     */     
/* 283 */     System.out.println("\nSending 'POST' request to URL : " + this.url);
/* 284 */     System.out.println("Post parameters : " + closeableHttpResponse.getEntity());
/* 285 */     System.out.println("Response Code : " + closeableHttpResponse.getStatusLine().getStatusCode());
/*     */     
/* 287 */     BufferedReader rd = new BufferedReader(new InputStreamReader(closeableHttpResponse.getEntity().getContent(), "UTF-8"));
/*     */     
/* 289 */     StringBuffer result = new StringBuffer();
/* 290 */     String line = "";
/* 291 */     while ((line = rd.readLine()) != null) {
/* 292 */       result.append(line);
/*     */     }
/*     */     
/* 295 */     client.close();
/* 296 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String sendPostJson(HashMap<String, String> paras, List<Header> headers, String json) throws Exception {
/* 300 */     HttpClientFactory factory = new HttpClientFactory(this.properties);
/* 301 */     CloseableHttpClient client = (CloseableHttpClient)factory.getHttpsClient(headers);
/*     */     
/* 303 */     URIBuilder uri = new URIBuilder(this.url);
/* 304 */     if (paras != null) {
/* 305 */       Iterator<String> iterator = paras.keySet().iterator();
/* 306 */       while (iterator.hasNext()) {
/* 307 */         String key = iterator.next();
/* 308 */         uri.setParameter(key, paras.get(key));
/*     */       } 
/*     */     } 
/*     */     
/* 312 */     HttpPost httpPost = new HttpPost(uri.build());
/* 313 */     httpPost.setEntity((HttpEntity)new StringEntity(json, ContentType.APPLICATION_JSON));
/*     */     
/* 315 */     CloseableHttpResponse closeableHttpResponse = client.execute((HttpUriRequest)httpPost);
/*     */     
/* 317 */     System.out.println("\nSending 'POST' request to URL : " + this.url);
/* 318 */     System.out.println("Post parameters : " + closeableHttpResponse.getEntity());
/* 319 */     System.out.println("Response Code : " + closeableHttpResponse.getStatusLine().getStatusCode());
/*     */     
/* 321 */     BufferedReader rd = new BufferedReader(new InputStreamReader(closeableHttpResponse.getEntity().getContent(), "UTF-8"));
/*     */     
/* 323 */     StringBuffer result = new StringBuffer();
/* 324 */     String line = "";
/* 325 */     while ((line = rd.readLine()) != null) {
/* 326 */       result.append(line);
/*     */     }
/*     */     
/* 329 */     client.close();
/* 330 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String execute(HashMap<String, String> paras, List<Header> headers) throws Exception {
/* 334 */     String result = "";
/* 335 */     if ("GET".equals(getMethod())) {
/* 336 */       result = sendGet(paras, headers);
/*     */     } else {
/* 338 */       result = sendPost(paras, headers);
/*     */     } 
/* 340 */     return result;
/*     */   }
/*     */   
/*     */   public String execute(HashMap<String, String> paras, List<Header> headers, InputStream content) throws Exception {
/* 344 */     String result = "";
/* 345 */     result = sendPostMultipart(paras, headers, content);
/* 346 */     return result;
/*     */   }
/*     */   
/*     */   public String execute(HashMap<String, String> paras, List<Header> headers, String content) throws Exception {
/* 350 */     String result = "";
/* 351 */     result = sendPostMultipart(paras, headers, content);
/* 352 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String executeSendFileDoc(HashMap<String, String> paras, List<Header> headers, InputStream content) throws Exception {
/* 357 */     String result = "";
/* 358 */     result = sendPostMultipartFileDoc(paras, headers, content);
/* 359 */     return result;
/*     */   }
/*     */   
/*     */   public String getUrl() {
/* 363 */     return this.url;
/*     */   }
/*     */   
/*     */   public void setUrl(String url) {
/* 367 */     this.url = url;
/*     */   }
/*     */   
/*     */   public String getMethod() {
/* 371 */     return this.method;
/*     */   }
/*     */   
/*     */   public void setMethod(String method) {
/* 375 */     this.method = method;
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\HttpConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */