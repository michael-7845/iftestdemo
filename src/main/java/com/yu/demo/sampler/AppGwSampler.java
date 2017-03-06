package com.yu.demo.sampler;

import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.yu.demo.db.TestCase;
import com.yu.demo.util.IResult;
import com.yu.demo.util.MyProperties;

public class AppGwSampler implements ISampler {
	
	private static RestTemplate template = new RestTemplate();

    static String queryFromApi(String funcId, Map<String, Object> body) {
        Map<String, Object> param = new TreeMap<String, Object>();
        param.put("sign", "");
        param.put("functionId", funcId);
        param.put("body", JSON.toJSONString(body));
        param.put("client", "apple");
        param.put("clientVersion", "5.6.0");
        param.put("st", "1482486813720");
        param.put("sv", "100");
        param.put("uuid", "9FC38545FD6745268A9ACD6270469D96");
        param.put("openudid", "");
        param.put("keyListStr", "functionId;body;uuid;client;clientVersion;");
        String ret = template.postForObject("http://192.168.144.64:8085/WebSignature/validate/", param, String.class);
        String signedStr = JSON.parseObject(ret).getString("signedStr");
        param.put("sign", signedStr);
        param.remove("openudid");
        param.remove("keyListStr");
//        String urlTpl = "http://api.m.jd.com/client.action?functionId={functionId}&body={body}&client={client}&clientVersion={clientVersion}&" +
//                "st={st}&sv={sv}&uuid={uuid}&sign={sign}";
        URL urlpre = MyProperties.makeUrl("client.action");
        String urlTpl = urlpre.toString() + "?functionId={functionId}&body={body}&client={client}&clientVersion={clientVersion}&" +
                "st={st}&sv={sv}&uuid={uuid}&sign={sign}";
        String apiResult = template.getForObject(urlTpl, String.class, param);
        return apiResult;
    }
    
	@Override
	public IResult request(TestCase tc) {
		return new IResult() {
			@Override
			public int getStatusCode() {
				return 0;
			}
			@Override
			public long getEntityLength() {
				return 0;
			}
			@Override
			public String getEntity() {
				String entity = queryFromApi(tc.getApi(), tc.getParamMap());
				return entity;
			}
		};
	}

}
