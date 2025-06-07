package com.example.MiraiElectronics.service;

import com.example.MiraiElectronics.repository.Elasticsearch.interfaces.ProductElasticsearchRepository;
import com.example.MiraiElectronics.repository.Elasticsearch.realization.ProductDocument;
import com.example.MiraiElectronics.repository.JPA.realization.Product;
import com.example.MiraiElectronics.service.ProductServices.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElasticsearchService {
    private final ProductService productService;
    private final ProductElasticsearchRepository productElasticsearchRepository;

    public ElasticsearchService(ProductService productService, ProductElasticsearchRepository productElasticsearchRepository) {
        this.productService = productService;
        this.productElasticsearchRepository = productElasticsearchRepository;
    }

    @Transactional(readOnly = true)
    public void reindexAllProducts() {
        List<Product> products = productService.findAll();

        productElasticsearchRepository.saveAll(
                products.stream()
                        .map(product -> new ProductDocument(
                                product.getProductId(),
                                product.getName(),
                                product.getCategory().getCategoryId(),
                                product.getBrandId(),
                                product.getModel(),
                                product.getPrice(),
                                product.getDescription(),
                                product.getAttributes()
                        ))
                        .collect(Collectors.toList())
        );
    }
}
