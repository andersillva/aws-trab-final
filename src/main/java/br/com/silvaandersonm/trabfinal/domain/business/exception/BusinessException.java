package br.com.silvaandersonm.trabfinal.domain.business.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private BusinessExceptionType type;
	
	public BusinessException(BusinessExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }
	
	public String getCode() {
		return type.getCode();
	}

}
