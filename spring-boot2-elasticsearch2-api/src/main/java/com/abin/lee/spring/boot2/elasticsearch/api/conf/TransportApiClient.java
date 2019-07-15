package com.abin.lee.spring.boot2.elasticsearch.api.conf;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * https://www.elastic.co/guide/en/elasticsearch/client/index.html
 *
 **/
@Slf4j
@Component
public class TransportApiClient {
    private static final Logger logger = LoggerFactory.getLogger(TransportApiClient.class);


    private static TransportClient client;

    @Value("${elasticsearch.host}")
    private String elasticSearchHost;
    @Value("${elasticsearch.port}")
    private Integer elasticSearchPort;


    @PostConstruct
    public void init() throws UnknownHostException {
        logger.info("TransportApiClient--init--start");
        if (Strings.isNullOrEmpty(elasticSearchHost) || null == elasticSearchPort){
            throw new IllegalArgumentException("elasticsearch host ["+elasticSearchHost+"] error"+" or elasticSearchPort is null");
        }

        Settings settings = Settings.builder()
                .put("client.transport.ignore_cluster_name", true)
//                .put("cluster.name", "elaasticsearch")
                .put("client.transport.sniff", true)
                .build();

        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(elasticSearchHost), elasticSearchPort));

        logger.info("TransportApiClient--init--end");
    }


    public TransportClient getTransportClient() {
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
