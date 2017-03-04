package com.yu.demo.util;

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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
