package com.yu.demo.checker;

import static org.junit.Assert.*;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.IResult;
import com.yu.demo.util.SpringPropertyResourceReader;

import static com.yu.demo.util.FileUtil.*;

public class EntityChecker implements ICheck {
	
	protected ICheckResult checkJson(TestCase tc, IResult result) {
		return new ICheckResult() {
			@Override
			public boolean getStatus() {
				return true;
			}
			@Override
			public String getMessage() {
				return "Dumb Entity Checker";
			}
		};
	}

	@Override
	public ICheckResult check(TestCase tc, IResult result) {
		String mode = SpringPropertyResourceReader.getProperty("chk.mod"); //Configuration.env.getConfig().get("mode");
		
		if(mode.equals("recorder")) {
			writeRecord(tc.getApi(), tc.getTcname(), result.getEntity());
			return new ICheckResult() {
				@Override
				public boolean getStatus() {
					return true;
				}
				@Override
				public String getMessage() {
					return "Entity Checker recorder mode";
				}
			};
		} else if(mode.equals("checker")) {
			writeReal(tc.getApi(), tc.getTcname(), result.getEntity());
			return checkJson(tc, result);
		}
		
		return null;
	}
}
