package tyche.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tyche.dao.ProductDao;
import tyche.exception.AlreadyExistsException;
import tyche.exception.DoesNotExistException;
import tyche.exception.ExtensionNotSupportedException;
import tyche.exception.NoImagesSentException;
import tyche.exception.NotImageException;
import tyche.model.Product;
import tyche.utils.StringUtils;

@Service("productService")
public class ProductService {
	private static final String imagesFolderPath = "/resources/images";
	private static final String imageExtensionServer = ".jpg";
	private static final String[] extensionsAllowed = { "jpg", "jpeg", "png" };
	@Autowired
	ProductDao productDao;
	
	@Transactional
	public Set<Product> getRelatedProducts(String name) {
		Set<Product> relatedProducts = findProductByName(name).getRelatedProducts();
		Hibernate.initialize(relatedProducts);
		Iterator<Product> itr = relatedProducts.iterator();
		// traversing over HashSet
		while(itr.hasNext()){
		  Hibernate.initialize(itr.next().getReviews());
		}
		return relatedProducts;
	}
	
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	public List<Product> searchProducts(String search) {
		return productDao.searchProducts(search);
	}

	public Product findProductByName(String name) {
		return productDao.findProductByName(name);
	}
	

	public void uploadProductImage(Product product, HttpSession session)
			throws IOException, ExtensionNotSupportedException, NotImageException, NoImagesSentException {
		if(product.getImages() == null) {
			throw new NoImagesSentException();
		}
		
		for (MultipartFile file : product.getImages()) {
			if (file == null)
				continue;
			String[] typeExtension = file.getContentType().split("/");
			String type = typeExtension[0];
			if (!type.equals("image")) {
				throw new NotImageException(file);
			}
			String extension = typeExtension[1];
			boolean extensionCorrect = false;
			for (int j = 0; j < extensionsAllowed.length; j++) {
				if (extensionsAllowed[j].equals(extension)) {
					extensionCorrect = true;
					break;
				}
			}
			if (!extensionCorrect) {
				throw new ExtensionNotSupportedException(file, extension);
			}
		}

		String prodNameUpScore = StringUtils.convertSpaceToUpperScore(product.getName());
		File productDirectory = new File(
				session.getServletContext().getRealPath(imagesFolderPath + "/" + prodNameUpScore));
//		System.out.println(productDirectory.getCanonicalPath());
		if(product.getImages() != null) {
			FileUtils.deleteDirectory(productDirectory);
			Files.createDirectories(
					Paths.get(session.getServletContext().getRealPath(imagesFolderPath + "/" + prodNameUpScore)));
			
			Path rootLocation = Paths
					.get(session.getServletContext().getRealPath(imagesFolderPath + "/" + prodNameUpScore));
			for (int i = 0; i < product.getImages().length; i++) {
				if (i == 0 && product.getImages()[0] != null) {
					Files.copy(product.getImages()[0].getInputStream(),
							rootLocation.resolve(prodNameUpScore.toLowerCase() + imageExtensionServer));
				}
				if (i == 1 && product.getImages()[1] != null) {
					Files.copy(product.getImages()[1].getInputStream(),
							rootLocation.resolve(prodNameUpScore.toLowerCase() + "-255x320" + imageExtensionServer));
				}
				
				if (i == 2 && product.getImages()[2] != null) {
					Files.copy(product.getImages()[2].getInputStream(),
							rootLocation.resolve(prodNameUpScore.toLowerCase() + "-160x120" + imageExtensionServer));
				}
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateProduct(Product product)
			throws DoesNotExistException, IOException{
		if (productDao.updateProduct(product) == 1)
			throw new DoesNotExistException(product.getName());

	}

	@Transactional(rollbackFor = Exception.class)
	public void saveProduct(Product product)
			throws AlreadyExistsException, IOException {
		if (productDao.saveProduct(product) == 1)
			throw new AlreadyExistsException(product.getName());

	}
}
