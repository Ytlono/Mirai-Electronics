package com.example.MiraiElectronics.service;

import com.example.MiraiElectronics.repository.JPA.realization.Category;
import com.example.MiraiElectronics.repository.JPA.interfaces.CategoryRepository;
import com.example.MiraiElectronics.service.Generic.GenericEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService extends GenericEntityService<Category,Long> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category updateCategory(Long id, Category updateCategory) {
        Category category = findById(id);
        category.setName(updateCategory.getName());
        category.setDescription(updateCategory.getDescription());
        return categoryRepository.save(category);
    }
} 