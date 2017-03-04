package com.yu.demo.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyProperties {

	static public String DEFAULT_RESULT_FOLDER_NAME = "result";
	static public String RESULTFOLDER_PROP = "chk.result.folder";
	static public String EXPECT_PROP = "chk.expect"; 
	static public String REAL_PROP = "chk.real";
	static public String RECORD_PROP = "chk.record";
	static public PropertyPlaceholderConfigurer config;
	
	public static String getValue(String name) {
		return SpringPropertyResourceReader.getProperty(name);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
