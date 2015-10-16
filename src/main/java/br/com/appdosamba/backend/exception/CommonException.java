package br.com.appdosamba.backend.exception;

public class CommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5418244448619434314L;
	
	public CommonException(String m) {
		super(m);
	}

	public CommonException(String m, Throwable e) {
		super(m, e);
	}
	
	public CommonException( Throwable e) {
		super( e);
	}

}
