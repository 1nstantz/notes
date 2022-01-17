package com.qinhao.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;

import java.io.IOException;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/12/19 - 13:46
 */
public class Index {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.0.101", 9200, "http")));
        //创建索引
        CreateIndexResponse response = client.indices().create(new CreateIndexRequest("user"), RequestOptions.DEFAULT);
        //响应状态
        System.out.println(response.isAcknowledged());

        //查询索引
        GetIndexResponse get = client.indices().get(new GetIndexRequest("user"), RequestOptions.DEFAULT);

        //删除索引
        AcknowledgedResponse delete = client.indices().delete(new DeleteIndexRequest("user"), RequestOptions.DEFAULT);

        client.close();
    }
}
