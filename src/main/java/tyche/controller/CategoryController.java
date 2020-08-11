package tyche.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tyche.exception.AlreadyExistsException;
import tyche.model.Category;
import tyche.service.CategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("")
	public List<Category> getAllCategories() {
		return categoryService.findAllCategories();
	}
	
	@PostMapping("")
	public String saveCategory(Category category) throws AlreadyExistsException, IOException {
		categoryService.saveCategory(category);
		return category.getName();
	}
}
