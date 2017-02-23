package com.yu.demo.util;

import java.io.File;
//import com.tencent.stat.common.User;
//import org.apache.commons.lang.StringUtils;
//import java.security.Principal;
//import java.security.acl.Group;
//import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

/**
 Fastjson中的经常调用的方法：  避免使用org.apche.json的包。

 1 public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
 2 public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
 3 public static final  T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
 4 public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
 5 public static final  List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
 6 public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
 7 public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
 8 public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
 */

public class JSONUtil {
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2Map(String json){
		return JSON.parseObject(json, Map.class);
	}
	
	public static List<Map<String, Object>> json2List(String json){
		return JSON.parseObject(json, new TypeReference<List<Map<String,Object>>>(){});
	}
	
	public static Map<String,Object> loadMap(String api, String casename)  {
		return json2Map(FileUtil.readExpect(api, casename));
	}
	
	public static List<Map<String, Object>> loadList(String api, String casename)  {
		return json2List(FileUtil.readExpect(api, casename));
	}
}

