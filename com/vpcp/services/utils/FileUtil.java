/*     */ package com.vpcp.services.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileUtil
/*     */ {
/*     */   public static int emptyFolder(File folder, boolean ignoreCannotDel) throws IOException {
/*  27 */     int counter = 0;
/*  28 */     if (folder.exists() && folder.isDirectory()) {
/*  29 */       File[] child = folder.listFiles();
/*  30 */       for (int i = 0; i < child.length; i++) {
/*  31 */         File file = child[i];
/*  32 */         if (file.isDirectory()) counter += emptyFolder(file, ignoreCannotDel); 
/*  33 */         boolean result = file.delete();
/*  34 */         if (!result && !ignoreCannotDel) {
/*  35 */           throw new IOException("Cannot delete " + file.getAbsolutePath());
/*     */         }
/*  37 */         counter++;
/*     */       } 
/*     */     } 
/*     */     
/*  41 */     return counter;
/*     */   }
/*     */   
/*     */   public static int remove(File file, boolean ignoreCannotDelete) throws Exception {
/*  45 */     int counter = 0;
/*  46 */     if (file.exists()) {
/*  47 */       if (file.isDirectory()) {
/*  48 */         counter += emptyFolder(file, ignoreCannotDelete);
/*     */       }
/*  50 */       boolean result = file.delete();
/*  51 */       if (!result && !ignoreCannotDelete) {
/*  52 */         throw new Exception("Cannot delete " + file.getAbsolutePath());
/*     */       }
/*  54 */       counter++;
/*     */     } 
/*     */     
/*  57 */     return counter;
/*     */   }
/*     */   
/*     */   public static void removeIfExist(String path) throws Exception {
/*  61 */     File file = new File(path);
/*  62 */     if (file.exists()) remove(file, true); 
/*     */   }
/*     */   
/*     */   public static boolean exist(String path) throws Exception {
/*  66 */     File file = new File(path);
/*  67 */     return file.exists();
/*     */   }
/*     */   
/*     */   public static void mkdirs(String path) throws Exception {
/*  71 */     File file = new File(path);
/*  72 */     if (!file.exists() && 
/*  73 */       !file.mkdirs()) {
/*  74 */       throw new Exception("Cannot create directory " + path);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int cp(String src, String dest) throws Exception {
/*  80 */     int counter = 0;
/*  81 */     File srcFolder = new File(src);
/*  82 */     if (!srcFolder.exists()) throw new Exception(String.valueOf(src) + " does not exist");
/*     */     
/*  84 */     if (srcFolder.isFile()) {
/*  85 */       File destFolder = new File(dest);
/*  86 */       if (destFolder.isFile()) {
/*  87 */         dest = destFolder.getParent();
/*     */       }
/*  89 */       if (destFolder.exists()) {
/*  90 */         dest = String.valueOf(dest) + "/" + srcFolder.getName();
/*     */       }
/*  92 */       InputStream input = new FileInputStream(srcFolder);
/*  93 */       OutputStream output = new FileOutputStream(dest);
/*  94 */       byte[] buff = new byte[8192];
/*  95 */       int len = 0;
/*  96 */       while ((len = input.read(buff)) > 0) {
/*  97 */         output.write(buff, 0, len);
/*     */       }
/*  99 */       input.close();
/* 100 */       output.close();
/* 101 */       counter++;
/*     */     } else {
/* 103 */       File destFolder = new File(dest);
/* 104 */       if (!destFolder.exists()) {
/* 105 */         destFolder.mkdirs();
/*     */       }
/* 107 */       File[] child = srcFolder.listFiles();
/* 108 */       for (int i = 0; i < child.length; i++) {
/* 109 */         File file = child[i];
/* 110 */         if (file.isFile()) {
/* 111 */           counter += cp(file.getAbsolutePath(), String.valueOf(destFolder.getAbsolutePath()) + "/" + file.getName());
/*     */         } else {
/* 113 */           counter += cp(file.getAbsolutePath(), String.valueOf(destFolder.getAbsolutePath()) + "/" + file.getName());
/*     */         } 
/*     */       } 
/*     */     } 
/* 117 */     return counter;
/*     */   }
/*     */   
/*     */   public static void copyTo(InputStream src, String dest) throws IOException {
/* 121 */     File destFolder = new File(dest);
/* 122 */     if (destFolder.isFile()) {
/* 123 */       dest = destFolder.getParent();
/*     */     }
/* 125 */     OutputStream output = new FileOutputStream(dest);
/* 126 */     byte[] buff = new byte[8192];
/* 127 */     int len = 0;
/* 128 */     while ((len = src.read(buff)) > 0) {
/* 129 */       output.write(buff, 0, len);
/*     */     }
/* 131 */     src.close();
/* 132 */     output.close();
/*     */   }
/*     */   
/*     */   public static void writeToFile(String fname, String data, boolean append) throws IOException {
/* 136 */     writeToFile(fname, data.getBytes("UTF-8"), append);
/*     */   }
/*     */   
/*     */   public static void writeToFile(String fname, byte[] data, boolean append) throws IOException {
/* 140 */     FileOutputStream os = new FileOutputStream(fname, append);
/* 141 */     os.write(data);
/* 142 */     os.close();
/*     */   }
/*     */   
/*     */   public static <T extends java.io.FileFilter> List<String> findFiles(File dir, T filter) throws IOException {
/* 146 */     List<String> holder = new ArrayList<>();
/* 147 */     findFiles(holder, dir, filter);
/* 148 */     return holder;
/*     */   }
/*     */   
/*     */   public static <T extends java.io.FileFilter> void findFiles(List<String> holder, File file, T filter) throws IOException {
/* 152 */     if (file.isFile() && filter.accept(file)) {
/* 153 */       holder.add(file.getAbsolutePath()); return;
/*     */     } 
/* 155 */     if (file.isDirectory()) {
/* 156 */       byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = file.listFiles()).length, b = 0; b < i; ) { File child = arrayOfFile[b];
/* 157 */         findFiles(holder, child, filter);
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */   public static void deleteFile(String folder) {
/* 163 */     File file = new File(folder);
/* 164 */     if (file.isDirectory()) {
/*     */       byte b; int i; File[] arrayOfFile;
/* 166 */       for (i = (arrayOfFile = file.listFiles()).length, b = 0; b < i; ) { File child = arrayOfFile[b];
/* 167 */         if (child.isFile()) {
/* 168 */           child.delete();
/*     */         }
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ArrayList<String> readFileComicUrls() {
/* 176 */     ArrayList<String> urls = new ArrayList<>();
/*     */     try {
/* 178 */       FileInputStream fstream = new FileInputStream("./conf/comicUrl.txt");
/* 179 */       DataInputStream in = new DataInputStream(fstream);
/* 180 */       BufferedReader br = new BufferedReader(new InputStreamReader(in));
/*     */       
/*     */       String strLine;
/* 183 */       while ((strLine = br.readLine()) != null) {
/* 184 */         urls.add(strLine);
/*     */       }
/* 186 */       in.close();
/*     */     }
/* 188 */     catch (Exception e) {
/* 189 */       System.err.println("Error: " + e.getMessage());
/*     */     } 
/* 191 */     return urls;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void saveImage(String imageUrl, String destinationFile) {
/*     */     try {
/* 197 */       URL url = new URL(imageUrl);
/* 198 */       InputStream is = url.openStream();
/* 199 */       OutputStream os = new FileOutputStream(destinationFile);
/*     */       
/* 201 */       byte[] b = new byte[2048];
/*     */       
/*     */       int length;
/* 204 */       while ((length = is.read(b)) != -1) {
/* 205 */         os.write(b, 0, length);
/*     */       }
/*     */       
/* 208 */       is.close();
/* 209 */       os.close();
/* 210 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\service\\utils\FileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */