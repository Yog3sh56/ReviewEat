package application;

import java.util.ArrayList;
import java.util.TreeSet;

import Database.dataSQLite;

/**
 * This is a test script used for white box testing
 * 
 * Please delete the previous sqlite file before running this test script
 * 
 * @author Yogesh Parajuli
 *
 */
public class testScript {

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		
		//This is test stage one of whitebox testing
		//This includes testing of individual classes for valid inputs and invalid inputs

		
		// Software Users tests
		
		SoftwareUsers testSoftwareUsers = null;
		System.out.println("SoftwareUsers Class Checks");
		
		try {
			//valid inputs
			testSoftwareUsers = new SoftwareUsers("yogesh12", "password123");
			System.out.println("Pass");
			
			System.out.println(testSoftwareUsers.getUsername());
			System.out.println(testSoftwareUsers.getPassword());
			
			//Invalid Usename Check
			try {
				testSoftwareUsers.setUsername("y");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				testSoftwareUsers.setUsername("yog3shp@");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			
			//Invalid Password Check
			try {
				testSoftwareUsers.setPassword("pass");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				testSoftwareUsers.setPassword("p@ssword1");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
		}
		catch (InputValidationException e) {
			System.out.println("Fail");
		}
		
		
		
		
		//Users test
		User testUser = null;
		System.out.println("User class Checks");
		try {
			//For username and password, user calls its super class
			//i.e. softwareUsers class which has already been tested above
			
			//valid inputs
			testUser = new User("yogesh12", "password123","Yogesh", "Parajuli","yogesh@gmail.com");
			System.out.println("Pass");
			
			if(!testUser.areYouAdmin()) {
				//User does not have admin priviledge 
				System.out.println("Pass");
			}
			else {
				System.out.println("Fail");
			}
			
			System.out.println(testUser.getfName());
			System.out.println(testUser.getsName());
			System.out.println(testUser.getEmail());
			System.out.println(testUser.getUsername());
			System.out.println(testUser.getPassword());
			
			//Invalid first name checks
			try {
				//invalid length
				testUser.setfName("y");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				//invalid characters
				testUser.setfName("Yog3sh");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			//Invalid Second name checks
			try {
				//invalid length
				testUser.setsName("p");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				//invalid characters
				testUser.setsName("p@r@juli");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
		}
		catch(InputValidationException e) {
			System.out.println("Fail");
		}
		
		
		
		//Restaurant Test
		Restaurant testRestaurant = null;
		System.out.println("Restaurant class Checks");
		
		try {
			// all valid inputs
			testRestaurant = new Restaurant("Alpha Restaurant", "British Cuisine at its finest.", "British","01416666666", "100 Hello Road" );
			System.out.println("Pass");
			
			System.out.println(testRestaurant.getName());
			System.out.println(testRestaurant.getRestaurantDescription());
			System.out.println(testRestaurant.getCuisine());
			System.out.println(testRestaurant.getContact());
			System.out.println(testRestaurant.getAddress());
			
			//Invalid Name Checks
			try {
				//invalid length for restaurant name and special characters are not allowed
				testRestaurant.setName("Hi@");
				System.out.println("Fail");
			}
			catch (InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				//invalid length for restaurant name
				testRestaurant.setName("The best Restaurant that you have ever been to.");
				System.out.println("Fail");
			}
			catch (InputValidationException e) {
				System.out.println("Pass");
			}
			
			//Invalid Restaurant Description Check
			try {
				//invalid length for a description
				testRestaurant.setRestaurantDescription("Hi");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				//invalid length for a description
				testRestaurant.setRestaurantDescription("This is the text that contains more than 100 characters. The restaurant is the best one that you have ever been to.");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			
			//Invalid Cuisine check
			try {
				testRestaurant.setCuisine("Br");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				testRestaurant.setCuisine("Best British Ever");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			//Invalid Contact Check
			try {
				//the contact number has to be 11 digits
				testRestaurant.setContact("123456789");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				//only digits are allowed
				testRestaurant.setContact("Hello");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			//Invalid Address Check 
			try {
				//Adrees should have a number followed by a space followed by a word followed by another space and a word
				testRestaurant.setAddress("Random Road");	
				System.out.println("Fail");
			}
			catch (InputValidationException e){
				System.out.println("Pass");
			}
			
			
			try {
				testRestaurant.setAddress("100");
				System.out.println("Fail");
			}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
		}
		catch(InputValidationException e) {
			System.out.println("Fail");
		}
		
		//Review Class Check
		Review testReview = null;
		System.out.println("Review Class checks");
		
		try {
			testReview = new Review("I absolutely loved this place", 8);
			System.out.println("Pass");
			
			System.out.println(testReview.getRating());
			System.out.println(testReview.getDescription());
			
			//Invalid description test
			try {
				testReview.setDescription("Nah");
				System.out.println("Fail");
				}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			try {
				testReview.setDescription("This is a review description that contains more than one hundred characters. It was just awesome. Just awesome.");
				System.out.println("Fail");
				}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
			//Invalid Rating test
			try {
				testReview.setRating(-1);
				System.out.println("Fail");
				}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			try {
				testReview.setRating(11);
				System.out.println("Fail");
				}
			catch(InputValidationException e) {
				System.out.println("Pass");
			}
			
		}
		catch(InputValidationException e) {
			System.out.println("Fail");
		}
		
		//This is the second stage of testing
		//In this stage, we check if the classes work together.
		
		try {
			//Created a couple of restaurant classes
		Restaurant restaurant1 = new Restaurant("Alpha Restaurant", "British Cuisine at its finest.", "British","01416666666", "100 Hello Road");
		Restaurant restaurant2 = new Restaurant("Beta Restaurant", "Indian Cuisine at its finest.", "Indian","01416666667", "5 Glasgow Cresent");
		
		
		User user1 = new User("yogesh12", "password123","Yogesh", "Parajuli","yogesh@gmail.com");
		User user2 = new User("random12", "password321","Random", "Person","random@gmail.com");
		
		Review review1 =  new Review("I had an awesome experience", 10);
		Review review2 = new Review("It was really lovely.", 9);
		
		restaurant1.addReview(user1.getUsername(), review1);
		restaurant2.addReview(user2.getUsername(), review2);
		restaurant1.addReview(user2.getUsername(),"Test Description", 6);
		
		System.out.println(restaurant1.getReviews());
		System.out.println("--");
		System.out.println(restaurant2.getReviews());
		System.out.println("--");
		System.out.println("First Restaurant Average Rating:" + restaurant1.getAverageRating());
		System.out.println("Second Restaurant Average Rating:" + restaurant2.getAverageRating());
		System.out.println("--");
		
		System.out.println("Comparision Check");
		System.out.println("1 expected: " +  restaurant1.compareTo(restaurant2));
		System.out.println("-1 expected: " +  restaurant2.compareTo(restaurant1));
		System.out.println("--");
		System.out.println("Remove Review from restaurant check");
		restaurant1.removeReview(user2, review1);
		System.out.println(restaurant1.getReviews());
		System.out.println("-1 expected: " +  restaurant1.compareTo(restaurant2));
		
		
		
		}
		catch(InputValidationException e) {
			System.out.println("Fail");
		}
		
		
		//The third stage is when we test the database 
		//the database is controlled by dataSQLite class
		//In this stage we test all the methods in the dataSQLite class
		dataSQLite data = dataSQLite.getInstance();
		System.out.println("---");
		System.out.println("Database Check\n");
		
		
		//Here we create some more restaurant object and some more review objects.
		try {
			
			User user1 = new User("yogesh12", "password123","Yogesh", "Parajuli","yogesh@gmail.com");
			User user2 = new User("random12", "password321","Random", "Person","random@gmail.com");
			
			Restaurant restaurant1 = new Restaurant("Alpha Restaurant", "British Cuisine at its finest.", "British","01416666666", "100 Hello Road");
			Restaurant restaurant2 = new Restaurant("Beta Restaurant", "Indian Cuisine at its finest.", "Indian","01416666667", "5 Glasgow Cresent");
			Restaurant restaurant3 = new Restaurant("Charlie Restaurant", "French Cuisine at its finest.", "French","01416666677", "12 Glasgow Cresent");
			Restaurant restaurant4 = new Restaurant("Zuli Restaurant", "Italian Cuisine at its finest.", "Italian","01416666767", "5 Heaven Road");
			
			Review review1 =  new Review("I had an awesome experince", 10);
			Review review2 = new Review("It was really lovely.", 9);
			Review review3 = new Review("Worst Place ever", 1);
			Review review4 = new Review("It was okay", 5);
			
			// Now we add restaurants on the database
			data.saveRestaurant(restaurant1);
			data.saveRestaurant(restaurant2);
			data.saveRestaurant(restaurant3);
			data.saveRestaurant(restaurant4);
			
			//check this by opening all the restaurants
			TreeSet<Restaurant> restaurants = data.openRestaurants();
			
			System.out.println("\n Here are a list of restaurants that have been added to the database\n" + restaurants);
			
			//database sets the id as auto increment instead of the restaurant class itselg
			//we dont set the id when an object is created
			//but here we need an id to add review on a restaurant
			//This works well with the GUI because we will be selectin a restaurant that has been given an id by the database
			restaurant1.setId(1);
			restaurant2.setId(2);
			restaurant3.setId(3);
			restaurant4.setId(4);
			
			//Now we save reviews on the restaurants
			data.saveReview(user1, restaurant1, review1);
			data.saveReview(user2, restaurant1, review2);
			
			data.saveReview(user1, restaurant2, review3);
			data.saveReview(user2, restaurant2, review4);
			
			data.saveReview(user1, restaurant4, review2);
			data.saveReview(user2, restaurant4, review1);
			
			
			System.out.println("\n Here the restaurants should be printed with their average rating on the side.");
			//check this by opening all the restaurants along with the reviews
			
			TreeSet<Restaurant> restWithReview = data.openRestaurants();
			for(Restaurant r: restWithReview) {
				r.setReviews(data.openReview(r.getId()));
				r.getAverageRating();
				
				System.out.println(r);
			}
			
			
			//add users to the database
			data.saveUser(user1);
			data.saveUser(user2);
			
			
			System.out.println("\n List of all users");
			//Check this by opening all the users
			ArrayList<User> users = data.openAllUsers();
			System.out.println(users);
			
			//open a single user
			System.out.println("----");
			System.out.println("Retrieve single user");
			System.out.println(data.openUser(user1.getUsername()));
			
			System.out.println("\nCheck for username");
			//CheckUsername
			System.out.println("Should be true: " + data.checkUsername(user1.getUsername()));
			System.out.println("Should be false: " + data.checkUsername("Random1234"));
			
			
			System.out.println("\n validate username and password");
			//Validate Username
			System.out.println("\nShould be true: " + data.validate(user1.getUsername(), user1.getPassword()));
			
			System.out.println("\nShould be false: " + data.validate("Completely Random", "Hello World"));
			
			//removeUser
			System.out.println("\n Remove a user test");
			data.removeUser(user1.getUsername());
			
			System.out.println("Should be false:" + data.checkUsername(user1.getUsername()));
			
			
			//remove Restaurant
			System.out.println("\nRemove a restaurant");
			
		
			
			data.removeRestaurant(restaurant3);
			System.out.println(data.openRestaurants());
			System.out.println(restaurant3.getName() + "should not be in the list");
			
			//Update password test
			System.out.println("\nUpdate Password");
			data.updatePassword(user2.getUsername(), "TestPassword1");
			
			System.out.println("Should be true:" + data.validate(user2.getUsername(), "TestPassword1"));
			
			
			//Update Restaurant test
			System.out.println("\nUpdate Restaurant");
			data.updateRestaurant(2, "01419090900", "1 ONe Road", "Updated Description");
			
			System.out.println(data.openRestaurants());
			System.out.println(restaurant2.getName()  + "Should have updated details.");
			
			
		} catch (InputValidationException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail");
		}
		
		
	}

}
