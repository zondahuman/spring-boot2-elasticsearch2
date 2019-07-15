package com.abin.lee.spring.boot2.elasticsearch.api.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * https://www.elastic.co/guide/en/elasticsearch/client/index.html
 *
 **/

@Slf4j
@Component
public class TransportRestClient {
    private static final Logger logger = LoggerFactory.getLogger(TransportRestClient.class);


    private static RestHighLevelClient client;

    @Value("${elasticsearch.host}")
    private String elasticSearchHost;
    @Value("${elasticsearch.port}")
    private Integer elasticSearchPort;




    @PostConstruct
    public void init() throws UnknownHostException {
        logger.info("TransportRestClient--init--start");
        if (Strings.isNullOrEmpty(elasticSearchHost) || null == elasticSearchPort){
            throw new IllegalArgumentException("elasticsearch host ["+elasticSearchHost+"] error"+" or elasticSearchPort is null");
        }

        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        logger.info("TransportRestClient--init--end");
    }

    public RestHighLevelClient getTransportClient() {
        return client;
    }

    @PreDestroy
    private void destroy() throws IOException {
        if (client!=null){
            client.close();
        }
    }

    public String getElasticSearchHost() {
        return elasticSearchHost;
    }

    public void setElasticSearchHost(String elasticSearchHost) {
        this.elasticSearchHost = elasticSearchHost;
    }

    public int getElasticSearchPort() {
        return elasticSearchPort;
    }

    public void setElasticSearchPort(int elasticSearchPort) {
        this.elasticSearchPort = elasticSearchPort;
    }
}
