package com.mins.httpclient.http.impl;

import com.mins.httpclient.http.HttpClientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/5/16
 * No struggle, talent how to match the willfulness.
 * Description: HttpClient逻辑实现类
 */
@Service
@Slf4j
public class HttpClientServiceImpl implements HttpClientService {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    @Override
    public String doGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }

    @Override
    public String doGet(String url, Map<String, Object> map) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());
    }

    @Override
    public String doPost(String url, String json) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);

        if (json != null) {
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
        }

        try (CloseableHttpResponse response = this.httpClient.execute(httpPost)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
