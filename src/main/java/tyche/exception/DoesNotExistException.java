package tyche.exception;

public class DoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DoesNotExistException(String name) {
		super(name + " doesn't exist.");
	}

}
