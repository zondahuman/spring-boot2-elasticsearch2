package com.abin.lee.spring.boot2.elasticsearch.api.controller;

import com.abin.lee.spring.boot2.elasticsearch.api.service.BusinessApiService;
import com.abin.lee.spring.boot2.elasticsearch.common.base.BaseExtends;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by abin on 2019/7/13.
 */
@RestController
@RequestMapping("/business")
@Slf4j
@Api("BusinessController--api")
public class BusinessApiController {

    @Resource
    BusinessApiService businessApiService ;

    @ApiOperation(value = "insertJson", notes = "insertJson")
    @PostMapping(value = "/insertJson")
    public String insertJson(String jsonString) {
        log.info("insertJson--jsonString=" + jsonString);
        try {

            this.businessApiService.insertJson(jsonString);
        } catch (Exception e) {
            log.error("insertJson--jsonString=" + jsonString  + "e=" + e);
            return BaseExtends.error();
        }
        return BaseExtends.success();
    }


    @ApiOperation(value = "searchFunctions", notes = "searchFunctions")
    @GetMapping(value = "/searchFunctions")
    public String searchFunctions(String key, String value) {
        log.info("searchFunctions--key=" + key);
        List<Map<String, Object>> resultList = null;
        try {
            resultList = this.businessApiService.searchFunctions(key, value);
        } catch (Exception e) {
            log.error("searchFunctions--key=" + key + "e=" + e);
            return BaseExtends.error();
        }
        return BaseExtends.success(resultList);
    }


    @ApiOperation(value = "insertBulkJson", notes = "insertBulkJson")
    @PostMapping(value = "/insertBulkJson")
    public String insertBulkJson(String jsonString) {
        log.info("insertBulkJson--jsonString=" + jsonString);
        try {

            this.businessApiService.insertBulkJson(jsonString);
        } catch (Exception e) {
            log.error("insertBulkJson--jsonString=" + jsonString  + "e=" + e);
            return BaseExtends.error();
        }
        return BaseExtends.success();
    }


}
