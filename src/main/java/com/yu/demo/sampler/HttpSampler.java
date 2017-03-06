package com.yu.demo.sampler;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.HttpClientUtil;
import com.yu.demo.util.IResult;
import com.yu.demo.util.MyProperties;

public class HttpSampler implements ISampler {

//	protected static String PROTOCOL = "http";
	
	@Override
	public IResult request(TestCase tc) {
//		URL url = null;
//		try {
//			url = new URL(PROTOCOL, Configuration.env.getConfig().get("host"), tc.getApi());
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
		URL url = MyProperties.makeUrl(tc.getApi());
		
		IResult result = null;
    	try {
			result = HttpClientUtil.httpGetRequest(url.toString(), tc.getParamMap());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
