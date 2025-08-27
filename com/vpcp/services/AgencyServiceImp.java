/*     */ package com.vpcp.services;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.vpcp.services.model.Agency;
/*     */ import com.vpcp.services.model.DeleteAgencyResult;
/*     */ import com.vpcp.services.model.GetAgenciesResult;
/*     */ import com.vpcp.services.model.GetSynchronizeUnit;
/*     */ import com.vpcp.services.model.RegisterAgencyResult;
/*     */ import com.vpcp.services.model.UnitSynchronize;
/*     */ import java.io.FileInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class AgencyServiceImp
/*     */   extends VnptService
/*     */   implements AgencyService {
/*     */   public AgencyServiceImp(VnptProperties properties) {
/*  24 */     super(properties);
/*     */   }
/*     */   
/*     */   public GetAgenciesResult getAgenciesList(String jsonHeaders) {
/*  28 */     this.headers.put("Content-Type", "application/json");
/*  29 */     String urlRequest = String.valueOf(this.endpoint) + "/getAgenciesList";
/*  30 */     GetAgenciesResult agenciesResult = null;
/*     */     try {
/*  32 */       JsonParser parser = new JsonParser();
/*  33 */       JsonElement element = parser.parse(jsonHeaders);
/*  34 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/*  35 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/*  36 */       while (iteratorJE.hasNext()) {
/*  37 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/*  38 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*     */       
/*  41 */       String json = execute(urlRequest, "POST", null, getListHeader());
/*     */       
/*  43 */       parser = new JsonParser();
/*  44 */       element = parser.parse(json);
/*     */       
/*  46 */       if (element != null) {
/*  47 */         agenciesResult = new GetAgenciesResult();
/*  48 */         String status = element.getAsJsonObject().get("status").getAsString();
/*  49 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/*  50 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/*  52 */         agenciesResult.setErrorCode(errorCode);
/*  53 */         agenciesResult.setErrorDesc(errorDesc);
/*  54 */         agenciesResult.setStatus(status);
/*  55 */         if (errorCode.equals("0")) {
/*  56 */           JsonArray data = element.getAsJsonObject().get("data").getAsJsonArray();
/*  57 */           if ("0".equalsIgnoreCase(errorCode)) {
/*  58 */             List<Agency> agencies = new ArrayList<>();
/*  59 */             Iterator<JsonElement> iterator = data.iterator();
/*  60 */             while (iterator.hasNext()) {
/*  61 */               JsonElement elementData = iterator.next();
/*  62 */               Agency agency = new Agency();
/*  63 */               if (elementData.getAsJsonObject().get("code") != null) {
/*  64 */                 agency.setCode(elementData.getAsJsonObject().get("code").getAsString());
/*     */               }
/*  66 */               if (elementData.getAsJsonObject().get("id") != null) {
/*  67 */                 agency.setId(elementData.getAsJsonObject().get("id").getAsString());
/*     */               }
/*  69 */               if (elementData.getAsJsonObject().get("mail") != null) {
/*  70 */                 agency.setMail(elementData.getAsJsonObject().get("mail").getAsString());
/*     */               }
/*  72 */               if (elementData.getAsJsonObject().get("name") != null) {
/*  73 */                 agency.setName(elementData.getAsJsonObject().get("name").getAsString());
/*     */               }
/*  75 */               if (elementData.getAsJsonObject().get("pid") != null) {
/*  76 */                 agency.setPid(elementData.getAsJsonObject().get("pid").getAsString());
/*     */               }
/*  78 */               if (elementData.getAsJsonObject().get("oldCode") != null)
/*  79 */                 agency.setOldCode(elementData.getAsJsonObject().get("oldCode").getAsString()); 
/*  80 */               agencies.add(agency);
/*     */             } 
/*     */             
/*  83 */             agenciesResult.setAgencies(agencies);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*  88 */     } catch (Exception e) {
/*  89 */       e.printStackTrace();
/*     */     } 
/*  91 */     return agenciesResult;
/*     */   }
/*     */   
/*     */   public GetAgenciesResult getAgenciesListQD20(String jsonHeaders) {
/*  95 */     this.headers.put("Content-Type", "application/json");
/*  96 */     String urlRequest = String.valueOf(this.endpoint) + "/getAgenciesListQD20";
/*  97 */     GetAgenciesResult agenciesResult = null;
/*     */     try {
/*  99 */       JsonParser parser = new JsonParser();
/* 100 */       JsonElement element = parser.parse(jsonHeaders);
/* 101 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 102 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 103 */       while (iteratorJE.hasNext()) {
/* 104 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 105 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*     */       
/* 108 */       String json = execute(urlRequest, "POST", null, getListHeader());
/*     */       
/* 110 */       parser = new JsonParser();
/* 111 */       element = parser.parse(json);
/*     */       
/* 113 */       if (element != null) {
/* 114 */         agenciesResult = new GetAgenciesResult();
/* 115 */         String status = element.getAsJsonObject().get("status").getAsString();
/* 116 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 117 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/* 119 */         agenciesResult.setErrorCode(errorCode);
/* 120 */         agenciesResult.setErrorDesc(errorDesc);
/* 121 */         agenciesResult.setStatus(status);
/* 122 */         if (errorCode.equals("0")) {
/* 123 */           JsonArray data = element.getAsJsonObject().get("data").getAsJsonArray();
/* 124 */           if ("0".equalsIgnoreCase(errorCode)) {
/* 125 */             List<Agency> agencies = new ArrayList<>();
/* 126 */             Iterator<JsonElement> iterator = data.iterator();
/* 127 */             while (iterator.hasNext()) {
/* 128 */               JsonElement elementData = iterator.next();
/* 129 */               Agency agency = new Agency();
/* 130 */               if (elementData.getAsJsonObject().get("code") != null) {
/* 131 */                 agency.setCode(elementData.getAsJsonObject().get("code").getAsString());
/*     */               }
/* 133 */               if (elementData.getAsJsonObject().get("id") != null) {
/* 134 */                 agency.setId(elementData.getAsJsonObject().get("id").getAsString());
/*     */               }
/* 136 */               if (elementData.getAsJsonObject().get("mail") != null) {
/* 137 */                 agency.setMail(elementData.getAsJsonObject().get("mail").getAsString());
/*     */               }
/* 139 */               if (elementData.getAsJsonObject().get("name") != null) {
/* 140 */                 agency.setName(elementData.getAsJsonObject().get("name").getAsString());
/*     */               }
/* 142 */               if (elementData.getAsJsonObject().get("code102") != null)
/* 143 */                 agency.setCode102(elementData.getAsJsonObject().get("code102").getAsString()); 
/* 144 */               agencies.add(agency);
/*     */             } 
/* 146 */             agenciesResult.setAgencies(agencies);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 151 */     } catch (Exception e) {
/* 152 */       e.printStackTrace();
/*     */     } 
/* 154 */     return agenciesResult;
/*     */   }
/*     */   
/*     */   public GetSynchronizeUnit getSynchronizeUnit(String jsonHeaders) {
/* 158 */     this.headers.put("Content-Type", "application/json");
/* 159 */     String urlRequest = String.valueOf(this.endpoint) + "/getSynchronizeUnit";
/* 160 */     GetSynchronizeUnit agenciesResult = null;
/*     */     try {
/* 162 */       JsonParser parser = new JsonParser();
/* 163 */       JsonElement element = parser.parse(jsonHeaders);
/* 164 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 165 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 166 */       while (iteratorJE.hasNext()) {
/* 167 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 168 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*     */       
/* 171 */       String json = execute(urlRequest, "POST", null, getListHeader());
/* 172 */       parser = new JsonParser();
/* 173 */       element = parser.parse(json);
/*     */       
/* 175 */       if (element != null) {
/* 176 */         agenciesResult = new GetSynchronizeUnit();
/* 177 */         String status = element.getAsJsonObject().get("status").getAsString();
/* 178 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 179 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/* 181 */         agenciesResult.setErrorCode(errorCode);
/* 182 */         agenciesResult.setErrorDesc(errorDesc);
/* 183 */         agenciesResult.setStatus(status);
/* 184 */         if (errorCode.equals("0")) {
/* 185 */           JsonArray data = element.getAsJsonObject().get("data").getAsJsonArray();
/* 186 */           if ("0".equalsIgnoreCase(errorCode)) {
/* 187 */             List<UnitSynchronize> agencies = new ArrayList<>();
/* 188 */             Iterator<JsonElement> iterator = data.iterator();
/* 189 */             while (iterator.hasNext()) {
/* 190 */               JsonElement elementData = iterator.next();
/* 191 */               UnitSynchronize agency = new UnitSynchronize();
/* 192 */               if (elementData.getAsJsonObject().get("code") != null) {
/* 193 */                 agency.setCode(elementData.getAsJsonObject().get("code").getAsString());
/*     */               }
/* 195 */               if (elementData.getAsJsonObject().get("id") != null) {
/* 196 */                 agency.setId(elementData.getAsJsonObject().get("id").getAsString());
/*     */               }
/* 198 */               if (elementData.getAsJsonObject().get("mail") != null) {
/* 199 */                 agency.setMail(elementData.getAsJsonObject().get("mail").getAsString());
/*     */               }
/* 201 */               if (elementData.getAsJsonObject().get("name") != null) {
/* 202 */                 agency.setName(elementData.getAsJsonObject().get("name").getAsString());
/*     */               }
/* 204 */               if (elementData.getAsJsonObject().get("pid") != null) {
/* 205 */                 agency.setPid(elementData.getAsJsonObject().get("pid").getAsString());
/*     */               }
/* 207 */               if (elementData.getAsJsonObject().get("version") != null) {
/* 208 */                 agency.setVersion(elementData.getAsJsonObject().get("version").getAsInt());
/*     */               }
/* 210 */               if (elementData.getAsJsonObject().get("centerCode") != null) {
/* 211 */                 agency.setCenterCode(elementData.getAsJsonObject().get("centerCode").getAsString());
/*     */               }
/* 213 */               if (elementData.getAsJsonObject().get("deleted") != null) {
/* 214 */                 agency.setDeleted(elementData.getAsJsonObject().get("deleted").getAsBoolean());
/*     */               }
/* 216 */               if (elementData.getAsJsonObject().get("modifyDate") != null) {
/* 217 */                 agency.setModifyDate(elementData.getAsJsonObject().get("modifyDate").getAsString());
/*     */               }
/* 219 */               if (elementData.getAsJsonObject().get("isAgency") != null) {
/* 220 */                 agency.setIsAgency(Boolean.valueOf(elementData.getAsJsonObject().get("isAgency").getAsBoolean()));
/*     */               }
/* 222 */               agencies.add(agency);
/*     */             } 
/*     */             
/* 225 */             agenciesResult.setUnitSynchronize(agencies);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 230 */     } catch (Exception e) {
/* 231 */       e.printStackTrace();
/*     */     } 
/* 233 */     return agenciesResult;
/*     */   }
/*     */ 
/*     */   
/*     */   public RegisterAgencyResult registerAgency(String jsonHeaders, String data) {
/* 238 */     String urlRequest = String.valueOf(this.endpoint) + "/registerAgency";
/* 239 */     this.headers.put("Content-Type", "application/json");
/* 240 */     JsonParser parser = new JsonParser();
/* 241 */     JsonElement element = parser.parse(jsonHeaders);
/* 242 */     Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 243 */     Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 244 */     while (iteratorJE.hasNext()) {
/* 245 */       Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 246 */       this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */     } 
/*     */     
/* 249 */     RegisterAgencyResult registerAgencyResult = null;
/*     */     
/*     */     try {
/* 252 */       String json = executeJson(urlRequest, "POST", null, getListHeader(), data);
/* 253 */       parser = new JsonParser();
/* 254 */       element = parser.parse(json);
/*     */       
/* 256 */       if (element != null) {
/* 257 */         registerAgencyResult = new RegisterAgencyResult();
/* 258 */         String status = element.getAsJsonObject().get("status").getAsString();
/* 259 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 260 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/* 262 */         registerAgencyResult.setErrorCode(errorCode);
/* 263 */         registerAgencyResult.setErrorDesc(errorDesc);
/* 264 */         registerAgencyResult.setStatus(status);
/*     */       }
/*     */     
/* 267 */     } catch (Exception e) {
/* 268 */       e.printStackTrace();
/*     */     } 
/* 270 */     return registerAgencyResult;
/*     */   }
/*     */   
/*     */   public DeleteAgencyResult deleteAgency(String jsonHeaders) {
/* 274 */     String urlRequest = String.valueOf(this.endpoint) + "/deleteAgency";
/* 275 */     this.headers.put("Content-Type", "application/json");
/* 276 */     DeleteAgencyResult deleteAgencyResult = null;
/*     */     try {
/* 278 */       JsonParser parser = new JsonParser();
/* 279 */       JsonElement element = parser.parse(jsonHeaders);
/* 280 */       Set<Map.Entry<String, JsonElement>> set = element.getAsJsonObject().entrySet();
/* 281 */       Iterator<Map.Entry<String, JsonElement>> iteratorJE = set.iterator();
/* 282 */       while (iteratorJE.hasNext()) {
/* 283 */         Map.Entry<String, JsonElement> entry = iteratorJE.next();
/* 284 */         this.headers.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
/*     */       } 
/*     */       
/* 287 */       FileInputStream inputStream = null;
/* 288 */       String json = execute(urlRequest, "POST", (HashMap<String, String>)null, getListHeader(), inputStream);
/* 289 */       parser = new JsonParser();
/* 290 */       element = parser.parse(json);
/*     */       
/* 292 */       if (element != null) {
/* 293 */         deleteAgencyResult = new DeleteAgencyResult();
/* 294 */         String status = element.getAsJsonObject().get("status").getAsString();
/* 295 */         String errorCode = element.getAsJsonObject().get("ErrorCode").getAsString();
/* 296 */         String errorDesc = element.getAsJsonObject().get("ErrorDesc").getAsString();
/*     */         
/* 298 */         deleteAgencyResult.setErrorCode(errorCode);
/* 299 */         deleteAgencyResult.setErrorDesc(errorDesc);
/* 300 */         deleteAgencyResult.setStatus(status);
/*     */       }
/*     */     
/* 303 */     } catch (Exception e) {
/* 304 */       e.printStackTrace();
/*     */     } 
/* 306 */     return deleteAgencyResult;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 310 */     FileConfig.setFileConfig("D:\\Works\\Thangtt\\Java\\services\\resources\\collaboration.properties");
/* 311 */     VnptProperties vnptProperties = new VnptProperties(FileConfig.getCollaborationPF());
/*     */     
/* 313 */     AgencyServiceImp agencyServiceImp = new AgencyServiceImp(vnptProperties);
/* 314 */     agencyServiceImp.getAgenciesList("{}");
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\AgencyServiceImp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */