package tyche.exception;

public class NoImagesSentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoImagesSentException() {
		super("No images has been sent");
	}
}
