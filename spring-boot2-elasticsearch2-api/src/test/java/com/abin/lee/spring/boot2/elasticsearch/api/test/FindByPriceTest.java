package com.abin.lee.spring.boot2.elasticsearch.api.test;

import com.abin.lee.spring.boot2.elasticsearch.common.util.HttpClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * Created by lee on 2019/3/15.
 */
public class FindByPriceTest {

    private static final String httpUrl = "http://localhost:6000/business/findByPriceBetween";


    @Test
    public void testFindByName() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            URIBuilder uriBuilder = new URIBuilder(httpUrl);

            uriBuilder.addParameter("startPrice", "11");
            uriBuilder.addParameter("endPrice", "555555");

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.toString());

            System.out.println("Executing request: " + httpGet.getRequestLine());
            HttpResponse response = httpClient.execute(httpGet);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }







}
