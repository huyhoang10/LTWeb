package vn.iotstar.service.Impl;

import vn.iotstar.dao.Impl.CategoryDaoImpl;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.entity.CategoryEntity;
import vn.iotstar.service.CategoryService;

import java.util.List;



public class CategoryServiceImpl implements CategoryService{
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void insert(CategoryEntity category) {
        categoryDao.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }
    @Override
    public CategoryEntity findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public void update(CategoryEntity newCategory) {
        CategoryEntity oldCategory = categoryDao.findById(newCategory.getId());
        oldCategory.setCateName(newCategory.getCateName());
        if (newCategory.getCateImage() != null) {
            // XOA ANH CU DI
//            String fileName = oldCategory.getCateImage();
//            final String dir = Constant.DIR;
//            File file = new File(dir + "/category" + fileName);
//            if (file.exists()) {
//                file.delete();
//            }
            oldCategory.setCateImage(newCategory.getCateImage());
        }
        categoryDao.update(oldCategory);
    }

    @Override
    public List<CategoryEntity> findByName(String name) {
        return categoryDao.findByName(name);
    }
    @Override
    public List<CategoryEntity> findAll() {
        return categoryDao.findAll();
    }

}
