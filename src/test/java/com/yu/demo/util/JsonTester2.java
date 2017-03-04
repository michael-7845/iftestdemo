package com.yu.demo.util;

import static com.yu.demo.util.JSONUtil.json2Map;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;

import org.testng.annotations.Test;

public class JsonTester2 {
	private static String s1 = "{'data':{'author':{'chName':'吉の味','firstName':'Geeyah Yee','fullName':'吉の味','id':33,'img':'//img12.360buyimg.com/cooking/jfs/t2935/98/1616999791/4183/9c5a3515/578855b9Nfb584d0e.png','lastName':''},'cook':{'auditDesc':'审核中','auditFlag':0,'authorId':33,'authorName':'吉の味','focusSum':4,'focused':false,'id':118,'intro':'面拖蟹是上海名菜之一，也是最家常的六月黄做法。此菜讲究嫩、脆、鲜，蟹黄鲜嫩，肉质细软，汤汁下饭，回味无穷。','name':'鸡蛋面拖蟹','rectImg':'//img11.360buyimg.com/cooking/jfs/t2824/225/3260980579/158678/77b10c4c/578773ceN57e8d9e1.jpg','squareImg':'//img13.360buyimg.com/cooking/jfs/t2632/187/3315718963/107129/1366789a/578773c7Nda6e0cc3.jpg'},'skuIds':[1221976728,2108919,502232,2880536,2880540,937280,862560],'attrs':[{'attrId':100,'attrName':'难易度','attrVal':'中','id':470},{'attrId':200,'attrName':'常用分量','attrUnit':'人份','attrVal':'3','id':471},{'attrId':300,'attrName':'准备时间','attrUnit':'分钟','attrVal':'10','id':472},{'attrId':400,'attrName':'烹饪时长','attrUnit':'分钟','attrVal':'15','id':473}]},'success':true,'msg':'success'}";
	private static String s2 = "{'data':{'author':{'id':33,'chName':'吉の味','firstName':'Geeyah Yee','fullName':'吉の味','img':'//img13.360buyimg.com/cooking/jfs/t2935/98/1616999791/4183/9c5a3515/578855b9Nfb584d0e.png','lastName':''},'cook':{'auditDesc':'审核中','auditFlag':0,'authorId':33,'authorName':'吉の味','focusSum':4,'focused':false,'id':118,'intro':'面拖蟹是上海名菜之一，也是最家常的六月黄做法。此菜讲究嫩、脆、鲜，蟹黄鲜嫩，肉质细软，汤汁下饭，回味无穷。','name':'鸡蛋面拖蟹','rectImg':'//img11.360buyimg.com/cooking/jfs/t2824/225/3260980579/158678/77b10c4c/578773ceN57e8d9e1.jpg','squareImg':'//img13.360buyimg.com/cooking/jfs/t2632/187/3315718963/107129/1366789a/578773c7Nda6e0cc3.jpg'},'skuIds':[1221976728,2108919,502232,2880536,2880540,937280,862560],'attrs':[{'attrId':100,'attrName':'难易度','attrVal':'中','id':470},{'attrId':200,'attrName':'常用分量','attrUnit':'人份','attrVal':'3','id':471},{'attrId':300,'attrName':'准备时间','attrUnit':'分钟','attrVal':'10','id':472},{'attrId':400,'attrName':'烹饪时长','attrUnit':'分钟','id':473,'attrVal':'15'}]},'success':true,'msg':'success'}";
	
	private static void convert(String s) {
		Map<String,Object> o1 = json2Map(s1);
		Map<String,Object> o2 = json2Map(s2);
//		File f = FileUtil.expectFile("cookDetailMainInfo", "cook001");
//		System.out.println(f.getAbsolutePath());
//		String expect = FileUtil.readExpect("cookDetailMainInfo", "cook001");
//		System.out.println(expect);
//		System.out.println(s1);
//		Map<String,Object> o2 = JSONUtil.json2Map(expect); //loadMap("cookDetailMainInfo", "cook001");
//		
		System.out.println(o1.get("data"));
		System.out.println(o1.get("data").getClass());
		System.out.println(o1.get("data"));
		System.out.println(o1.get("data").getClass());
		System.out.println(o1.get("msg").equals(o2.get("msg")));
		System.out.println(o1.get("success").equals(o2.get("success")));
		System.out.println(o1.get("data").equals(o2.get("data")));
		System.out.println(o1.equals(o2));
		
		
	}
	
	@Test
	public void testMapEquals() {
		Map<String,Object> o1 = json2Map(s1);
//		Map<String,Object> o1b = json2Map(s1b);
//		Map<String,Object> o1c = json2Map(s1c);
		Map<String,Object> o2 = json2Map(s1);
//		Map<String,Object> o3 = json2Map(s3);
//		Map<String,Object> o4 = json2Map(s4);
//		
//		assertTrue(o1.equals(o1b));
//		assertTrue(o1.equals(o1c));
//		assertTrue(o1b.equals(o1c));
//		
		assertTrue(o1.equals(o2));
//		assertFalse(o1.equals(o3));
//		assertFalse(o1.equals(o4));
//		assertFalse(o2.equals(o3));
//		assertFalse(o2.equals(o4));
//		assertFalse(o3.equals(o4));
	}
	
	public static void main(String[] args) {
		convert(s1);
	}
}
