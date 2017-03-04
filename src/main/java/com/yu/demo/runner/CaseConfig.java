package com.yu.demo.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yu.demo.checker.JsonExcludingConfig;
import com.yu.demo.util.TestcaseSelector;

public class CaseConfig {
	public static TestcaseSelector tcSelector;
	public static JsonExcludingConfig exJson;
	static {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		tcSelector = (TestcaseSelector) ctx.getBean("testcaseSelector");
		exJson = (JsonExcludingConfig) ctx.getBean("jsonExcludingConfig");
	}
}
