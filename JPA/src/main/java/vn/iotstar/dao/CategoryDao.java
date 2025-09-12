package vn.iotstar.dao;

import vn.iotstar.entity.CategoryEntity;

import java.util.List;

public interface CategoryDao {
    void insert(CategoryEntity cateEntity);

    void update(CategoryEntity cateEntity);

    void delete(int cateId);

    List<CategoryEntity> findAll();

    List<CategoryEntity> findAll(int page, int pageSize);

    CategoryEntity findById(int id);

    List<CategoryEntity> findByName(String cateName);
}
