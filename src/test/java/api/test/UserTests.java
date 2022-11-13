package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserTests 
{
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("Creating User");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("..........................");
		logger.info("User is created");
	}
	
	@Test(priority=2)
	public void getUser()
	{
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("..........................");
		logger.info(" User ifo is displayed");
		
	}
	@Test(priority=3)
	public void updateUser()
	{
		logger.info("Updating  User");
		//updated data
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response=UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().statusCode(200);
		System.out.println("..........................");
		Response response1=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response1.getStatusCode(), 200);
		
	}
	@Test(priority=4)
	public void getUser1()
	{
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("..........................");
		
	}
	@Test(priority=5)
	public void deleteUser()
	{
		logger.info("Delete User");
		Response response=UserEndPoints.deleteUser(userPayload.getUsername());
		response.then().statusCode(200);
		
		
		
	}
	
	
}
