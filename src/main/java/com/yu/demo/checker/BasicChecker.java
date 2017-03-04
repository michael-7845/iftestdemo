package com.yu.demo.checker;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.IResult;

import static org.junit.Assert.*;

// check the response status code
public class BasicChecker implements ICheck {

	@Override
	public ICheckResult check(TestCase tc, IResult result) {
		assertEquals(tc.getStatus(), result.getStatusCode());
		return new ICheckResult() {
			@Override
			public boolean getStatus() {
				return (tc.getStatus() == result.getStatusCode());
			}

			@Override
			public String getMessage() {
				return String.format("BasicChecker: expect status code (%s), real status code (%s)",
						tc.getStatus(), result.getStatusCode());
			}
		};
	}

}
