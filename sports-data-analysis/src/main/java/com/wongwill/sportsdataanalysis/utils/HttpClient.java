package com.wongwill.sportsdataanalysis.utils;

import com.wongwill.sportsdataanalysis.entity.CompanyData;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {
    public static String doGet(String url) {
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

    public static String crawler(String url) {
//        String html = doGet(url);
        Connection conn = Jsoup.connect(url);
        Document doc = null;
        try {
            doc = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements td11 = doc.select("#td_11");
        Elements td12 = doc.select("#td_12");
        Elements td13 = doc.select("#td_13");


        List<CompanyData> companyDataList = new ArrayList<>();
        for (int i = 0; i<td11.size();i++){
            CompanyData companyData = new CompanyData();
            companyData.setHomeVar(Float.parseFloat(td11.get(i).text()));
            companyData.setIndex(Float.parseFloat(td12.get(i).text()));
            companyData.setAwayVar(Float.parseFloat(td13.get(i).text()));
            companyDataList.add(companyData);
            System.out.println(companyData);
        }

        return null;
    }

    public static void main(String[] args) {
        crawler("http://nba.win007.com/odds/AsianOdds_n.aspx?id=436069");
    }



}
