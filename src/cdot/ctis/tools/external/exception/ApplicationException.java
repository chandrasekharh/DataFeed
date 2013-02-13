package cdot.ctis.tools.external.exception;

/**
 * Application Exception.
 * 
 * @author chandrasekharh
 *
 */
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an application exception with the given message.
	 * 
	 * @param pMessage
	 */
	public ApplicationException(String pMessage) {
		super(pMessage);
	}
	
	/**
	 * Creates an application exception with the given message.
	 * 
	 * @param pMessage
	 * @param pThrowable
	 */
	public ApplicationException(String pMessage, Throwable pThrowable) {
		super(pMessage, pThrowable);
	}

	/**
	 * Creates an application exception with the given message.
	 * 
	 * @param pThrowable
	 */
	public ApplicationException(Throwable pThrowable) {
		super(pThrowable);
	}
}