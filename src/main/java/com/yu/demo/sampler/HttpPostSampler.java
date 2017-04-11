package com.yu.demo.sampler;

import com.yu.demo.db.TestCase;
import com.yu.demo.util.HttpClientUtil;
import com.yu.demo.util.IResult;
import com.yu.demo.util.MyProperties;
import com.yu.demo.util.SpringPropertyResourceReader;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27.
 */
public class HttpPostSampler implements ISampler {

    private Map<String, Object> withCookie() {
        return new HashMap<String, Object>() {{
            put("Cookie", (Object) SpringPropertyResourceReader.getProperty("debug.cookie"));
//            put("Cookie", (Object) FileUtil.read(new File("files/debug_cookie")));
        }};
    }

    @Override
    public IResult request(TestCase tc) {
        URL url = MyProperties.makeUrl(tc.getApi());

        IResult result = null;
        try {
            result = HttpClientUtil.httpPostRequest(url.toString(), withCookie(), tc.getParamMap());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        HttpPostSampler s = new HttpPostSampler();

        TestCase tc = new TestCase();
        tc.setId(1);
        tc.setTcname("demo");
        tc.setApi("/group/remote/create");
        tc.setParams("policyId,json");
        tc.setP0("1");
        tc.setP1("{\"groupName\":\"lmn\",\"tagList\":[{\"tagId\":\"1007\",\"tagName\":\"女\"}],\"type\":1}");

        IResult result = s.request(tc);
        System.out.println(result);
    }
}
