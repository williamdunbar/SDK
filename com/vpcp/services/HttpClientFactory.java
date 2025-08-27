/*     */ package com.vpcp.services;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import org.apache.http.Consts;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.ParseException;
/*     */ import org.apache.http.client.CookieStore;
/*     */ import org.apache.http.client.CredentialsProvider;
/*     */ import org.apache.http.client.HttpClient;
/*     */ import org.apache.http.client.HttpRequestRetryHandler;
/*     */ import org.apache.http.client.config.RequestConfig;
/*     */ import org.apache.http.config.ConnectionConfig;
/*     */ import org.apache.http.config.MessageConstraints;
/*     */ import org.apache.http.config.Registry;
/*     */ import org.apache.http.config.RegistryBuilder;
/*     */ import org.apache.http.config.SocketConfig;
/*     */ import org.apache.http.conn.DnsResolver;
/*     */ import org.apache.http.conn.HttpClientConnectionManager;
/*     */ import org.apache.http.conn.HttpConnectionFactory;
/*     */ import org.apache.http.conn.socket.ConnectionSocketFactory;
/*     */ import org.apache.http.conn.socket.PlainConnectionSocketFactory;
/*     */ import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
/*     */ import org.apache.http.impl.DefaultHttpResponseFactory;
/*     */ import org.apache.http.impl.client.BasicCookieStore;
/*     */ import org.apache.http.impl.client.BasicCredentialsProvider;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.impl.client.HttpClients;
/*     */ import org.apache.http.impl.conn.DefaultHttpResponseParser;
/*     */ import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
/*     */ import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
/*     */ import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
/*     */ import org.apache.http.impl.conn.SystemDefaultDnsResolver;
/*     */ import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
/*     */ import org.apache.http.io.HttpMessageParser;
/*     */ import org.apache.http.io.HttpMessageParserFactory;
/*     */ import org.apache.http.io.HttpMessageWriterFactory;
/*     */ import org.apache.http.io.SessionInputBuffer;
/*     */ import org.apache.http.message.BasicHeader;
/*     */ import org.apache.http.message.BasicLineParser;
/*     */ import org.apache.http.message.LineParser;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.ssl.SSLContexts;
/*     */ import org.apache.http.util.CharArrayBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpClientFactory
/*     */ {
/*     */   private static CloseableHttpClient client;
/*  63 */   private static String host = "";
/*  64 */   private static int retry = 3;
/*  65 */   private static int maxConnection = 10;
/*  66 */   private static int port = 80;
/*     */   
/*     */   public HttpClientFactory(VnptProperties properties) {
/*  69 */     retry = properties.getRetry();
/*  70 */     maxConnection = properties.getMaxConnection();
/*  71 */     port = properties.getPort();
/*  72 */     host = properties.getHost();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpClient getHttpsClient(List<Header> headers) throws Exception {
/*  78 */     DefaultHttpResponseParserFactory defaultHttpResponseParserFactory = new DefaultHttpResponseParserFactory()
/*     */       {
/*     */         public HttpMessageParser<HttpResponse> create(SessionInputBuffer buffer, MessageConstraints constraints)
/*     */         {
/*  82 */           BasicLineParser basicLineParser = new BasicLineParser()
/*     */             {
/*     */               public Header parseHeader(CharArrayBuffer buffer)
/*     */               {
/*     */                 try {
/*  87 */                   return super.parseHeader(buffer);
/*  88 */                 } catch (ParseException ex) {
/*  89 */                   return (Header)new BasicHeader(buffer.toString(), null);
/*     */                 } 
/*     */               }
/*     */             };
/*     */           
/*  94 */           return (HttpMessageParser<HttpResponse>)new DefaultHttpResponseParser(buffer, (LineParser)basicLineParser, (HttpResponseFactory)DefaultHttpResponseFactory.INSTANCE, 
/*  95 */               constraints)
/*     */             {
/*     */               
/*     */               protected boolean reject(CharArrayBuffer line, int count)
/*     */               {
/* 100 */                 return false;
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 107 */     DefaultHttpRequestWriterFactory defaultHttpRequestWriterFactory = new DefaultHttpRequestWriterFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     ManagedHttpClientConnectionFactory managedHttpClientConnectionFactory = new ManagedHttpClientConnectionFactory(
/* 114 */         (HttpMessageWriterFactory)defaultHttpRequestWriterFactory, (HttpMessageParserFactory)defaultHttpResponseParserFactory);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     SSLContext sslcontext = SSLContexts.createSystemDefault();
/*     */ 
/*     */ 
/*     */     
/* 127 */     Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.create()
/* 128 */       .register("http", PlainConnectionSocketFactory.INSTANCE)
/* 129 */       .register("https", new SSLConnectionSocketFactory(sslcontext)).build();
/*     */ 
/*     */     
/* 132 */     SystemDefaultDnsResolver systemDefaultDnsResolver = new SystemDefaultDnsResolver()
/*     */       {
/*     */         public InetAddress[] resolve(String host) throws UnknownHostException
/*     */         {
/* 136 */           if (host.equalsIgnoreCase("myhost")) {
/* 137 */             return new InetAddress[] { InetAddress.getByAddress(new byte[] { Byte.MAX_VALUE, 1 }) };
/*     */           }
/* 139 */           return super.resolve(host);
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, 
/* 147 */         (HttpConnectionFactory)managedHttpClientConnectionFactory, (DnsResolver)systemDefaultDnsResolver);
/*     */ 
/*     */     
/* 150 */     SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
/*     */ 
/*     */     
/* 153 */     connManager.setDefaultSocketConfig(socketConfig);
/* 154 */     connManager.setSocketConfig(new HttpHost(host, port), socketConfig);
/*     */     
/* 156 */     connManager.setValidateAfterInactivity(1000);
/*     */ 
/*     */     
/* 159 */     MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(20000)
/* 160 */       .setMaxLineLength(2000000).build();
/*     */     
/* 162 */     ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE)
/* 163 */       .setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(Consts.UTF_8)
/* 164 */       .setMessageConstraints(messageConstraints).build();
/*     */ 
/*     */     
/* 167 */     connManager.setDefaultConnectionConfig(connectionConfig);
/* 168 */     connManager.setConnectionConfig(new HttpHost(host, port), ConnectionConfig.DEFAULT);
/*     */ 
/*     */ 
/*     */     
/* 172 */     connManager.setMaxTotal(maxConnection);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     BasicCookieStore basicCookieStore = new BasicCookieStore();
/*     */     
/* 179 */     BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
/*     */     
/* 181 */     RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec("default")
/* 182 */       .setExpectContinueEnabled(true)
/* 183 */       .setTargetPreferredAuthSchemes(Arrays.asList(new String[] { "NTLM", "Digest"
/* 184 */           })).setProxyPreferredAuthSchemes(Arrays.asList(new String[] { "Basic" })).build();
/*     */     
/* 186 */     client = HttpClients.custom().setConnectionManager((HttpClientConnectionManager)connManager).setDefaultCookieStore((CookieStore)basicCookieStore)
/* 187 */       .setDefaultCredentialsProvider((CredentialsProvider)basicCredentialsProvider).setDefaultRequestConfig(defaultRequestConfig)
/* 188 */       .setDefaultHeaders(headers).setRetryHandler(retryHandler()).build();
/*     */     
/* 190 */     return (HttpClient)client;
/*     */   }
/*     */   
/*     */   public static void releaseInstance() {
/* 194 */     client = null;
/*     */   }
/*     */   
/*     */   private static HttpRequestRetryHandler retryHandler() {
/* 198 */     return new HttpRequestRetryHandler() {
/*     */         public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
/* 200 */           System.out.println("Retry lan thu:" + executionCount);
/* 201 */           if (executionCount >= HttpClientFactory.retry)
/*     */           {
/* 203 */             return false;
/*     */           }
/* 205 */           if (exception instanceof java.io.InterruptedIOException)
/*     */           {
/* 207 */             return false;
/*     */           }
/* 209 */           if (exception instanceof UnknownHostException)
/*     */           {
/* 211 */             return false;
/*     */           }
/* 213 */           if (exception instanceof javax.net.ssl.SSLException)
/*     */           {
/* 215 */             return false;
/*     */           }
/* 217 */           return true;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              G:\BCY_task\2024\3.DTCB_LGSP\4.VNPT_Adapter\SDKVXP-2.0.1.jar!\com\vpcp\services\HttpClientFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */