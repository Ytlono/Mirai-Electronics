package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.service.catalog.ElasticsearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/elasticsearch/reindex")
public class ElasticsearchController {
    private final ElasticsearchService  elasticsearchService;

    public ElasticsearchController(ElasticsearchService elasticsearchService) {
        this.elasticsearchService = elasticsearchService;
    }

    @PostMapping
    public ResponseEntity<String> reindex(){
        elasticsearchService.reindexAllProducts();
        return ResponseEntity.ok("Reindex Successful");
    }

}
