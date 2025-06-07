package com.example.MiraiElectronics.repository.Elasticsearch.interfaces;

import com.example.MiraiElectronics.repository.Elasticsearch.realization.ProductDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductElasticsearchRepository extends ElasticsearchRepository<ProductDocument,Long> {
    @Query("""
            {
              "bool": {
                "should": [
                  {
                    "multi_match": {
                      "query": "?0",
                      "fields": ["name^4", "model^3", "description^2", "attributes.*"],
                      "type": "best_fields",
                      "operator": "or"
                    }
                  },
                  {
                    "match_phrase": {
                      "movie": {
                        "query": "?0",
                        "boost": 3
                      }
                    }
                  },
                  {
                    "match_phrase": {
                      "overview": {
                        "query": "?0",
                        "boost": 2
                      }
                    }
                  }
                ],
                "minimum_should_match": 1
              }
            }
            """)
    List<ProductDocument> searchByQuery(String query);

}
