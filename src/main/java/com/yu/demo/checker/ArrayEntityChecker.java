package com.yu.demo.checker;

import static com.yu.demo.util.JSONUtil.json2List;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.IResult;
import com.yu.demo.util.JSONUtil;

public class ArrayEntityChecker extends EntityChecker {
	public ICheckResult checkJson(TestCase tc, IResult result) {
		System.out.println("in array entity checker");
		List<Map<String, Object>> real = json2List(result.getEntity());
		List<Map<String, Object>> expect = JSONUtil.loadList(tc.getApi(), tc.getTcname());
		boolean b = real.equals(expect);
		
		return new ICheckResult() {
			@Override
			public boolean getStatus() {
				assertTrue(b);
				return b;
			}
			@Override
			public String getMessage() {
				return String.format("ArrayEntityChecker: expect entity (%s), real entity (%s)",
						expect, real);
			}
		};
	}
}
