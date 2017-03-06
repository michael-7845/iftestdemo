package com.yu.demo.util;

import static com.yu.demo.util.MyProperties.SUT_HOST;
import static com.yu.demo.util.MyProperties.SUT_PORT;
import static com.yu.demo.util.MyProperties.SUT_PROTOCOL;
import static com.yu.demo.util.MyProperties.getValue;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyProperties {

	static public String SUT_PROTOCOL = "sut.protocol";
	static public String SUT_HOST = "sut.host";
	static public String SUT_PORT = "sut.port";
	
	static public String RESULTFOLDER_PROP = "chk.result.folder";
	static public String EXPECT_PROP = "chk.expect"; 
	static public String REAL_PROP = "chk.real";
	static public String RECORD_PROP = "chk.record";
	static public PropertyPlaceholderConfigurer config;
	
	public static String getValue(String name) {
		return SpringPropertyResourceReader.getProperty(name).trim();
	}
	
	public static boolean contains(String name) {
		return SpringPropertyResourceReader.containsProperty(name);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
