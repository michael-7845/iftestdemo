package com.yu.demo.checker;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.IResult;

import com.yu.demo.util.FileUtil;
import com.yu.demo.util.StringUtil;

import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2017/3/28.
 */
public class ContainEntityChecker extends EntityChecker {

    public ICheckResult checkJson(TestCase tc, IResult result) {
        System.out.println("in contain entity checker");
        String real = StringUtil.removeNonPrinted(result.getEntity());
        String expect = StringUtil.removeNonPrinted(
                FileUtil.readExpect(tc.getApi(), tc.getTcname(), false));
//        System.out.println("real: " + real);
//        System.out.println("expect: " + expect);
        boolean b = real.indexOf(expect)>=0 ? true : false;

        return new ICheckResult() {
            @Override
            public boolean getStatus() {
                assertTrue(b);
                return b;
            }

            @Override
            public String getMessage() {
                return String.format("ContainEntityChecker: expect entity (%s), real entity (%s)",
                        expect, real);
            }
        };
    }
}
