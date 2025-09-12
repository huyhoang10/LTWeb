package vn.iotstar.service;

import vn.iotstar.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    void insert(CategoryEntity category);

    void delete(int id);

    CategoryEntity findById(int id);

    void update(CategoryEntity newCategory);

    List<CategoryEntity> findByName(String name);

    List<CategoryEntity> findAll();
}
