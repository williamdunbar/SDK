/*     */ package com.vpcp.services;
/*     */ 
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.vpcp.services.model.CountReceiveEdoc;
/*     */ import com.vpcp.services.model.GetChangeStatusResult;
/*     */ import com.vpcp.services.model.GetEdocResult;
/*     */ import com.vpcp.services.model.GetReceivedEdocResult;
/*     */ import com.vpcp.services.model.GetSendEdocResult;
/*     */ import com.vpcp.services.model.Knobstick;
/*     */ import com.vpcp.services.model.SendEdocResult;
/*     */ import com.vpcp.services.model.StatusResult;
/*     */ import com.vpcp.services.utils.FileUtil;
/*     */ import com.vpcp.services.utils.StringUtils;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpUriRequest;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.impl.client.HttpClients;
/*     */ 
/*     */ public class KnobstickServiceImp
/*     */   extends VnptService
/*     */   implements KnobstickService {
/*     */   public KnobstickServiceImp(VnptProperties properties) {
/*  44 */     super(properties);
/*     */   }
/*     */   
/*     */   public GetReceivedEdocResult getReceivedEdocList(String jsonHeaders) {
/*  48 */     String urlRequest = String.valueOf(this.endpoint) + "/getReceivedEdocList";
/*  49 */     this.headers.put("Content-Type", "application/json");
/*  50 */     GetReceivedEdocResult receivedEdocResult = null;
/*  51 */     String json = "";
/*     */     try {
/*  53 */       JsonParser parser = new JsonParser();
/*  54 */       JsonElement element = parser.parse(jsonHeaders);
/*  55 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/*  56 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/*  57 */       while (iteratorJE.hasNext()) {
/*  58 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/*  59 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*  61 */       json = execute(urlRequest, "POST", null, getListHeader());
/*  62 */       System.out.println("json" + json);
/*  63 */       parser = new JsonParser();
/*     */       try {
/*  65 */         element = parser.parse(json);
/*  66 */       } catch (Exception e) {
/*     */         
/*  68 */         element = null;
/*     */       } 
/*     */ 
/*     */       
/*  72 */       if (element != null) {
/*  73 */         receivedEdocResult = new GetReceivedEdocResult();
/*  74 */         String status = element.getAsJsonObject().get("status").getAsString();
/*  75 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/*  76 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/*  78 */         receivedEdocResult.setErrorCode(errorCode);
/*  79 */         receivedEdocResult.setErrorDesc(errorDesc);
/*  80 */         receivedEdocResult.setStatus(status);
/*     */         
/*  82 */         if ("0".equalsIgnoreCase(errorCode)) {
/*  83 */           JsonArray data = element.getAsJsonObject().get("data").getAsJsonArray();
/*  84 */           List<Knobstick> knobsticks = new ArrayList<>();
/*  85 */           if (data != null) {
/*  86 */             Iterator<JsonElement> iterator = data.iterator();
/*  87 */             while (iterator.hasNext()) {
/*  88 */               JsonElement elementData = iterator.next();
/*  89 */               Knobstick knobstick = new Knobstick();
/*  90 */               if (elementData.getAsJsonObject().get("docId") != null) {
/*  91 */                 knobstick.setId(elementData.getAsJsonObject().get("docId").getAsString());
/*     */               }
/*  93 */               if (elementData.getAsJsonObject().get("serviceType") != null) {
/*  94 */                 knobstick
/*  95 */                   .setServiceType(elementData.getAsJsonObject().get("serviceType").getAsString());
/*     */               }
/*  97 */               if (elementData.getAsJsonObject().get("messageType") != null) {
/*  98 */                 knobstick
/*  99 */                   .setMessageType(elementData.getAsJsonObject().get("messageType").getAsString());
/*     */               }
/* 101 */               if (elementData.getAsJsonObject().get("created_time") != null) {
/* 102 */                 knobstick.setCreatedTime(
/* 103 */                     elementData.getAsJsonObject().get("created_time").getAsString());
/*     */               }
/* 105 */               if (elementData.getAsJsonObject().get("updated_time") != null) {
/* 106 */                 knobstick.setUpdatedTime(
/* 107 */                     elementData.getAsJsonObject().get("updated_time").getAsString());
/*     */               }
/* 109 */               if (elementData.getAsJsonObject().get("from") != null) {
/* 110 */                 knobstick.setFrom(elementData.getAsJsonObject().get("from").getAsString());
/*     */               }
/* 112 */               if (elementData.getAsJsonObject().get("to") != null) {
/* 113 */                 knobstick.setTo(elementData.getAsJsonObject().get("to").getAsString());
/*     */               }
/* 115 */               if (elementData.getAsJsonObject().get("status") != null) {
/* 116 */                 knobstick.setStatus(elementData.getAsJsonObject().get("status").getAsString());
/*     */               }
/* 118 */               if (elementData.getAsJsonObject().get("status_desc") != null)
/* 119 */                 knobstick.setStatusDesc(elementData.getAsJsonObject().get("status_desc").getAsString()); 
/* 120 */               knobsticks.add(knobstick);
/*     */             } 
/*     */           } 
/*     */           
/* 124 */           receivedEdocResult.setKnobsticks(knobsticks);
/*     */         } 
/*     */       } else {
/* 127 */         receivedEdocResult = new GetReceivedEdocResult();
/* 128 */         List<Knobstick> knobsticks = new ArrayList<>();
/* 129 */         receivedEdocResult.setErrorCode("0");
/* 130 */         receivedEdocResult.setErrorDesc("Success");
/* 131 */         receivedEdocResult.setStatus("OK");
/* 132 */         receivedEdocResult.setKnobsticks(knobsticks);
/*     */       }
/*     */     
/* 135 */     } catch (Exception e) {
/* 136 */       if (this.properties.getIsLog() > 0) {
/*     */         try {
/* 138 */           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
/* 139 */           String date = dateFormat.format(Calendar.getInstance().getTime());
/* 140 */           String file = String.valueOf(this.properties.getLogError()) + "/" + date + ".txt";
/* 141 */           FileUtil.writeToFile(file, 
/* 142 */               "-----------" + Calendar.getInstance().getTime() + System.lineSeparator() + "------------", 
/* 143 */               true);
/* 144 */           FileUtil.writeToFile(file, "Noi dung json: " + json + System.lineSeparator(), true);
/*     */           
/* 146 */           FileUtil.writeToFile(file, String.valueOf(e.getMessage()) + System.lineSeparator(), true);
/* 147 */           FileUtil.writeToFile(file, String.valueOf(e.getStackTrace().toString()) + System.lineSeparator(), true);
/* 148 */           FileUtil.writeToFile(file, String.valueOf(e.getCause().toString()) + System.lineSeparator(), true);
/* 149 */           FileUtil.writeToFile(file, "-----------------------" + System.lineSeparator(), true);
/* 150 */         } catch (IOException e1) {
/*     */           
/* 152 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/* 155 */       e.printStackTrace();
/*     */     } 
/* 157 */     return receivedEdocResult;
/*     */   }
/*     */ 
/*     */   
/*     */   public GetSendEdocResult getSentEdocList(String jsonHeaders) {
/* 162 */     this.headers.put("Content-Type", "application/json");
/* 163 */     String urlRequest = String.valueOf(this.endpoint) + "/getSentEdocList";
/* 164 */     GetSendEdocResult getSendEdocResult = null;
/*     */     try {
/* 166 */       JsonParser parser = new JsonParser();
/* 167 */       JsonElement element = parser.parse(jsonHeaders);
/* 168 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 169 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 170 */       while (iteratorJE.hasNext()) {
/* 171 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 172 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/* 174 */       String json = execute(urlRequest, "POST", null, getListHeader());
/* 175 */       parser = new JsonParser();
/* 176 */       element = parser.parse(json);
/*     */       
/* 178 */       if (element != null) {
/* 179 */         getSendEdocResult = new GetSendEdocResult();
/* 180 */         String status = element.getAsJsonObject().get("status").getAsString();
/* 181 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 182 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/* 184 */         getSendEdocResult.setErrorCode(errorCode);
/* 185 */         getSendEdocResult.setErrorDesc(errorDesc);
/* 186 */         getSendEdocResult.setStatus(status);
/*     */         
/* 188 */         if ("0".equalsIgnoreCase(errorCode)) {
/* 189 */           JsonArray data = element.getAsJsonObject().get("data").getAsJsonArray();
/* 190 */           List<StatusResult> statusResults = new ArrayList<>();
/*     */           
/* 192 */           if (data != null) {
/* 193 */             Iterator<JsonElement> iterator = data.iterator();
/* 194 */             while (iterator.hasNext()) {
/* 195 */               JsonElement elementData = iterator.next();
/* 196 */               StatusResult statusResult = new StatusResult();
/*     */               
/* 198 */               if (elementData.getAsJsonObject().get("serviceType") != null) {
/* 199 */                 statusResult
/* 200 */                   .setServiceType(elementData.getAsJsonObject().get("serviceType").getAsString());
/*     */               }
/* 202 */               if (elementData.getAsJsonObject().get("receiverDocId") != null) {
/* 203 */                 statusResult.setReceiverDocId(
/* 204 */                     elementData.getAsJsonObject().get("receiverDocId").getAsString());
/*     */               }
/* 206 */               if (elementData.getAsJsonObject().get("toCode") != null) {
/* 207 */                 statusResult.setToCode(elementData.getAsJsonObject().get("toCode").getAsString());
/*     */               }
/* 209 */               if (elementData.getAsJsonObject().get("receiveStatus") != null) {
/* 210 */                 statusResult.setReceiveStatus(
/* 211 */                     elementData.getAsJsonObject().get("receiveStatus").getAsString());
/*     */               }
/* 213 */               if (elementData.getAsJsonObject().get("title") != null) {
/* 214 */                 statusResult.setTitle(elementData.getAsJsonObject().get("title").getAsString());
/*     */               }
/* 216 */               if (elementData.getAsJsonObject().get("messageType") != null) {
/* 217 */                 statusResult
/* 218 */                   .setMessageType(elementData.getAsJsonObject().get("messageType").getAsString());
/*     */               }
/* 220 */               if (elementData.getAsJsonObject().get("notation") != null) {
/* 221 */                 statusResult.setNotation(elementData.getAsJsonObject().get("notation").getAsString());
/*     */               }
/* 223 */               if (elementData.getAsJsonObject().get("receivedTime") != null) {
/* 224 */                 statusResult.setReceivedTime(
/* 225 */                     elementData.getAsJsonObject().get("receivedTime").getAsString());
/*     */               }
/* 227 */               if (elementData.getAsJsonObject().get("sentTime") != null) {
/* 228 */                 statusResult.setSentTime(elementData.getAsJsonObject().get("sentTime").getAsString());
/*     */               }
/* 230 */               if (elementData.getAsJsonObject().get("sendStatus") != null) {
/* 231 */                 statusResult
/* 232 */                   .setSendStatus(elementData.getAsJsonObject().get("sendStatus").getAsString());
/*     */               }
/* 234 */               if (elementData.getAsJsonObject().get("sendStatusDesc") != null) {
/* 235 */                 statusResult.setSendStatusDesc(
/* 236 */                     elementData.getAsJsonObject().get("sendStatusDesc").getAsString());
/*     */               }
/* 238 */               if (elementData.getAsJsonObject().get("senderDocId") != null) {
/* 239 */                 statusResult
/* 240 */                   .setSenderDocId(elementData.getAsJsonObject().get("senderDocId").getAsString());
/*     */               }
/* 242 */               if (elementData.getAsJsonObject().get("fromCode") != null) {
/* 243 */                 statusResult.setFromCode(elementData.getAsJsonObject().get("fromCode").getAsString());
/*     */               }
/* 245 */               if (elementData.getAsJsonObject().get("receiveStatusDesc") != null)
/* 246 */                 statusResult.setReceiveStatusDesc(
/* 247 */                     elementData.getAsJsonObject().get("receiveStatusDesc").getAsString()); 
/* 248 */               statusResults.add(statusResult);
/*     */             } 
/*     */           } 
/* 251 */           getSendEdocResult.setStatusResult(statusResults);
/*     */         }
/*     */       
/*     */       } 
/* 255 */     } catch (Exception e) {
/* 256 */       e.printStackTrace();
/*     */     } 
/* 258 */     return getSendEdocResult;
/*     */   }
/*     */   
/*     */   public SendEdocResult sendEdoc(String jsonHeaders, String edXMLFileLocation) {
/* 262 */     String urlRequest = String.valueOf(this.endpoint) + "/sendEdoc";
/* 263 */     SendEdocResult sendEdocResult = null;
/*     */     try {
/* 265 */       this.headers.put("Content-Type", "application/octet-stream");
/* 266 */       JsonParser parser = new JsonParser();
/* 267 */       JsonElement element = parser.parse(jsonHeaders);
/* 268 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 269 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 270 */       while (iteratorJE.hasNext()) {
/* 271 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 272 */         if (((String)entry.getKey()).equals("to")) {
/* 273 */           String s = ((JsonElement)entry.getValue()).toString();
/* 274 */           this.headers.put(entry.getKey(), s); continue;
/*     */         } 
/* 276 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*     */       
/* 279 */       createContentHA256(edXMLFileLocation);
/*     */       
/* 281 */       FileInputStream inputStream = new FileInputStream(edXMLFileLocation);
/* 282 */       String json = executeSendFileDoc(urlRequest, "POST", null, getListHeader(), 
/* 283 */           inputStream);
/* 284 */       parser = new JsonParser();
/* 285 */       System.out.println("out send:" + json);
/* 286 */       element = parser.parse(json);
/*     */       
/* 288 */       if (element != null) {
/* 289 */         sendEdocResult = new SendEdocResult();
/* 290 */         String status = element.getAsJsonObject().get("status").getAsString();
/* 291 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 292 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/* 293 */         String docID = element.getAsJsonObject().get("DocId").getAsString();
/*     */         
/* 295 */         sendEdocResult.setErrorCode(errorCode);
/* 296 */         sendEdocResult.setErrorDesc(errorDesc);
/* 297 */         sendEdocResult.setStatus(status);
/* 298 */         sendEdocResult.setDocID(docID);
/*     */       }
/*     */     
/*     */     }
/* 302 */     catch (Exception e) {
/* 303 */       e.printStackTrace();
/*     */     } 
/* 305 */     return sendEdocResult;
/*     */   }
/*     */   
/*     */   public GetEdocResult getEdoc(String jsonHeaders) {
/* 309 */     String urlRequest = String.valueOf(this.endpoint) + "/getEdoc";
/* 310 */     FileInputStream inputStream = null;
/* 311 */     GetEdocResult getEdocResult = null;
/* 312 */     String filePath = "";
/* 313 */     String docId = "";
/* 314 */     String json = "";
/*     */     try {
/* 316 */       this.headers.put("Content-Type", "application/json");
/* 317 */       JsonParser parser = new JsonParser();
/* 318 */       JsonElement element = parser.parse(jsonHeaders);
/* 319 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 320 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 321 */       while (iteratorJE.hasNext()) {
/* 322 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 323 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/* 324 */         if ("filePath".equalsIgnoreCase(entry.getKey())) {
/* 325 */           filePath = ((JsonElement)entry.getValue()).getAsString();
/*     */         }
/* 327 */         if ("docId".equalsIgnoreCase(entry.getKey())) {
/* 328 */           docId = ((JsonElement)entry.getValue()).getAsString();
/*     */         }
/*     */       } 
/*     */       
/* 332 */       json = execute(urlRequest, "POST", (HashMap<String, String>)null, getListHeader(), inputStream);
/*     */       
/* 334 */       parser = new JsonParser();
/* 335 */       element = parser.parse(json);
/*     */       
/* 337 */       if (element != null) {
/* 338 */         getEdocResult = new GetEdocResult();
/* 339 */         String status = element.getAsJsonObject().get("status").getAsString();
/*     */         
/* 341 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 342 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */ 
/*     */ 
/*     */         
/* 346 */         getEdocResult.setErrorCode(errorCode);
/* 347 */         getEdocResult.setErrorDesc(errorDesc);
/* 348 */         getEdocResult.setStatus(status);
/*     */         
/* 350 */         if (!"FAIL".contentEquals(status)) {
/* 351 */           Base64 base64 = new Base64();
/* 352 */           String data = new String(base64.decode(element.getAsJsonObject().get("data").getAsString()), 
/* 353 */               Charset.forName("UTF-8"));
/*     */           
/* 355 */           getEdocResult.setData(data);
/*     */           
/* 357 */           if (!StringUtils.isNullOrEmpty(filePath) && "0".equalsIgnoreCase(errorCode)) {
/* 358 */             int year = Calendar.getInstance().get(1);
/* 359 */             int month = Calendar.getInstance().get(2) + 1;
/* 360 */             filePath = String.valueOf(filePath) + "/" + year + "/" + month;
/* 361 */             if (!FileUtil.exist(filePath)) {
/* 362 */               FileUtil.mkdirs(filePath);
/*     */             }
/* 364 */             filePath = String.valueOf(filePath) + "/" + docId + ".edxml";
/* 365 */             Files.write(data.getBytes("UTF-8"), new File(filePath));
/* 366 */             getEdocResult.setFilePath(filePath);
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/* 372 */     } catch (Exception e) {
/* 373 */       if (this.properties.getIsLog() > 0) {
/*     */         try {
/* 375 */           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
/* 376 */           String date = dateFormat.format(Calendar.getInstance().getTime());
/* 377 */           String file = String.valueOf(this.properties.getLogError()) + "/" + date + ".txt";
/* 378 */           FileUtil.writeToFile(file, 
/* 379 */               "-----------" + Calendar.getInstance().getTime() + System.lineSeparator() + "------------", 
/* 380 */               true);
/* 381 */           FileUtil.writeToFile(file, "Noi dung json: " + json + System.lineSeparator(), true);
/*     */           
/* 383 */           FileUtil.writeToFile(file, String.valueOf(e.getMessage()) + System.lineSeparator(), true);
/* 384 */           FileUtil.writeToFile(file, String.valueOf(e.getStackTrace().toString()) + System.lineSeparator(), true);
/*     */           
/* 386 */           FileUtil.writeToFile(file, "-----------------------" + System.lineSeparator(), true);
/* 387 */         } catch (IOException e1) {
/*     */           
/* 389 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/* 392 */       e.printStackTrace();
/* 393 */       e.printStackTrace();
/*     */     } 
/* 395 */     return getEdocResult;
/*     */   }
/*     */ 
/*     */   
/*     */   public CountReceiveEdoc countReceiveEdoc(String jsonHeaders) {
/* 400 */     String urlRequest = String.valueOf(this.endpoint) + "/countReceiveEdoc";
/* 401 */     FileInputStream inputStream = null;
/* 402 */     CountReceiveEdoc countReceiveEdoc = null;
/* 403 */     String json = "";
/*     */     try {
/* 405 */       this.headers.put("Content-Type", "application/json");
/* 406 */       JsonParser parser = new JsonParser();
/* 407 */       JsonElement element = parser.parse(jsonHeaders);
/* 408 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 409 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 410 */       while (iteratorJE.hasNext()) {
/* 411 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 412 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*     */       
/* 415 */       json = execute(urlRequest, "GET", (HashMap<String, String>)null, getListHeader(), inputStream);
/*     */       
/* 417 */       parser = new JsonParser();
/* 418 */       element = parser.parse(json);
/*     */       
/* 420 */       if (element != null) {
/* 421 */         countReceiveEdoc = new CountReceiveEdoc();
/* 422 */         String status = element.getAsJsonObject().get("status").getAsString();
/*     */         
/* 424 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 425 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/* 427 */         countReceiveEdoc.setErrorCode(errorCode);
/* 428 */         countReceiveEdoc.setErrorDesc(errorDesc);
/* 429 */         countReceiveEdoc.setStatus(status);
/*     */         
/* 431 */         if (!"FAIL".contentEquals(status)) {
/* 432 */           countReceiveEdoc.setCount(element.getAsJsonObject().get("count").getAsInt());
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 438 */     catch (Exception e) {
/*     */       
/* 440 */       e.printStackTrace();
/* 441 */       e.printStackTrace();
/*     */     } 
/* 443 */     return countReceiveEdoc;
/*     */   }
/*     */   
/*     */   public CountReceiveEdoc countReceiveStatus(String jsonHeaders) {
/* 447 */     String urlRequest = String.valueOf(this.endpoint) + "/countReceiveStatus";
/* 448 */     FileInputStream inputStream = null;
/* 449 */     CountReceiveEdoc countReceiveEdoc = null;
/* 450 */     String json = "";
/*     */     try {
/* 452 */       this.headers.put("Content-Type", "application/json");
/* 453 */       JsonParser parser = new JsonParser();
/* 454 */       JsonElement element = parser.parse(jsonHeaders);
/* 455 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 456 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 457 */       while (iteratorJE.hasNext()) {
/* 458 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 459 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*     */       
/* 462 */       json = execute(urlRequest, "GET", (HashMap<String, String>)null, getListHeader(), inputStream);
/*     */       
/* 464 */       parser = new JsonParser();
/* 465 */       element = parser.parse(json);
/*     */       
/* 467 */       if (element != null) {
/* 468 */         countReceiveEdoc = new CountReceiveEdoc();
/* 469 */         String status = element.getAsJsonObject().get("status").getAsString();
/*     */         
/* 471 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 472 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/* 474 */         countReceiveEdoc.setErrorCode(errorCode);
/* 475 */         countReceiveEdoc.setErrorDesc(errorDesc);
/* 476 */         countReceiveEdoc.setStatus(status);
/*     */         
/* 478 */         if (!"FAIL".contentEquals(status)) {
/* 479 */           countReceiveEdoc.setCount(element.getAsJsonObject().get("count").getAsInt());
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 485 */     catch (Exception e) {
/*     */       
/* 487 */       e.printStackTrace();
/* 488 */       e.printStackTrace();
/*     */     } 
/* 490 */     return countReceiveEdoc;
/*     */   }
/*     */   
/*     */   public GetEdocResult downloadEdoc2(String jsonHeaders) {
/* 494 */     String urlRequest = String.valueOf(this.endpoint) + "/downloadEdoc";
/* 495 */     GetEdocResult getEdocResult = new GetEdocResult();
/* 496 */     getEdocResult.setData("");
/* 497 */     getEdocResult.setErrorCode("100");
/* 498 */     getEdocResult.setFilePath("");
/* 499 */     getEdocResult.setStatus("FAIL");
/* 500 */     String filePath = "";
/* 501 */     String docId = "";
/* 502 */     String json = "";
/*     */     try {
/* 504 */       this.headers.put("Content-Type", "application/json");
/* 505 */       JsonParser parser = new JsonParser();
/* 506 */       JsonElement element = parser.parse(jsonHeaders);
/* 507 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 508 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 509 */       while (iteratorJE.hasNext()) {
/* 510 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 511 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/* 512 */         if ("filePath".equalsIgnoreCase(entry.getKey())) {
/* 513 */           filePath = ((JsonElement)entry.getValue()).getAsString();
/*     */         }
/* 515 */         if ("docId".equalsIgnoreCase(entry.getKey())) {
/* 516 */           docId = ((JsonElement)entry.getValue()).getAsString();
/*     */         }
/*     */       } 
/* 519 */       int year = Calendar.getInstance().get(1);
/* 520 */       int month = Calendar.getInstance().get(2) + 1;
/* 521 */       filePath = String.valueOf(filePath) + "/" + year + "/" + month;
/* 522 */       if (!FileUtil.exist(filePath)) {
/* 523 */         FileUtil.mkdirs(filePath);
/*     */       }
/* 525 */       filePath = String.valueOf(filePath) + "/" + docId + ".edxml";
/* 526 */       File fileDownload = new File(filePath);
/* 527 */       HttpGet httpGet = new HttpGet(urlRequest);
/* 528 */       CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
/* 529 */       HttpResponse httpResponse = closeableHttpClient.execute((HttpUriRequest)httpGet);
/* 530 */       InputStream is = httpResponse.getEntity().getContent();
/* 531 */       FileOutputStream fos = new FileOutputStream(fileDownload);
/*     */       int inByte;
/* 533 */       while ((inByte = is.read()) != -1) {
/* 534 */         fos.write(inByte);
/*     */       }
/* 536 */       is.close();
/* 537 */       fos.flush();
/* 538 */       fos.close();
/*     */       
/* 540 */       if (fileDownload.exists() && fileDownload.length() > 0L) {
/* 541 */         getEdocResult = new GetEdocResult();
/* 542 */         getEdocResult.setData("");
/* 543 */         getEdocResult.setErrorCode("0");
/* 544 */         getEdocResult.setFilePath(filePath);
/* 545 */         getEdocResult.setStatus("SUCCESS");
/*     */       }
/*     */     
/* 548 */     } catch (Exception e) {
/* 549 */       getEdocResult = new GetEdocResult();
/* 550 */       getEdocResult.setData("");
/* 551 */       getEdocResult.setErrorCode("100");
/* 552 */       getEdocResult.setFilePath("");
/* 553 */       getEdocResult.setStatus("FAIL");
/* 554 */       if (this.properties.getIsLog() > 0) {
/*     */         try {
/* 556 */           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
/* 557 */           String date = dateFormat.format(Calendar.getInstance().getTime());
/* 558 */           String file = String.valueOf(this.properties.getLogError()) + "/" + date + ".txt";
/* 559 */           FileUtil.writeToFile(file, 
/* 560 */               "-----------" + Calendar.getInstance().getTime() + System.lineSeparator() + "------------", 
/* 561 */               true);
/* 562 */           FileUtil.writeToFile(file, "Noi dung json: " + json + System.lineSeparator(), true);
/*     */           
/* 564 */           FileUtil.writeToFile(file, String.valueOf(e.getMessage()) + System.lineSeparator(), true);
/* 565 */           FileUtil.writeToFile(file, String.valueOf(e.getStackTrace().toString()) + System.lineSeparator(), true);
/* 566 */           FileUtil.writeToFile(file, "-----------------------" + System.lineSeparator(), true);
/* 567 */         } catch (IOException e1) {
/* 568 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/* 571 */       e.printStackTrace();
/* 572 */       e.printStackTrace();
/*     */     } 
/* 574 */     return getEdocResult;
/*     */   }
/*     */   
/*     */   public GetEdocResult downloadEdoc(String jsonHeaders) {
/* 578 */     String urlRequest = String.valueOf(this.endpoint) + "/downloadEdoc";
/* 579 */     GetEdocResult getEdocResult = new GetEdocResult();
/* 580 */     getEdocResult.setData("");
/* 581 */     getEdocResult.setErrorCode("100");
/* 582 */     getEdocResult.setFilePath("");
/* 583 */     getEdocResult.setStatus("FAIL");
/* 584 */     String fileFolder = "";
/* 585 */     String docId = "";
/* 586 */     String json = "";
/*     */     try {
/* 588 */       this.headers.put("Content-Type", "application/json");
/* 589 */       JsonParser parser = new JsonParser();
/* 590 */       JsonElement element = parser.parse(jsonHeaders);
/* 591 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 592 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 593 */       while (iteratorJE.hasNext()) {
/* 594 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 595 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/* 596 */         if ("filePath".equalsIgnoreCase(entry.getKey())) {
/* 597 */           fileFolder = ((JsonElement)entry.getValue()).getAsString();
/*     */         }
/* 599 */         if ("docId".equalsIgnoreCase(entry.getKey())) {
/* 600 */           docId = ((JsonElement)entry.getValue()).getAsString();
/*     */         }
/*     */       } 
/* 603 */       getEdocResult = dowload(urlRequest, null, getListHeader(), docId, fileFolder);
/*     */     }
/* 605 */     catch (Exception e) {
/* 606 */       getEdocResult = new GetEdocResult();
/* 607 */       getEdocResult.setData("");
/* 608 */       getEdocResult.setErrorCode("100");
/* 609 */       getEdocResult.setFilePath("");
/* 610 */       getEdocResult.setStatus("FAIL");
/* 611 */       if (this.properties.getIsLog() > 0) {
/*     */         try {
/* 613 */           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
/* 614 */           String date = dateFormat.format(Calendar.getInstance().getTime());
/* 615 */           String file = String.valueOf(this.properties.getLogError()) + "/" + date + ".txt";
/* 616 */           FileUtil.writeToFile(file, 
/* 617 */               "-----------" + Calendar.getInstance().getTime() + System.lineSeparator() + "------------", 
/* 618 */               true);
/* 619 */           FileUtil.writeToFile(file, "Noi dung json: " + json + System.lineSeparator(), true);
/*     */           
/* 621 */           FileUtil.writeToFile(file, String.valueOf(e.getMessage()) + System.lineSeparator(), true);
/* 622 */           FileUtil.writeToFile(file, String.valueOf(e.getStackTrace().toString()) + System.lineSeparator(), true);
/* 623 */           FileUtil.writeToFile(file, "-----------------------" + System.lineSeparator(), true);
/* 624 */         } catch (IOException e1) {
/* 625 */           e1.printStackTrace();
/*     */         } 
/*     */       }
/* 628 */       e.printStackTrace();
/* 629 */       e.printStackTrace();
/*     */     } 
/* 631 */     return getEdocResult;
/*     */   }
/*     */   
/*     */   public GetChangeStatusResult updateStatus(String jsonHeaders) {
/* 635 */     this.headers.put("Content-Type", "application/json");
/* 636 */     String urlRequest = String.valueOf(this.endpoint) + "/updateStatus";
/* 637 */     GetChangeStatusResult getEdocResult = null;
/* 638 */     FileInputStream inputStream = null;
/*     */     try {
/* 640 */       JsonParser parser = new JsonParser();
/* 641 */       JsonElement element = parser.parse(jsonHeaders);
/* 642 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 643 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 644 */       while (iteratorJE.hasNext()) {
/* 645 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 646 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*     */       
/* 649 */       String json = execute(urlRequest, "POST", (HashMap<String, String>)null, getListHeader(), inputStream);
/*     */       
/* 651 */       parser = new JsonParser();
/* 652 */       element = parser.parse(json);
/* 653 */       if (element != null) {
/* 654 */         getEdocResult = new GetChangeStatusResult();
/* 655 */         String status = element.getAsJsonObject().get("status").getAsString();
/* 656 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 657 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/* 658 */         getEdocResult.setErrorCode(errorCode);
/* 659 */         getEdocResult.setErrorDesc(errorDesc);
/* 660 */         getEdocResult.setStatus(status);
/*     */       }
/*     */     
/*     */     }
/* 664 */     catch (Exception e) {
/* 665 */       e.printStackTrace();
/*     */     } 
/* 667 */     return getEdocResult;
/*     */   }
/*     */ 
/*     */   
/*     */   private void createContentHA256(String edXMLFileLocation) {
/* 672 */     FileInputStream fis = null;
/*     */     try {
/* 674 */       MessageDigest md = MessageDigest.getInstance("SHA-256");
/* 675 */       fis = new FileInputStream(edXMLFileLocation);
/*     */       
/* 677 */       byte[] dataBytes = new byte[1024];
/*     */       
/* 679 */       int nread = 0;
/* 680 */       while ((nread = fis.read(dataBytes)) != -1) {
/* 681 */         md.update(dataBytes, 0, nread);
/*     */       }
/*     */       
/* 684 */       byte[] mdbytes = md.digest();
/*     */ 
/*     */       
/* 687 */       StringBuffer sb = new StringBuffer();
/* 688 */       for (int i = 0; i < mdbytes.length; i++) {
/* 689 */         sb.append(Integer.toString((mdbytes[i] & 0xFF) + 256, 16).substring(1));
/*     */       }
/*     */       
/* 692 */       System.out.println("Hex format : " + sb.toString());
/*     */ 
/*     */       
/* 695 */       StringBuffer hexString = new StringBuffer();
/* 696 */       for (int j = 0; j < mdbytes.length; j++) {
/* 697 */         hexString.append(Integer.toHexString(0xFF & mdbytes[j]));
/*     */       }
/* 699 */       this.headers.put("hash-file", hexString.toString());
/* 700 */       System.out.println("Hex format : " + hexString.toString());
/* 701 */     } catch (NoSuchAlgorithmException e) {
/* 702 */       e.printStackTrace();
/* 703 */     } catch (IOException e) {
/* 704 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 707 */         fis.close();
/* 708 */       } catch (IOException e) {
/* 709 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 716 */     FileConfig.setFileConfig("D:/Works/Thangtt/Java/services/resources/collaboration.properties");
/* 717 */     VnptProperties vnptProperties = new VnptProperties(FileConfig.getCollaborationPF());
/* 718 */     KnobstickServiceImp knobstickServiceImp = new KnobstickServiceImp(vnptProperties);
/* 719 */     knobstickServiceImp.getReceivedEdocList("{}");
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\KnobstickServiceImp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */