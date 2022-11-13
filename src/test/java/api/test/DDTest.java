package api.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;


public class DDTest 
{

	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String UserId,String Username,String FirstName,String LastName,String Email,String pw,String ph)
	{
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(UserId));
		userPayload.setEmail(Email);
		userPayload.setUsername(Username);
	    userPayload.setFirstName(FirstName);
	    userPayload.setLastName(LastName);
	    userPayload.setPassword(pw);
	    userPayload.setPhone(ph);
	    Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(),200);
		
		}

	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
    public void deleteUser(String username)
    {
		Response response=UserEndPoints.deleteUser(username);
		response.then().statusCode(200);
    }
}