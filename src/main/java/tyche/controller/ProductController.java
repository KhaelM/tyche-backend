package tyche.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tyche.config.AppInitializer;
import tyche.exception.AlreadyExistsException;
import tyche.exception.DoesNotExistException;
import tyche.exception.ExtensionNotSupportedException;
import tyche.exception.NoImagesSentException;
import tyche.exception.NotImageException;
import tyche.model.JsonResp;
import tyche.model.Product;
import tyche.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
    ProductService productService;
	
	@GetMapping("")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();

	}
	
	@GetMapping("/search/{input}")
	public List<Product> searchProducts(@PathVariable String input) {
		return productService.searchProducts(input);

	}
	
	@GetMapping("/maxFileSize")
	public JsonResp getMaxFileSize() {
		JsonResp json = new JsonResp();
		json.setData(AppInitializer.MAX_FILE_SIZE);
		json.setStatusSuccess();
		return json;
	}
	
	@GetMapping("/maxUploadSize")
	public JsonResp getMaxUploadSize() {
		JsonResp json = new JsonResp();
		json.setData(AppInitializer.MAX_REQUEST_SIZE);
		json.setStatusSuccess();
		return json;
	}
	
	@GetMapping("/relatedProducts/{name}")
	public Set<Product> getRelatedProducts(@PathVariable String name) {
		return productService.getRelatedProducts(name);
	}
	
	@PostMapping("")
    public JsonResp createProduct(@RequestBody Product product, HttpSession session) throws IOException {
    	JsonResp json = new JsonResp();
    	try {
    		productService.saveProduct(product);
    		json.setData(product.getName());
    	} catch(AlreadyExistsException e) {
    		System.out.println(e.getMessage());
    		json.setStatusError();
    		json.addError(e.getMessage());
    	}
    	json.setStatusSuccess();
    	return json;
    }
	
	@PostMapping("/uploadImage")
	public JsonResp uploadImage(Product product, HttpSession session) throws IOException {
		JsonResp json = new JsonResp();
		try {
			productService.uploadProductImage(product, session);    		
    	} catch(ExtensionNotSupportedException | NotImageException | NoImagesSentException e) {
    		json.setStatusError();
    		json.addError(e.getMessage());
    	}
		return json;
	}
	
	@PutMapping(value="")
    public JsonResp updateProduct(@RequestBody Product product, HttpSession session) throws IOException {
    	JsonResp json = new JsonResp();
    	try {
    		productService.updateProduct(product);   
    		json.setData(product.getName());
    		json.setStatusSuccess();
    	} catch(DoesNotExistException e) {
    		json.setStatusError();
    		json.addError(e.getMessage());
    	}
    	json.setStatusSuccess();
    	return json;
    }
    
    @GetMapping("/{name}")
    public Product findProductByName(@PathVariable String name) {
    	return productService.findProductByName(name);
    }
}
