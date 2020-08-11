package tyche.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product {
	@Id
	String name;
	Float price;
	@Column(name="sale_price")
	Float salePrice;
	String recap;
	String description;
	@Transient
	MultipartFile[] images = null;
	
    @ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_name"), inverseJoinColumns = @JoinColumn(name = "category_name"))
	Set<Category> categories = new HashSet<Category>();
	
    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_name"), inverseJoinColumns = @JoinColumn(name = "tag_name"))
	Set<Tag> tags = new HashSet<Tag>();
	
	@OneToMany(mappedBy="product", cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @JsonManagedReference
	Set<Review> reviews = new HashSet<Review>();
	
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "related_products", joinColumns = @JoinColumn(name = "current_product"), inverseJoinColumns = @JoinColumn(name = "related_product"))
	Set<Product> relatedProducts = new HashSet<Product>();
	
	public Set<Product> getRelatedProducts() {
		return relatedProducts;
	}

	public void setRelatedProducts(Set<Product> relatedProducts) {
		this.relatedProducts = relatedProducts;
	}

	public MultipartFile[] getImages() {
		return images;
	}

	public void setImages(MultipartFile[] images) {
		this.images = images;
	}

	public void addTag(Tag tag) {
		tags.add(tag);
	}
	
	public void addCategory(Category category) {
		categories.add(category);
	}
	
	public void addReview(Review review) {
		review.setProduct(this);
		reviews.add(review);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}

	public String getRecap() {
		return recap;
	}

	public void setRecap(String recap) {
		this.recap = recap;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
}
