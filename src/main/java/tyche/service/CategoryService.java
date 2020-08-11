package tyche.service;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tyche.dao.CategoryDao;
import tyche.exception.AlreadyExistsException;
import tyche.model.Category;

@Service("categoryService")
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	public List<Category> findAllCategories() {
		return categoryDao.findAllCategories();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void saveCategory(Category category)
			throws AlreadyExistsException, IOException {
		if (categoryDao.saveCategory(category) == 1)
			throw new AlreadyExistsException(category.getName());

	}
}
