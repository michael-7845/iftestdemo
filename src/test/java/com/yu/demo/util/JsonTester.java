package com.yu.demo.util;

import java.util.*;
import org.testng.annotations.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static com.yu.demo.util.JSONUtil.*;

public class JsonTester {
	
	private String s1 = "{'avBar':[{'barAge':-1442278496,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
	// == s1
	private String s1b = "{'avBar':[{'barAge':-1442278496,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
	// == s1: avBar[0]: consequence differnece
	private String s1c = "{'avBar':[{'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178','barAge':-1442278496},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
	// != s1: avBar[0].barAge != 
	private String s2 = "{'avBar':[{'barAge':-1442278495,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
	// != s1: delete avString.cc 
	private String s3 = "{'avBar':[{'barAge':-1442278496,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999,'vshort':128}";
	// != s1: delete 'vshort'
	private String s4 = "{'avBar':[{'barAge':-1442278496,'barDate':'2016-12-22 14:12:11','barName':'sss_0.7553178'},{'barAge':-46028450,'barDate':'2016-12-22 14:12:11','barName':'sss_0.9416827'}],'avString':['aaa','bbb','ccc'],'avboolean':[true,false,true,true],'avint':[1,2,3,4],'bar':{'barAge':2051276180,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8763819'},'dddd':'2016-12-22 14:12:11','listBar':[{'barAge':385060738,'barDate':'2016-12-22 14:12:11','barName':'sss_0.14227659'},{'barAge':698120777,'barDate':'2016-12-22 14:12:11','barName':'sss_0.8042798'},{'barAge':171325217,'barDate':'2016-12-22 14:12:11','barName':'sss_0.63253206'}],'listString':['listString1','listString2','listString3'],'map':{'x':'s11111x','y':'s22222y','z':'s33333z'},'vDate':'2016-12-22 14:12:11','vString':'vStringhehhehe','v_Date':'2016-12-22 14:12:11','vboolean':false,'vbyte':64,'vchar':'x','vdouble':22.203,'vfloat':12.1,'vint':65535,'vlong':9999999}";
	
	@Test
	public void testJson2Map() {
		String json = "{'p':'-1.00','m':'859.00','id':'J_954086','op':'459.00'}";
		Map<String,Object> o = json2Map(json);
		assertEquals(HashMap.class, o.getClass());
		assertThat(o, hasKey(equalTo("p")));  
		assertThat(o, hasValue(equalTo("-1.00")));  
		assertThat(o, hasKey(equalTo("op")));  
		assertThat(o, hasValue(equalTo("459.00")));
	}
	
	@Test
	public void testMapEquals() {
		Map<String,Object> o1 = json2Map(s1);
		Map<String,Object> o1b = json2Map(s1b);
		Map<String,Object> o1c = json2Map(s1c);
		Map<String,Object> o2 = json2Map(s2);
		Map<String,Object> o3 = json2Map(s3);
		Map<String,Object> o4 = json2Map(s4);
		
		assertTrue(o1.equals(o1b));
		assertTrue(o1.equals(o1c));
		assertTrue(o1b.equals(o1c));
		
		assertFalse(o1.equals(o2));
		assertFalse(o1.equals(o3));
		assertFalse(o1.equals(o4));
		assertFalse(o2.equals(o3));
		assertFalse(o2.equals(o4));
		assertFalse(o3.equals(o4));
	}
	
	@Test
	public void testJson2List() {
		String json = "[{'p':'-1.00','m':'859.00','id':'J_954086','op':'459.00'}]";
		List<Map<String,Object>> o = json2List(json);
		assertEquals(ArrayList.class, o.getClass());
		assertThat(o.get(0), hasKey(equalTo("p")));  
		assertThat(o.get(0), hasValue(equalTo("-1.00")));  
		assertThat(o.get(0), hasKey(equalTo("op")));  
		assertThat(o.get(0), hasValue(equalTo("459.00")));
	}
	
	private String ss1 = "[{'a':'-1.00','b':'0.00','c':'1.00'}, {'x':'1','y':'2','z':'3'}]";
	private String ss1b = "[{'c':'1.00', 'a':'-1.00','b':'0.00'}, {'x':'1','y':'2','z':'3'}]";
	private String ss2 = "[{'x':'1','y':'2','z':'3'}, {'a':'-1.00','b':'0.00','c':'1.00'}]";
	private String ss3 = "[{'a':'-2.00','b':'0.00','c':'1.00'}, {'x':'1','y':'2','z':'3'}]";
	
	@Test
	public void testListEquals() {
		List<Map<String,Object>> l1 = json2List(ss1);
		List<Map<String,Object>> l1b = json2List(ss1b);
		List<Map<String,Object>> l2 = json2List(ss2);
		List<Map<String,Object>> l3 = json2List(ss3);
		
		assertTrue(l1.equals(l1b));
		assertFalse(l1.equals(l2));
		assertFalse(l1.equals(l3));
		assertFalse(l2.equals(l3));
	}
}
