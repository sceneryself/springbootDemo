package com.self.controller;

import com.self.dao.ItemDocumentRepository;
import com.self.model.Info;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ElasticSearchController {

    @Autowired
    private TransportClient client;
    @Autowired
    private ItemDocumentRepository itemDocumentRepository;

    @GetMapping("es/get")
    public GetResponse get() {
        GetResponse response = client.prepareGet("info", "info", "1").get();
        return response;
    }

    @PostMapping("es/save")
    public String save() {
        Map map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("name", "test");
        map.put("code", 1000);
        map.put("msg", "test info");
        IndexResponse response = client.prepareIndex("info", "info", map.get("id").toString()).setSource(map).execute().actionGet();
        return response.getId();
    }

    @GetMapping("es/query")
    public List<String> search() {
        SearchResponse response = client.prepareSearch("info").setTypes("info").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setPostFilter(QueryBuilders.rangeQuery("id").from(0).to(100)).setFrom(0).setSize(100).setExplain(true).get();

        SearchHits searchHits = response.getHits();
        List<String> data = new ArrayList<String>();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            data.add(json);
            System.err.println(json);
        }
        return data;
    }

    @PostMapping("es/insert")
    public boolean insert(){
        Info info = new Info();
        itemDocumentRepository.save(info);
        return true;
    }

    @GetMapping("es/search")
    public List<Info> search(String name){
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(name);
        Iterable<Info> searchResult = itemDocumentRepository.search(builder);
        Iterator<Info> it = searchResult.iterator();
        List<Info> infos = new ArrayList<Info>();
        while(it.hasNext()){
            infos.add(it.next());
        }
        return infos;
    }

    @GetMapping("es/searchPage")
    public List<Info> searchPage(String name){
        Pageable pageable = new PageRequest(1, 100);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(name);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        Page<Info> searchResult = itemDocumentRepository.search(searchQuery);
        return searchResult.getContent();
    }

    @GetMapping("es/searchWeight")
    public List<Info> searchByWeight(String name){
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", name)));
        Iterable<Info> searchResult = itemDocumentRepository.search(functionScoreQueryBuilder);
        Iterator<Info> it = searchResult.iterator();
        List<Info> infos = new ArrayList<Info>();
        while(it.hasNext()){
            infos.add(it.next());
        }
        return infos;
    }

}
