package api.endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response response = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Route.post_url);
				
		return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response = 
				given()
					.pathParam("username", userName)
				.when()
					.get(Route.get_url);
		return response;
	}
	public static Response updateUser(String userName, User payload)
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username", userName)
					.body(payload)
				.when()
					.put(Route.delete_url);
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = 
				given()
					.pathParam("username", userName)
				.when()
					.delete(Route.delete_url);
		return response;
	}
}
