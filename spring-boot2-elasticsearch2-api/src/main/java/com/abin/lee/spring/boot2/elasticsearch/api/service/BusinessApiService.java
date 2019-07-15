package com.abin.lee.spring.boot2.elasticsearch.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by lee on 2019/7/13.
 */
public interface BusinessApiService {

    void insertJson(String jsonString) throws ExecutionException, InterruptedException;

    void insertMap(Map<String, Object> jsonMap) throws ExecutionException, InterruptedException;

    void insertBuilder(String driver_id, String card_id, String phone, String car_num, String row_key, String timestamp, String log_type) throws ExecutionException, InterruptedException, IOException;

    void insertColumn(String driver_id, String card_id, String phone, String car_num, String row_key, String timestamp, String log_type) throws ExecutionException, InterruptedException;

    void insertBulkJson(String jsonString) throws ExecutionException, InterruptedException;


    void updateJson(String jsonString) throws ExecutionException, InterruptedException;

    void updateMap(Map<String, Object> jsonMap) throws ExecutionException, InterruptedException;

    void updateBuilder(String driver_id, String card_id, String phone, String car_num, String row_key, String timestamp, String log_type) throws ExecutionException, InterruptedException, IOException;

    void updateColumn(String driver_id, String card_id, String phone, String car_num, String row_key, String timestamp, String log_type) throws ExecutionException, InterruptedException;

    void upsertJson(String jsonString) throws ExecutionException, InterruptedException;



    void delete(String id) throws ExecutionException, InterruptedException;

    void exists(String id) throws ExecutionException, InterruptedException;


    List<Map<String, Object>> searchFunctions(String key, String value) throws ExecutionException, InterruptedException;










}
