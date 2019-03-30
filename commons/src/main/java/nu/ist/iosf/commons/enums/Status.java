package nu.ist.iosf.commons.enums;

public enum Status {
	
	INACTIVE(0, "In-active"),
	ACTIVE(1, "Active");

	private int code;
	private String description;

	private Status(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription(){
		return description;
	}

	public static String getDescriptionByCode(int code){
		String description = null;
		for(Status status : Status.values()){
			if(status.getCode() == code){
				description = status.getDescription();
				break;
			}
		}
		return description;
	}

	public static Status getEnum(int code){
		Status thisEnum = null;
		for(Status currentEnum : Status.values()){
			if(currentEnum.getCode() == code){
				thisEnum = currentEnum;
				break;
			}
		}
		return thisEnum;
	}

}
