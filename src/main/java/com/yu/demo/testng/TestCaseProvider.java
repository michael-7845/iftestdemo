package com.yu.demo.testng;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.testng.annotations.DataProvider;

import com.yu.demo.db.TestCase;
import com.yu.demo.service.TestCaseService;

//@Service
@Deprecated
public class TestCaseProvider {
	
//	@Resource
//	private TestCaseService serv;
	
	private static TestCaseService serv;
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		serv = (TestCaseService) ctx.getBean("tcService");
	}
	
	@DataProvider
	public Object[][] allTestCase() {
//		List<TestCase> tcs = serv.all();
//		int size = tcs.size();
//		if(size > 0) {
//			Object[][] result = new Object[tcs.size()][];
//			for(int i=0; i<size; i++) {
//				result[i] = new Object[] { tcs.get(i) };
//			}
//			return result;
//		}
//		return null;
		
		TestCase tc = new TestCase();
		tc.setId(1);
		tc.setApi("/talent/find.html");
		tc.setTcname("findtalent");
		tc.setParams("a1,a2,a3");
		tc.setP0("123");
		tc.setP1("abc");
		tc.setP2("ABC");
		tc.setStatus(200);
		
		Object[][] result = new Object[1][];
		result[0] = new Object[] { tc };
		return result;
	}
	
	@DataProvider(name="byname")
	public Object[][] byTestName() {
		List<TestCase> tcs = serv.byTcName("");
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

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		TestCaseProvider prov = (TestCaseProvider) ctx.getBean("testCaseProvider");
//		TestCaseProvider prov = new TestCaseProvider();
		Object[][] result = prov.allTestCase();
		System.out.println(result.length);
		for(Object[] oarray: result) {
			System.out.println(oarray.length);
			for(Object o: oarray) {
				System.out.println(o);
			}
		}
	}

}
