package com.mins.httpclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mins.httpclient.http.HttpClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/5/16
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientTest {

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private Gson gson;

    @Test
    public void testDoGet() throws IOException {
        System.out.println(httpClientService.doGet("http://minsming.com"));
    }

    @Test
    public void testDoGetWithParam() throws IOException, URISyntaxException {
        Map<String, Object> map = new HashMap<>();
        map.put("location", "22.9310946,113.3063377");
        map.put("output", "json");
        map.put("pois", 1);
        map.put("ak", "NCwKCmGDdMNT8oLCSezS9vXib0bGRp6e");
        String result = httpClientService.doGet("http://api.map.baidu.com/geocoder/v2/", map);
        System.out.println(result);

        Map<String, Object> resultMap = gson.fromJson(result, new TypeToken<Map<String, Object>>() {
        }.getType());System.out.println(resultMap.get("result"));
        Map<String, Object> map1 = (Map<String, Object>) resultMap.get("result");
        System.out.println(map1.get("addressComponent"));
        Map<String, Object> map2 = (Map<String, Object>) map1.get("addressComponent");
        System.out.println(map2.get("street"));
    }
}
