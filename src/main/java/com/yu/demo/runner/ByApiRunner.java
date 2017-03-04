package com.yu.demo.runner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.yu.demo.db.TestCase;
import com.yu.demo.util.Configuration;

public class ByApiRunner extends BaseRunner {
	
	@DataProvider
	public Object[][] byApiTestCase() {
		List<TestCase> tcs = new ArrayList<TestCase>();
		for(String api: CaseConfig.tcSelector.getApiList()) {
			tcs.addAll(Configuration.serv.byApi(api));
		}
		int size = tcs.size();
		if(size > 0) {
			Object[][] result = new Object[tcs.size()][];
			for(int i=0; i<size; i++) {
				result[i] = new Object[] { tcs.get(i) };
			}
			return result;
		}
		return null;
	}
	
	@BeforeClass
	public void init() {
		ApplicationContext ctx =new ClassPathXmlApplicationContext("spring-config.xml");
		runner = (TestCaseRunner) ctx.getBean("byApiRunner");
	}
	
	@Test(dataProvider="byApiTestCase")
	public void run(TestCase tc){
		runByDefault(tc);
	}
}
