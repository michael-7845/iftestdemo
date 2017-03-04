package com.yu.demo.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yu.demo.service.TestCaseService;
import static com.yu.demo.util.MyProperties.*;

public class Configuration {
	public static TestCaseService serv;
//	public static Env env;
//	public static TestcaseSelector tcSelector;
	static {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		serv = (TestCaseService) ctx.getBean("tcService");
//		env = (Env) ctx.getBean("env");
//		tcSelector = (TestcaseSelector) ctx.getBean("testcaseSelector");
	}
	
	public static URL makeUrl(String api) {
		URL url = null;
		try {
			url = new URL(getValue(SUT_PROTOCOL), 
					getValue(SUT_HOST), 
					Integer.valueOf(getValue(SUT_PORT)),
					api);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
//	public static String getConfigValue(String key) {
//		if(env.getConfig().containsKey(key)) {
//			return env.getConfig().get(key);
//		}
//		return "";
//	}
//	
//	public static int getConfigInt(String key) {
//		if(env.getConfig().containsKey(key)) {
//			return Integer.valueOf(env.getConfig().get(key));
//		}
//		return (Integer)null;
//	}
}
