package com.yu.demo.runner;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yu.demo.checker.ICheck;
import com.yu.demo.checker.ICheckResult;
import com.yu.demo.db.TestCase;
import com.yu.demo.service.TestCaseService;
import com.yu.demo.util.Env;
import com.yu.demo.util.IResult;
import com.yu.demo.util.TestcaseSelector;

public class BaseRunner {
	
	protected IRunner runner;
	
	protected IResult sample(TestCase tc) {
		return runner.getSampler().request(tc);
	}

	protected void check(TestCase tc, IResult result) {
		int checkStep = 0;
		for(ICheck c: runner.getChecklist()) {
			ICheckResult r = c.check(tc, result);
			System.out.println(String.format("tcname: %10s - checker[%2d] | pass: %s, info: %s", 
					tc.getTcname(), ++checkStep, r.getStatus(), r.getMessage()));
			assertTrue(r.getStatus());
		}
	}
	
	protected void runByDefault(TestCase tc) {
		IResult result = sample(tc);
		check(tc, result);
	}
	
}