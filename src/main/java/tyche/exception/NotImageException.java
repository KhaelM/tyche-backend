package tyche.exception;

import org.springframework.web.multipart.MultipartFile;

public class NotImageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotImageException(MultipartFile file) {
		super(file.getOriginalFilename() + " is not recognized as an image");
	}
}
