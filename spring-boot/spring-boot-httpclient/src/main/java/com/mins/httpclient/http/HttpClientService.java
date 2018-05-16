package com.mins.httpclient.http;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/5/16
 * No struggle, talent how to match the willfulness.
 * Description: HttpClient服务接口
 */
public interface HttpClientService {

    /**
     * 没有参数的GET请求
     *
     * @param url url
     * @return String
     * @throws IOException IOException
     */
    String doGet(String url) throws IOException;

    /**
     * 带参数的GET请求
     *
     * @param url url
     * @param map 参数map
     * @return String
     * @throws URISyntaxException URISyntaxException
     * @throws IOException        IOException
     */
    String doGet(String url, Map<String, Object> map) throws URISyntaxException, IOException;

    /**
     * 带json参数的POST请求
     *
     * @param url  url
     * @param json json字符串
     * @return String
     */
    String doPost(String url, String json) throws IOException;
}
