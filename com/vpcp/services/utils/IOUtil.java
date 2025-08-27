/*     */ package com.vpcp.services.utils;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IOUtil
/*     */ {
/*     */   public static String getFileContenntAsString(File file, String encoding) throws Exception {
/*  25 */     FileInputStream is = new FileInputStream(file);
/*  26 */     return new String(getStreamContentAsBytes(is), encoding);
/*     */   }
/*     */   
/*     */   public static String getFileContenntAsString(File file) throws Exception {
/*  30 */     FileInputStream is = new FileInputStream(file);
/*  31 */     return new String(getStreamContentAsBytes(is));
/*     */   }
/*     */   
/*     */   public static String getFileContenntAsString(String fileName, String encoding) throws Exception {
/*  35 */     FileInputStream is = new FileInputStream(fileName);
/*  36 */     return new String(getStreamContentAsBytes(is), encoding);
/*     */   }
/*     */   
/*     */   public static String getFileContenntAsString(String fileName) throws Exception {
/*  40 */     FileInputStream is = new FileInputStream(fileName);
/*  41 */     String data = new String(getStreamContentAsBytes(is));
/*  42 */     is.close();
/*  43 */     return data;
/*     */   }
/*     */   
/*     */   public static byte[] getFileContentAsBytes(String fileName) throws Exception {
/*  47 */     FileInputStream is = new FileInputStream(fileName);
/*  48 */     byte[] data = getStreamContentAsBytes(is);
/*  49 */     is.close();
/*  50 */     return data;
/*     */   }
/*     */   
/*     */   public static String getStreamContentAsString(InputStream is, String encoding) throws Exception {
/*  54 */     return new String(getStreamContentAsBytes(is), encoding);
/*     */   }
/*     */   
/*     */   public static byte[] getStreamContentAsBytes(InputStream is) throws IOException {
/*  58 */     BufferedInputStream buffer = new BufferedInputStream(is);
/*  59 */     ByteArrayOutputStream output = new ByteArrayOutputStream();
/*  60 */     byte[] data = new byte[4912];
/*  61 */     int available = -1;
/*  62 */     while ((available = buffer.read(data)) > -1) {
/*  63 */       output.write(data, 0, available);
/*     */     }
/*  65 */     return output.toByteArray();
/*     */   }
/*     */   
/*     */   public static byte[] getStreamContentAsBytes(InputStream is, int maxRead) throws IOException {
/*  69 */     BufferedInputStream buffer = new BufferedInputStream(is);
/*  70 */     ByteArrayOutputStream output = new ByteArrayOutputStream();
/*  71 */     byte[] data = new byte[4912];
/*  72 */     int available = -1, read = 0;
/*  73 */     while ((available = buffer.read(data)) > -1 && read < maxRead) {
/*  74 */       if (maxRead - read < available) available = maxRead - read; 
/*  75 */       output.write(data, 0, available);
/*  76 */       read += available;
/*     */     } 
/*  78 */     return output.toByteArray();
/*     */   }
/*     */   
/*     */   public static String getResourceAsString(String resource, String encoding) throws Exception {
/*  82 */     ClassLoader cl = Thread.currentThread().getContextClassLoader();
/*  83 */     URL url = cl.getResource(resource);
/*  84 */     InputStream is = url.openStream();
/*  85 */     String data = getStreamContentAsString(is, encoding);
/*  86 */     is.close();
/*  87 */     return data;
/*     */   }
/*     */   
/*     */   public static byte[] getResourceAsBytes(String resource) throws Exception {
/*  91 */     ClassLoader cl = Thread.currentThread().getContextClassLoader();
/*  92 */     URL url = cl.getResource(resource);
/*  93 */     InputStream is = url.openStream();
/*  94 */     byte[] data = getStreamContentAsBytes(is);
/*  95 */     is.close();
/*  96 */     return data;
/*     */   }
/*     */   
/*     */   public static byte[] serialize(Object obj) throws Exception {
/* 100 */     ByteArrayOutputStream bytes = new ByteArrayOutputStream();
/* 101 */     ObjectOutputStream out = new ObjectOutputStream(bytes);
/* 102 */     out.writeObject(obj);
/* 103 */     out.close();
/* 104 */     byte[] ret = bytes.toByteArray();
/* 105 */     return ret;
/*     */   }
/*     */   
/*     */   public static Object deserialize(byte[] bytes) throws Exception {
/* 109 */     if (bytes == null) return null; 
/* 110 */     ByteArrayInputStream is = new ByteArrayInputStream(bytes);
/* 111 */     ObjectInputStream in = new ObjectInputStream(is);
/* 112 */     Object obj = in.readObject();
/* 113 */     in.close();
/* 114 */     return obj;
/*     */   }
/*     */   
/*     */   public static String findContainingJar(Class my_class) {
/* 118 */     ClassLoader loader = my_class.getClassLoader();
/* 119 */     String class_file = String.valueOf(my_class.getName().replaceAll("\\.", "/")) + ".class";
/*     */     try {
/* 121 */       Enumeration<URL> itr = loader.getResources(class_file);
/* 122 */       while (itr.hasMoreElements()) {
/* 123 */         URL url = itr.nextElement();
/* 124 */         if ("jar".equals(url.getProtocol())) {
/* 125 */           String toReturn = url.getPath();
/* 126 */           if (toReturn.startsWith("file:")) {
/* 127 */             toReturn = toReturn.substring("file:".length());
/*     */           }
/* 129 */           toReturn = URLDecoder.decode(toReturn, "UTF-8");
/* 130 */           return toReturn.replaceAll("!.*$", "");
/*     */         } 
/*     */       } 
/* 133 */     } catch (IOException e) {
/* 134 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/* 137 */     return null;
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\service\\utils\IOUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */