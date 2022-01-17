package com.qinhao.estest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/12/19 - 14:02
 */
public class Doc {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.0.101", 9200, "http")));
        // 新增文档 - 请求对象
        IndexRequest request = new IndexRequest();
        // 设置索引及唯一性标识
        request.index("user").id("1001");
        // 创建数据对象
        User user = new User();
        user.setName("zhangsan");
        user.setAge(30);
        user.setSex("男");
        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(user);
        // 添加文档数据，数据格式为 JSON 格式
        request.source(productJson, XContentType.JSON);
        // 客户端发送请求，获取响应对象
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        //打印结果信息
        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());

        // 修改文档 - 请求对象
        UpdateRequest request1 = new UpdateRequest();
        // 配置修改参数
        request.index("user").id("1001");
        // 设置请求体，对数据进行修改
        request1.doc(XContentType.JSON, "sex", "女");
        // 客户端发送请求，获取响应对象
        UpdateResponse response1 = client.update(request1, RequestOptions.DEFAULT);
        System.out.println("_index:" + response1.getIndex());
        System.out.println("_id:" + response1.getId());
        System.out.println("_result:" + response1.getResult());


        //1.创建请求对象   查询
        GetRequest request2 = new GetRequest().index("user").id("1001");
        //2.客户端发送请求，获取响应对象
        GetResponse response2 = client.get(request2, RequestOptions.DEFAULT);


        //创建请求对象   删除
        DeleteRequest request3 = new DeleteRequest().index("user").id("1001");
        //客户端发送请求，获取响应对象
        DeleteResponse response3 = client.delete(request3, RequestOptions.DEFAULT);



        //创建批量新增请求对象  批量新增
        BulkRequest request4 = new BulkRequest();
        request4.add(new
                IndexRequest().index("user").id("1001").source(XContentType.JSON, "name",
                "zhangsan"));
        request4.add(new
                IndexRequest().index("user").id("1002").source(XContentType.JSON, "name",
                "lisi"));
        request4.add(new
                IndexRequest().index("user").id("1003").source(XContentType.JSON, "name",
                "wangwu"));
        //客户端发送请求，获取响应对象
        BulkResponse responses = client.bulk(request4, RequestOptions.DEFAULT);


        //创建批量删除请求对象 批量删除
        BulkRequest request5 = new BulkRequest();
        request5.add(new DeleteRequest().index("user").id("1001"));
        request5.add(new DeleteRequest().index("user").id("1002"));
        request5.add(new DeleteRequest().index("user").id("1003"));
        //客户端发送请求，获取响应对象
        BulkResponse responses5 = client.bulk(request5, RequestOptions.DEFAULT);
        //打印结果信息
        System.out.println("took:" + responses5.getTook());
        System.out.println("items:" + responses5.getItems());

        client.close();
    }
}
