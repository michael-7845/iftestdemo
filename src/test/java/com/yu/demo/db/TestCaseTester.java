package com.yu.demo.db;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;

import java.util.Map;

import org.testng.annotations.Test;

public class TestCaseTester {

	@Test
	public void testGetParamList() {
		TestCase tc = new TestCase();
		tc.setId(1);
		tc.setApi("/talent/find.html");
		tc.setTcname("findtalent");
		tc.setParams("a1,a2,a3");
		tc.setP0("123");
		tc.setP1("abc");
		tc.setP2("ABC");
		tc.setStatus(200);
//		System.out.println(tc);
		
		Map<String,Object> m = tc.getParamMap();
//		System.out.println(m);
		assertThat(m, hasEntry("a1", "123"));
		assertThat(m, hasEntry("a2", "abc"));
		assertThat(m, hasEntry("a3", "ABC"));
	}
}
