package tyche.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tyche.model.Category;

@Transactional
@Repository
public class CategoryDao extends AbstractDao<String, Category> {
	public List<Category> findAllCategories() {
		Session session = getSession();
		TypedQuery<Category> query = session.createQuery("from Category", Category.class);
		return query.getResultList();
	}
	
	public int saveCategory(Category category) {
		Category categoryInSession = getByKey(category.getName());
		if(categoryInSession != null) {
			return 1;
		}
		save(category);
		return 0;
	}
}
