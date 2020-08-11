package tyche.exception;

import org.springframework.web.multipart.MultipartFile;

public class ExtensionNotSupportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExtensionNotSupportedException(MultipartFile file, String extension) {
		super("Extension " + extension + " of " + file.getOriginalFilename() + " not supported.");
	}
}
