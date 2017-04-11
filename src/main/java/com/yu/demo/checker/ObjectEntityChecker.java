package com.yu.demo.checker;

import static com.yu.demo.util.JSONUtil.json2Map;

import java.util.Map;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.IResult;
import com.yu.demo.util.JSONUtil;

import static org.junit.Assert.*;

public class ObjectEntityChecker extends EntityChecker {
	
	@Deprecated
	private void printMap(Map<String,Object> m) {
		System.out.println("length: " + m.size());
		for(String key: m.keySet()) {
			System.out.println(String.format("key: %s, value: %s", key, m.get(key)));
			System.out.println(String.format("key type: %s, value type: %s", 
					key.getClass(), m.get(key).getClass()));
		}
	}
	
	@Deprecated
	private void cmpMap(Map<String,Object> m1, Map<String,Object> m2) {
		System.out.println("m1 length == m2 length: " + (m1.size()==m2.size()));
		System.out.println("m1 msg == m2 msg: " + (m1.get("msg").equals(m2.get("msg"))));
		System.out.println("m1 success == m2 success: " + (m1.get("success")==m2.get("success")));
		System.out.println("m1 data == m2 data: " + (m1.get("data").equals(m2.get("data"))));
	}

	public ICheckResult checkJson(TestCase tc, IResult result) {
		Map<String,Object> real = json2Map(result.getEntity());
		Map<String,Object> expect = JSONUtil.loadMap(tc.getApi(), tc.getTcname());
//		printMap(real);
//		printMap(expect);
//		cmpMap(real, expect);
		System.out.println("real: " + real);
		System.out.println("expect: " + expect);
		boolean finalB = JSONComparor.compare(expect, real);
//		System.out.println(finalB);
		
		return new ICheckResult() {
			@Override
			public boolean getStatus() {
				assertTrue(finalB);
				return finalB;
			}
			@Override
			public String getMessage() {
				return String.format("ObjectEntityChecker: expect entity (%s), real entity (%s)",
						expect, real);
			}
		};
	}
}
