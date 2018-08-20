package com.self.dao;

import com.self.model.Info;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * 利用spring data jpa
 */
@Component
public interface ItemDocumentRepository extends ElasticsearchRepository<Info, String> {
}
