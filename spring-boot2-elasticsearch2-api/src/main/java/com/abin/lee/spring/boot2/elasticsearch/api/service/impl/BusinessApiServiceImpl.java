package com.abin.lee.spring.boot2.elasticsearch.api.service.impl;

import com.abin.lee.spring.boot2.elasticsearch.api.conf.TransportApiClient;
import com.abin.lee.spring.boot2.elasticsearch.api.service.BusinessApiService;
import com.abin.lee.spring.boot2.elasticsearch.common.generator.SnowflakeIdWorker;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lee on 2019/7/13.
 */
@Slf4j
@Service
public class BusinessApiServiceImpl implements BusinessApiService {

    @Resource
    TransportApiClient transportApiClient;
    @Value("${elasticsearch.index}")
    private String elasticSearchIndex;
    @Value("${elasticsearch.type}")
    private String elasticSearchType;


    @Override
    public void insertJson(String jsonString) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        IndexRequest request = new IndexRequest(elasticSearchIndex, elasticSearchType, id + StringUtils.EMPTY).source(jsonString)
                .opType(DocWriteRequest.OpType.CREATE);
        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("indexResponse=" + indexResponse.toString());
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("indexResponse=" + indexResponse.toString());
        }
    }

    @Override
    public void insertMap(Map<String, Object> jsonMap) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        IndexRequest request = new IndexRequest(elasticSearchIndex, elasticSearchType).id(id + StringUtils.EMPTY).source(jsonMap)
                .opType(DocWriteRequest.OpType.CREATE);
        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("indexResponse=" + indexResponse.toString());
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("indexResponse=" + indexResponse.toString());
        }
    }

    @Override
    public void insertBuilder(String driver_id, String card_id, String phone, String car_num, String row_key, String timestamp, String log_type) throws ExecutionException, InterruptedException, IOException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("driver_id", driver_id);
            builder.timeField("card_id", card_id);
            builder.field("phone", phone);
            builder.field("car_num", car_num);
            builder.field("row_key", row_key);
            builder.field("timestamp", timestamp);
            builder.field("log_type", log_type);
        }
        builder.endObject();


        IndexRequest request = new IndexRequest(elasticSearchIndex, elasticSearchType).id(id + StringUtils.EMPTY).source(builder)
                .opType(DocWriteRequest.OpType.CREATE);
        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("indexResponse=" + indexResponse.toString());
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("indexResponse=" + indexResponse.toString());
        }
    }

    @Override
    public void insertColumn(String driver_id, String card_id, String phone, String car_num, String row_key, String timestamp, String log_type) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        IndexRequest request = new IndexRequest(elasticSearchIndex, elasticSearchType).id(id + StringUtils.EMPTY)
                .source("driver_id", driver_id,
                        "card_id", card_id,
                        "phone", phone,
                        "car_num", car_num,
                        "row_key", row_key,
                        "timestamp", timestamp,
                        "log_type", log_type)
                .opType(DocWriteRequest.OpType.CREATE);
//        request.routing(driver_id);
        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("indexResponse=" + indexResponse.toString());
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("indexResponse=" + indexResponse.toString());
        }
    }


    @Override
    public void insertBulkJson(String jsonString) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest(elasticSearchIndex, elasticSearchType, id + StringUtils.EMPTY).source(jsonString));

        BulkResponse bulkResponse = null;
        try {
            bulkResponse = client.bulk(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }

        for (BulkItemResponse bulkItemResponse : bulkResponse) {
            DocWriteResponse itemResponse = bulkItemResponse.getResponse();

            switch (bulkItemResponse.getOpType()) {
                case INDEX:
                case CREATE:
                    IndexResponse indexResponse = (IndexResponse) itemResponse;
                    break;
                case UPDATE:
                    UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                    break;
                case DELETE:
                    DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
            }
        }
    }






    @Override
    public void updateJson(String jsonString) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        UpdateRequest request = new UpdateRequest(elasticSearchIndex, elasticSearchType, id + StringUtils.EMPTY);
        request.doc(jsonString, XContentType.JSON);
//        request.routing("routing");
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("updateResponse=" + updateResponse.toString());
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("updateResponse=" + updateResponse.toString());
        }
    }

    @Override
    public void updateMap(Map<String, Object> jsonMap) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        UpdateRequest request = new UpdateRequest(elasticSearchIndex, elasticSearchType, id + StringUtils.EMPTY);
        request.doc(jsonMap);
//        request.routing("routing");
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("updateResponse=" + updateResponse.toString());
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("updateResponse=" + updateResponse.toString());
        }
    }

    @Override
    public void updateBuilder(String driver_id, String card_id, String phone, String car_num, String row_key, String timestamp, String log_type) throws ExecutionException, InterruptedException, IOException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("driver_id", driver_id);
            builder.timeField("card_id", card_id);
            builder.field("phone", phone);
            builder.field("car_num", car_num);
            builder.field("row_key", row_key);
            builder.field("timestamp", timestamp);
            builder.field("log_type", log_type);
        }
        builder.endObject();
        UpdateRequest request = new UpdateRequest(elasticSearchIndex, elasticSearchType, id + StringUtils.EMPTY);
        request.doc(builder);
//        request.routing("routing");
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("updateResponse=" + updateResponse.toString());
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("updateResponse=" + updateResponse.toString());
        }
    }

    @Override
    public void updateColumn(String driver_id, String card_id, String phone, String car_num, String row_key, String timestamp, String log_type) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        UpdateRequest request = new UpdateRequest(elasticSearchIndex, elasticSearchType, id + StringUtils.EMPTY)
                .doc("driver_id", driver_id,
                        "card_id", card_id,
                        "phone", phone,
                        "car_num", car_num,
                        "row_key", row_key,
                        "timestamp", timestamp,
                        "log_type", log_type);

        //        request.routing("routing");
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("updateResponse=" + updateResponse.toString());
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("updateResponse=" + updateResponse.toString());
        }
    }


    @Override
    public void upsertJson(String jsonString) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();
        Long id = SnowflakeIdWorker.getId();

        UpdateRequest request = new UpdateRequest(elasticSearchIndex, elasticSearchType, id + StringUtils.EMPTY);
        request.upsert(jsonString, XContentType.JSON);
//        request.routing("routing");
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("updateResponse=" + updateResponse.toString());
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("updateResponse=" + updateResponse.toString());
        }
    }

    @Override
    public void delete(String id) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();

        DeleteRequest request = new DeleteRequest(elasticSearchIndex, elasticSearchType, id + StringUtils.EMPTY);

        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(request).get();
        } catch (ElasticsearchException e) {
            log.info("e=" + e);
            if (e.status() == RestStatus.CONFLICT) {

            }
        }
        if (deleteResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("deleteResponse=" + deleteResponse.toString());
        } else if (deleteResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("updateResponse=" + deleteResponse.toString());
        } else if (deleteResponse.getResult() == DocWriteResponse.Result.DELETED) {
            log.info("updateResponse=" + deleteResponse.toString());
        }
    }


    @Override
    public void exists(String id) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();

        GetRequest getRequest = new GetRequest(
                "posts",
                "doc",
                "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

//        boolean exists = client.exists(getRequest);
//        log.info("id=" + id + ",exists" + exists);

    }


    @Override
    public List<Map<String, Object>> searchFunctions(String key, String value) throws ExecutionException, InterruptedException {
        TransportClient client = transportApiClient.getTransportClient();

        SearchRequest searchRequest = new SearchRequest();

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery(key, value));
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);


        SearchResponse searchResponse = client.search(searchRequest).get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        List<Map<String, Object>> resultList = Lists.newArrayList() ;
        for (SearchHit hit : searchHits) {
            String sourceAsString = hit.getSourceAsString();
            log.info("sourceAsString="+sourceAsString);
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            log.info("sourceAsMap="+sourceAsMap);
            resultList.add(sourceAsMap);
        }

        return resultList;
    }









}
