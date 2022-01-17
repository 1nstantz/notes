package com.qinhao.estest;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/12/19 - 13:40
 */
public class Client {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.0.101", 9200, "http")));


        client.close();
    }
}
