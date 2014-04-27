package cloud.app.utils;

public enum ResponceCode {
	SUCCESSFUL(1, "Operation Successful"),
	CORRUPT_INPUT_DATA(2, "Data set corrupt"),
	FAILED(3, "Failed due to internal reasons");
	
	private int code;
	private String message;

    private ResponceCode(int code, String message) {
            this.code = code;
            this.message = message;
    }

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}    
}
