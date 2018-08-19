package com.self.dao;

import com.self.model.Info;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 利用spring data jpa
 */

public interface ItemDocumentRepository extends ElasticsearchRepository<Info, String> {
}
