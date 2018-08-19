package com.self.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * 利用elasticsearch restapi来实现
 * io.searchbox
 */
@Configuration
public class ElasticSearchConfigure {

    @Value("${elasticsearch.name}")
    private String name;

    private Settings settings() {
        Settings settings = Settings.builder().put("cluster.name", name).put("client.transport.sniff", true).build();
        return settings;
    }

    @Bean
    protected Client buildClient() {
        TransportClient transportClient = new PreBuiltTransportClient(settings());
        return transportClient;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        Client client = buildClient();
        return new ElasticsearchTemplate(client);
    }
}
