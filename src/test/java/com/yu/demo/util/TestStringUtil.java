package com.yu.demo.util;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2017/3/30.
 */
public class TestStringUtil {
    @Test
    public void testRemoveNonPrinted() {
        String s1 = "a  \r  \t  \n   b";
        String s2 = "a       b";
        String s3 = "a\r\r\rb";
        String s4 = "a\t\t\tb";
        String s5 = "a\n\n\nb";
        String s6 = " \r\t\n a  \r  \t  \n   b \r\t\n ";
        String s7 = "   a  \r  \t  \n   b   ";
        String s8 = " \r\t\n ab \r\t\n ";
        String expect = "ab";

        assertEquals(StringUtil.removeNonPrinted(s1), expect);
        assertEquals(StringUtil.removeNonPrinted(s2), expect);
        assertEquals(StringUtil.removeNonPrinted(s3), expect);
        assertEquals(StringUtil.removeNonPrinted(s4), expect);
        assertEquals(StringUtil.removeNonPrinted(s5), expect);
        assertEquals(StringUtil.removeNonPrinted(s6), expect);
        assertEquals(StringUtil.removeNonPrinted(s7), expect);
        assertEquals(StringUtil.removeNonPrinted(s8), expect);
    }
}
