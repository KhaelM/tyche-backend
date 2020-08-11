package tyche.dao;



import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tyche.model.Product;

@Transactional
@Repository
public class ProductDao extends AbstractDao<String, Product> {
	
	public List<Product> getAllProducts() {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		Query<Product> query = session.createQuery("select distinct product from Product as product left join fetch product.categories left join fetch product.tags left join fetch product.reviews left join fetch product.relatedProducts");
		return query.getResultList();
	}
	
	public List<Product> searchProducts(String search) {
		Session session = getSession();
		TypedQuery<Product> query = session.createQuery("select distinct product from Product product left join fetch product.categories left join fetch product.tags left join fetch product.relatedProducts where product.name LIKE :name", Product.class);
		query.setParameter("name", "%"+search+"%");
		return query.getResultList();
	}
	
	public Product findProductByName(String name) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
		Query<Product> query = session.createQuery("select distinct product from Product product left join fetch product.categories left join fetch product.tags left join fetch product.reviews left join fetch product.relatedProducts as relatedProduct left join fetch relatedProduct.reviews where product.name = :name");
		query.setParameter("name", name);
        return query.getSingleResult();
    }
	
	public int saveProduct(Product product) {
		Product productInSession = getByKey(product.getName());
		if(productInSession != null) {
			return 1;
		}
		save(product);
		return 0;
	}
	
	public int updateProduct(Product product) {
		System.out.println("before getByKey");
		Product productInSession = getByKey(product.getName());
		if(productInSession == null) {
			return 1;
		}
		BeanUtils.copyProperties(product, productInSession);
		return 0;
	}
}
