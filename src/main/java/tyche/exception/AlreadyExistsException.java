package tyche.exception;


public class AlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AlreadyExistsException(String name) {
		super(name + " already exists.");
	}

}
