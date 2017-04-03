package com.yu.demo.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import static com.yu.demo.util.MyProperties.*;
import static com.yu.demo.util.JSONUtil.*;

public class JSONComparor {
	
	public static void compareJson(JSONObject json1, JSONObject json2,String key) {
		Iterator<String> i = json1.keySet().iterator();
		
		while (i.hasNext()) {
			key = i.next();
			compareJson(json1.get(key), json2.get(key),key);
		}
	}
	
	public static void compareJson(Object json1,Object json2,String key) {
		if ( json1 instanceof JSONObject ) {
			compareJson((JSONObject) json1 ,(JSONObject) json2,key);
		}else if ( json1 instanceof JSONArray ) {
			compareJson((JSONArray) json1 ,(JSONArray) json2,key);
		}else if(json1 instanceof String ){
			compareJson((String) json1 ,(String) json2,key);
		}else {
			String json1Str = (json1==null) ? "" : json1.toString();
			String json2Str = (json2==null) ? "" : json2.toString();
			compareJson(json1Str, json2Str, key);
		}
	}
	
	public static void compareJson(JSONArray json1,JSONArray json2,String key) {
		Iterator i1= json1.iterator();
		Iterator i2= json2.iterator();
		while ( i1.hasNext()) {
			compareJson(i1.next(), i2.next(),key);
		}
	}
	
	public static void compareJson(String str1,String str2,String key) {
		if (!str1.equals(str2)) {
//			System.out.println("key:"+key+",json1:"+ str1 +",json2:"+str2);
			cmpResult.add(new String[] {str1, str2, key});
		}
	}
	
	private static List<String[]> cmpResult = new ArrayList<String[]>();
	
	public static List<String[]> getCmpResult() {
		return cmpResult;
	}

	public static void debugCmpResult() {
		for(String[] sa: cmpResult) {
			System.out.print(sa + ": ");
			for(String s: sa) {
				System.out.print(s + ", ");
			}
			System.out.println("");
		}
	}

	// m1.size()==m2.size(), m1.keys==m2.keys
	public static boolean compare(Map<String,Object> m1, Map<String,Object> m2) {
		cmpResult.clear();
		// 只有size和keySet相同才有比较的必要
		if( (m1.size() == m2.size()) && (m1.keySet().equals(m2.keySet())) ) {
			//逐个比较每项, 在CompareJson()中会把不相等的放到cmpResult中
			for(String key: m1.keySet()) {
//				System.out.println(String.format("%s(%s) : %s(%s)", 
//						key, key.getClass(), m1.get(key), m1.get(key).getClass()));
				compareJson(m1.get(key), m2.get(key), key);
			}
			//如果cmpResult为空, 表明没有不相等的项, 返回true, 表示相等
			if(cmpResult.size()==0)
				return true;
			else {
				System.out.println("michael: " + cmpResult);
				debugCmpResult();
				//如果cmpResult不为空, 在compareExcluding函数中进一步比较
				return compareExcluding();
			}

		}
		return false;	
	}

	//根据正则规则, 比较是否相等
	//提取出的组进行比较
	//如果想跳过比较, 正则表达式可以写为"()"即可
	private static boolean compareEx(String s1, String s2, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m1 = p.matcher(s1);
		Matcher m2 = p.matcher(s2);
		if (m1.find() && m2.find()) {
			return m1.group(1).equals(m2.group(1)); // equaled
		}
		return false; // unequaled
	}
	
	//所有不相等保存在cmpResult, 在本函数中逐一进行比较
	private static boolean compareExcluding() {
//		System.out.println(CaseConfig.exJson.getExcluding());
//		System.out.println(getCmpResult().size());
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		JsonExcludingConfig exJson = (JsonExcludingConfig) ctx.getBean("jsonExcludingConfig");
		
		for(String[] sa: cmpResult) {
			String s1 = sa[0], s2 = sa[1], key = sa[2], reg = "";
			//yukemin: 如果是申明了的例外, 那么获得正则规则, 继续比较;
			//如果没有申明的例外, 且不相等, 那么打印不等, 返回false, 表示不相等
			if(exJson.getExcluding().containsKey(key)) {
				reg = exJson.getExcluding().get(key);
			} else {
				System.out.println(String.format("no excluding, key[%s] - %s != %s", key, s1, s2));
				return false; //无例外,不相等
			}

			//compareEx比较, 根据正则规则提取出的组(部分内容),对组的值进行比较
			//如果组的值不等, 那么不满足预期, 返回false, 表示不相等
			if(!compareEx(s1, s2, reg)) {
				System.out.println(String.format("key[%s] - %s != %s", key, s1, s2));
				return false;
			}
		}
		return true;
	}
	
	public static void demo1() {
		String s1 = "{'data':{'author':{'chName':'吉の味','firstName':'Geeyah Yee','fullName':'吉の味','id':33,'img':'//img12.360buyimg.com/cooking/jfs/t2935/98/1616999791/4183/9c5a3515/578855b9Nfb584d0e.png','lastName':''},'cook':{'auditDesc':'审核中','auditFlag':0,'authorId':33,'authorName':'吉の味','focusSum':4,'focused':false,'id':118,'intro':'面拖蟹是上海名菜之一，也是最家常的六月黄做法。此菜讲究嫩、脆、鲜，蟹黄鲜嫩，肉质细软，汤汁下饭，回味无穷。','name':'鸡蛋面拖蟹','rectImg':'//img11.360buyimg.com/cooking/jfs/t2824/225/3260980579/158678/77b10c4c/578773ceN57e8d9e1.jpg','squareImg':'//img13.360buyimg.com/cooking/jfs/t2632/187/3315718963/107129/1366789a/578773c7Nda6e0cc3.jpg'},'skuIds':[1221976728,2108919,502232,2880536,2880540,937280,862560],'attrs':[{'attrId':100,'attrName':'难易度','attrVal':'中','id':470},{'attrId':200,'attrName':'常用分量','attrUnit':'人份','attrVal':'3','id':471},{'attrId':300,'attrName':'准备时间','attrUnit':'分钟','attrVal':'10','id':472},{'attrId':400,'attrName':'烹饪时长','attrUnit':'分钟','attrVal':'15','id':473}]},'success':true,'msg':'success'}";
		String s2 = "{'data':{'author':{'id':33,'chName':'吉の味','firstName':'Geeyah Yee','fullName':'吉の味','img':'//img13.360buyimg.com/cooking/jfs/t2935/98/1616999791/4183/9c5a3515/578855b9Nfb584d0e.png','lastName':''},'cook':{'auditDesc':'审核中','auditFlag':0,'authorId':33,'authorName':'吉の味','focusSum':4,'focused':false,'id':118,'intro':'面拖蟹是上海名菜之一，也是最家常的六月黄做法。此菜讲究嫩、脆、鲜，蟹黄鲜嫩，肉质细软，汤汁下饭，回味无穷。','name':'鸡蛋面拖蟹','rectImg':'//img16.360buyimg.com/cooking/jfs/t2824/225/3260980579/158678/77b10c4c/578773ceN57e8d9e1.jpg','squareImg':'//img18.360buyimg.com/cooking/jfs/t2632/187/3315718963/107129/1366789a/578773c7Nda6e0cc3.jpg'},'skuIds':[1221976728,2108919,502232,2880536,2880540,937280,862560],'attrs':[{'attrId':100,'attrName':'难易度','attrVal':'中','id':470},{'attrId':200,'attrName':'常用分量','attrUnit':'人份','attrVal':'3','id':471},{'attrId':300,'attrName':'准备时间','attrUnit':'分钟','attrVal':'10','id':472},{'attrId':400,'attrName':'烹饪时长','attrUnit':'分钟','id':473,'attrVal':'15'}]},'success':true,'msg':'success'}";
		String reg = "img\\d+(.*)$";
		Map<String,Object> o1 = json2Map(s1);
		Map<String,Object> o2 = json2Map(s2);
		
		if(compare(o1, o2)) {
			System.out.println("o1 == o2");
		} else {
			System.out.println("o1 != o2");
			for(String[] sarray: getCmpResult()) {
				System.out.println(String.format("key[%s] - %s : %s", 
						sarray[2], sarray[0], sarray[1]));
			}
		}
	}
	
	public static void main(String[] args) {
		demo1();
	}
	
}
