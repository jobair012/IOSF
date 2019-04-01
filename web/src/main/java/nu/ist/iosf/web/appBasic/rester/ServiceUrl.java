package nu.ist.iosf.web.appBasic.rester;

public class ServiceUrl {
	
	public static final String BASE_URL = "http://localhost:8080";

	/* ACCESS TOKEN - beguns */
	public static final String GET_TOKEN = "/access-token";
	public static final String DELETE_TOKEN = "/delete-token";
	/* ACCESS TOKEN - beguns */

	/*LOOKUP - beguns*/
	public static final String USERNAME_LOOKUP = "/lookup/systemuser";
	/*LOOKUP - ends*/
	
	/* USER MANAGEMENT - beguns */
	public static final String USER_CRUP_BASIC_INFO = "/user/crup/basic-info";
	public static final String USER_CRUP = "/user/crup";
	public static final String USER_DETAILS = "/user/details/%s";
	public static final String USER_STATUS = "/user/status/%s";
	public static final String USER_SEARCH_DATA = "/user/search-data";
	public static final String USER_SEARCH_RESULT = "/user/search";
	/* USER MANAGEMENT - ends */

}
