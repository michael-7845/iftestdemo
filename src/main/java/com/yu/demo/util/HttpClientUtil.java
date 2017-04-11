package com.yu.demo.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;  
import java.net.URISyntaxException;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;  
  
import org.apache.http.HttpEntity;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.client.methods.HttpRequestBase;  
import org.apache.http.client.utils.URIBuilder;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
  
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils; 

public class HttpClientUtil {  
    private static PoolingHttpClientConnectionManager cm;  
    private static IResult EMPTY_STR = new HttpResult(-1, -1, "");  
    private static String UTF_8 = "UTF-8";  
  
    private static void init() {  
        if (cm == null) {  
            cm = new PoolingHttpClientConnectionManager();  
            cm.setMaxTotal(50);// 整个连接池最大连接数  
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2  
        }  
    }  
  
    /** 
     * 通过连接池获取HttpClient 
     *  
     * @return 
     */  
    private static CloseableHttpClient getHttpClient() {  
        init();  
        return HttpClients.custom().setConnectionManager(cm).build();  
    }  
  
    /** 
     * @param url 
     * @return 
     */  
    public static IResult httpGetRequest(String url) {  
        HttpGet httpGet = new HttpGet(url);  
        return getResult(httpGet);  
    }  
  
    /** 
     * @param url 
     * @param params
     * @return 
     */ 
    public static IResult httpGetRequest(String url, Map<String, Object> params) 
    		throws URISyntaxException {  
        URIBuilder ub = new URIBuilder();  
        ub.setPath(url);  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  
        ub.setParameters(pairs);  
        
        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
    }  

    /** 
     * @param url 
     * @param headers
     * @param params
     * @return 
     */ 
    public static IResult httpGetRequest(String url, Map<String, Object> headers, 
    		Map<String, Object> params) throws URISyntaxException {  
        URIBuilder ub = new URIBuilder();  
        ub.setPath(url);  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  
        ub.setParameters(pairs);  
  
        HttpGet httpGet = new HttpGet(ub.build());  
        for (Map.Entry<String, Object> param : headers.entrySet()) {  
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));  
        }  
        return getResult(httpGet);  
    }  
  
    
    /**
     * @param url
     * @return
     */
    public static IResult httpPostRequest(String url) {  
        HttpPost httpPost = new HttpPost(url);  
        return getResult(httpPost);  
    }  
  
    /**
     * @param url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static IResult httpPostRequest(String url, Map<String, Object> params) 
    		throws UnsupportedEncodingException {  
        HttpPost httpPost = new HttpPost(url);  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));  
        return getResult(httpPost);  
    }  
  
    /**
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static IResult httpPostRequest(String url, Map<String, Object> headers, 
    		Map<String, Object> params) throws UnsupportedEncodingException {  
        HttpPost httpPost = new HttpPost(url);  
  
        for (Map.Entry<String, Object> param : headers.entrySet()) {  
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));  
        }  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);  
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));  
  
        return getResult(httpPost);  
    }  
  
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {  
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();  
        for (Map.Entry<String, Object> param : params.entrySet()) {  
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));  
        }  
  
        return pairs;  
    }  
  
    /** 
     * 处理Http请求 
     *  
     * @param request 
     * @return 
     */  
    private static IResult getResult(HttpRequestBase request) {  
        // CloseableHttpClient httpClient = HttpClients.createDefault();  
        CloseableHttpClient httpClient = getHttpClient();  
        try {  
            CloseableHttpResponse response = httpClient.execute(request);  
            int statusCode = response.getStatusLine().getStatusCode();  
            HttpEntity entity = response.getEntity();  
            if (entity != null) {  
                long len = entity.getContentLength();// -1 表示长度未知 
                String result = EntityUtils.toString(entity);  
                response.close();  
                // httpClient.close();  
                return new HttpResult(statusCode, len, result);  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
  
        }  
  
        return EMPTY_STR;  
    }

    public static void demo() {
        IResult result = null;
//    	result = HttpClientUtil.httpGetRequest("http://www.2345.com/about/about.htm");
//    	System.out.println(result);

        result = HttpClientUtil.httpGetRequest("http://p.3.cn/prices/mgets?skuIds=J_954086&type=1");
//    	System.out.println(result);

        Map<String, Object> params = new HashMap<String, Object>() {{
            put("skuIds", (Object)"J_954086");
            put("type", (Object)"1");
        }};
        try {
            result = HttpClientUtil.httpGetRequest("http://p.3.cn/prices/mgets", new HashMap<String, Object>(), params);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    static String debugCookie="pin=test_jdb;ceshi3.com=B3opkNQXIhU7w5TrYl0VRXeFsjubcuul8YLNWwiGgjk;";

    public static Map<String, Object> withCookie() {
        return new HashMap<String, Object>() {{
            put("Cookie", (Object) FileUtil.read(new File("files/debug_cookie")));
        }};
    }

    public static Map<String, Object> withParameter() {
        return new HashMap<String, Object>() {{
            put("currentPageNo", (Object) "1");
            put("pageSize", (Object) "5");
        }};
    }

    public static void demo2() {
        IResult result = null;
//        result = HttpClientUtil.httpGetRequest("http://ieasy-dev.jd.com/policy/remote/list.htm?currentPageNo=1&pageSize=5");
        try {
            result = HttpClientUtil.httpGetRequest("http://ieasy-dev.jd.com/policy/remote/list.htm", withCookie(), withParameter());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static void main(String... args) {
        demo2();
    }
}  
