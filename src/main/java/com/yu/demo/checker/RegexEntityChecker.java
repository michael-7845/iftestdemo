package com.yu.demo.checker;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.FileUtil;
import com.yu.demo.util.IResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2017/3/28.
 */
public class RegexEntityChecker extends EntityChecker {
    public boolean regexMatch(String search, String regex) {
        Matcher m = Pattern.compile(regex).matcher(search);
        if(m.find() && m.groupCount()>0) {
            return true;
        }
        return false;
    }

    public ICheckResult checkJson(TestCase tc, IResult result) {
        System.out.println("in contain entity checker");
        String real = result.getEntity();
        String expect = FileUtil.readExpect(tc.getApi(), tc.getTcname());
        boolean b = regexMatch(real, expect);

        return new ICheckResult() {
            @Override
            public boolean getStatus() {
                assertTrue(b);
                return b;
            }

            @Override
            public String getMessage() {
                return String.format("RegexEntityChecker: expect entity (%s), real entity (%s)",
                        expect, real);
            }
        };
    }
}
