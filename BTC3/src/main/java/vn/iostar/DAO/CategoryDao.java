package vn.iostar.DAO;

import java.util.List;

import vn.iostar.Models.Category;


public interface CategoryDao {
	void insert(Category category);
	void edit(Category category);
	void delete(int id);
	Category get(int id);
	Category get(String name);
	List<Category> getAll();
	List<Category> search(String keyword);
}
