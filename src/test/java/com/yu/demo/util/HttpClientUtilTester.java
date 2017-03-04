package com.yu.demo.util;

import org.testng.annotations.Test;

import static com.yu.demo.util.JSONUtil.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtilTester {

	@Test
	public void testGetUrl() {
		IResult result = HttpClientUtil.httpGetRequest("http://p.3.cn/prices/mgets?skuIds=J_954086&type=1");
//    	System.out.println(result);
    	assertEquals(200, result.getStatusCode());
    	
    	List<Map<String,Object>> real = json2List(result.getEntity());
    	List<Map<String,Object>> exp = json2List("[{'id':'J_954086','p':'-1.00','m':'859.00','op':'459.00'}]");
		assertTrue(exp.equals(real));
	}
	
	@Test
	public void testGetUrlParams() {
		IResult result = null;
    	Map<String, Object> params = new HashMap<String, Object>() {{
    		put("skuIds", (Object)"J_954086");
    		put("type", (Object)"1");
    	}};
    	try {
			result = HttpClientUtil.httpGetRequest("http://p.3.cn/prices/mgets", params);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
//    	System.out.println(result);
    	
    	assertEquals(200, result.getStatusCode());
    	
    	List<Map<String,Object>> real = json2List(result.getEntity());
    	List<Map<String,Object>> exp = json2List("[{'m':'859.00','id':'J_954086','p':'-1.00','op':'459.00'}]");
		assertTrue(exp.equals(real));
	}
	
}
