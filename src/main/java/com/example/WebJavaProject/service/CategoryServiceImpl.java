package com.example.WebJavaProject.service;

import com.example.WebJavaProject.dao.CategoryRepository;
import com.example.WebJavaProject.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int theId) {
        Optional<Category> result = categoryRepository.findById(theId);

        Category theCategory;

        if (result.isPresent()) {
            theCategory = result.get();
        }
        else {
            throw new RuntimeException("Did not find category id - " + theId);
        }

        return theCategory;
    }

    @Override
    public void save(Category theCategory) {
        categoryRepository.save(theCategory);
    }

    @Override
    public void deleteById(int theId) {
        categoryRepository.deleteById(theId);
    }
}
