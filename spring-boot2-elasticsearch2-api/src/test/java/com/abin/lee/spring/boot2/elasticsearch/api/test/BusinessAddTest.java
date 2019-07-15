package com.abin.lee.spring.boot2.elasticsearch.api.test;

import com.abin.lee.spring.boot2.elasticsearch.common.util.HttpClientUtil;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2019/3/15.
 */
public class BusinessAddTest {

    private static final String httpUrl = "http://localhost:6000/business/addBusiness";

    @Test
    public void testFindByParams() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            HttpPost httpPost = new HttpPost(httpUrl);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            nvps.add(new BasicNameValuePair("name", "白银"));
            nvps.add(new BasicNameValuePair("price", "12345"));

            httpPost.addHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.toString());
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

            System.out.println("Executing request: " + httpPost.getRequestLine());
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Test
    public void testBusinessAdd() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            URIBuilder uriBuilder = new URIBuilder(httpUrl);


            uriBuilder.addParameter("queryInfo", "123456789");
            uriBuilder.addParameter("type", "order");
            uriBuilder.addParameter("startTime", "2019-07-01 18:15:53");
            uriBuilder.addParameter("endTime", "2019-07-12 18:15:53");


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
