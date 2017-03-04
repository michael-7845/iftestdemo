package com.yu.demo.util;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.annotations.*;

public class TestHttpClientUtil {
	@Test
	public void testRequest() {
		IResult result = null;
		result = HttpClientUtil.httpGetRequest("http://p.3.cn/prices/mgets?skuIds=J_954086&type=1");
		System.out.println(result);
		assertThat(result.getStatusCode(), equalTo(200));
		assertThat(result.getEntityLength(), equalTo(-1));
	}
	
	@Test
	public void testRequestWithParams() {
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
		System.out.println(result);
	}

}
