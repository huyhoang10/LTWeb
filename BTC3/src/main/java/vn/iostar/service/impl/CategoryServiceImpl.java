package vn.iostar.service.impl;

import vn.iostar.DAO.impl.CategoryDaoImpl;
import vn.iostar.Models.Category;
import vn.iostar.service.CategoryService;
import vn.iostar.utils.Constant;

import java.io.File;
import java.util.List;

import vn.iostar.DAO.CategoryDao;

public class CategoryServiceImpl implements CategoryService{
	CategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
	public void insert(Category category) {
	categoryDao.insert(category);
	}
	
	@Override
	public void delete(int id) {
	categoryDao.delete(id);
	}
	@Override
	public Category get(int id) {
	return categoryDao.get(id);
	}
	
	@Override
	public void edit(Category newCategory) {
	Category oldCategory = categoryDao.get(newCategory.getCateid());
	oldCategory.setCatename(newCategory.getCatename());
	if (newCategory.getIcon() != null) {
	// XOA ANH CU DI
	String fileName = oldCategory.getIcon();
	final String dir = Constant.DIR;
	File file = new File(dir + "/category" + fileName);
	if (file.exists()) {
	file.delete();
	}
	oldCategory.setIcon(newCategory.getIcon());
	}
	categoryDao.edit(oldCategory);
	}
	
	@Override
	public Category get(String name) {
	return categoryDao.get(name);
	}
	@Override
	public List<Category> getAll() {
	return categoryDao.getAll();
	}
	@Override
	public List<Category> search(String catename) {
	return categoryDao.search(catename);
	}
}
