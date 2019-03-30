package nu.ist.iosf.commons.models.appBasic;

public class Response<T> {

	private int responseCode;
	private String responseMessage;
	private T items;

	public Response() {
	}

	public Response(int responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public T getItems() {
		return items;
	}

	public void setItems(T items) {
		this.items = items;
	}

}
