package com.example.WebJavaProject.service;

import com.example.WebJavaProject.entity.Category;


import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(int theId);

    void save(Category theCategory);

    void deleteById(int theId);


}
