package com.example.sportbetanalysis.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName: SpiderClient
 * @Description: get request and post request tools
 * @Author: wongwill
 * @Date: 2021/10/13 14:26
 **/
@Component
public class SpiderClient {
    public String doGet(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "error!";

        HttpGet httpGet = new HttpGet(url);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(20000)
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .setConnectTimeout(5000).build();
        httpGet.setConfig(requestConfig);

        try {
            response = client.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    result = EntityUtils.toString(httpEntity, "UTF-8");
                    EntityUtils.consume(httpEntity);
                    return result;
                }
            }
        } catch (IOException e) {
            System.out.println("失败任务：" + url);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
