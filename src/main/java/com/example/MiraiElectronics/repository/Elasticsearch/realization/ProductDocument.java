package com.example.MiraiElectronics.repository.Elasticsearch.realization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class ProductDocument {

    @Id
    private Long productId;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Long)
    private Long categoryId;

    @Field(type = FieldType.Long)
    private Long brandId;

    @Field(type = FieldType.Text)
    private String model;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Object)
    private Map<String, Object> attributes;
}
