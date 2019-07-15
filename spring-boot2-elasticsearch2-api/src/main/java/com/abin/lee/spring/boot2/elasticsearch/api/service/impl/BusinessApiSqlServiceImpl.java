package com.abin.lee.spring.boot2.elasticsearch.api.service.impl;

import com.abin.lee.spring.boot2.elasticsearch.api.conf.TransportApiClient;
import com.abin.lee.spring.boot2.elasticsearch.api.service.BusinessApiSqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lee on 2019/7/13.
 */
@Slf4j
@Service
public class BusinessApiSqlServiceImpl implements BusinessApiSqlService {

    @Resource
    TransportApiClient transportApiClient;
    @Value("${elasticsearch.index}")
    private String elasticSearchIndex;
    @Value("${elasticsearch.type}")
    private String elasticSearchType;








}
