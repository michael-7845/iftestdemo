package com.yu.demo.checker;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.IResult;

public interface ICheck {
	public ICheckResult check(TestCase tc, IResult result);
}
