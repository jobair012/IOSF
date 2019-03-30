package nu.ist.iosf.commons.enums;

public enum ResponseCode {

	OPERATION_SUCCESSFUL(1000), 
	RECORD_NOT_FOUND(1001), 
	AUTHENTICATION_FAILED(1002), 
	INVALID_ARGUMENT(1003),
	WRONG_INFORMATION(1004),
	NOT_DONE(1005),
	UNEXPECTED_EXCEPTION(1006), 
	RUNTIME_ERROR(1007);
	
	private int code;

	private ResponseCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
