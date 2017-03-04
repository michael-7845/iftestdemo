package com.yu.demo.runner;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.yu.demo.db.TestCase;

public class AllRunner extends BaseRunner {
	@DataProvider
	public Object[][] allTestCase() {
		List<TestCase> tcs = Configuration.serv.all();
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
		runner = (TestCaseRunner) ctx.getBean("AllRunner");
	}
	
	@Test(dataProvider="allTestCase")
	public void run(TestCase tc){
		runByDefault(tc);
	}
}
	
//	private TestCaseProvider prov;
//	
//	@BeforeClass
//	public void init() {
//		ApplicationContext ctx =new ClassPathXmlApplicationContext("spring-config.xml");
//		this.prov = (TestCaseProvider) ctx.getBean("testCaseProvider");
//	}
	
//	@Test(dataProvider="allTestCase", dataProviderClass=TestCaseProvider.class)
//	@Test(dataProvider="allTestCase", dataProviderClass=TestCaseProvider.class)
//    public void testAllcases(TestCase tc){
//		System.out.println("in all cases()");
//        System.out.println(tc.toString());
//    }
	
//	@DataProvider
//    public Object[][] providerMethod(Method method){
//        Object[][] result = null;
//        if(method.getName().equals("testmethod1")){
//            result = new Object[][]{new Object[]{1}};
//        }else if(method.getName().equals("testmethod2")){
//            result = new Object[][]{new Object[]{2}};
//        }else{
//            result = new Object[][]{new Object[]{3}, new Object[]{4}, new Object[]{5}};
//        }
//        return result;
//    }
	
//	@Test(dataProvider="providerMethod")
//	@Test(dataProvider="providerMethod", dataProviderClass=my.lab.testng.DataProviderTester.class)
//	@Test
//    public void testmethod1(int param) {
//		System.out.println(DataProviderTester.class);
//		System.out.println("in demo provider()");
//        System.out.println(param);
//        assertEquals(4, param);
//    }

