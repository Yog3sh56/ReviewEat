package Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import application.InputValidationException;
import application.Restaurant;
import application.Review;

import application.User;

/**
 * 
 * This is the data layer of the program
 * @author Yogesh Parajuli
 *
 */
public final class dataSQLite {
	private static final dataSQLite instance = new dataSQLite();
	
	/**
	 * Using Singleton pattern.
	 * Returns the dataSQLite's instance
	 * @return instance
	 */
	public static dataSQLite getInstance() {
		return instance;
	}
	
	//Private constructor so that the class cannot be instantiated
	// The constructor also creates the necessary tables on the database
	@SuppressWarnings("deprecation")
	private dataSQLite() {
		try {
			//Loads SQLite Driver
			Class.forName("org.sqlite.JDBC").newInstance();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		
		try {
			
			// Make connection with the database
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			//Create a User Table if it hasnt already been created
			PreparedStatement statement =connection.prepareStatement("CREATE TABLE IF NOT EXISTS user (username VARCHAR(256) PRIMARY KEY NOT NULL, password VARCHAR(256) NOT NULL,fName VARCHAR(256) NOT NULL, sName VARCHAR(256) NOT NULL, email VARCHAR(256) NOT NULL)");
			
			try {
			 statement.executeUpdate();
			 statement.close();
			 
			 
			 // Create Restaurant Table if not created already
			 statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS restaurant (restId INTEGER PRIMARY KEY AUTOiNCREMENT, name VARCHAR(256) NOT NULL,description VARCHAR(256) NOT NULL, cuisine VARCHAR(256) NOT NULL,contact VARCHAR(256) NOT NULL,address VARCHAR(256) NOT NULL)");
			 
			 statement.executeUpdate();
			 statement.close();
			 
			 //Create Review Table if not created already
			 
			 // Foreign keys have been initialised
			 //If data in parent table are deleted, the clild class data are also deleted
			 //i.e if restaurant is deleted, reviews related to the restaurant are also deleted
			 // same with the user
			 statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS review (reviewId INTEGER PRIMARY KEY AUTOINCREMENT, restId INTEGER NOT NULL, username VARCHAR(256) NOT NULL, rating INTEGER NOT NULL, description VARCHAR(256) NOT NULL, FOREIGN KEY (restId) REFERENCES restaurant (restId) ON DELETE CASCADE ON UPDATE NO ACTION, FOREIGN KEY (username) REFERENCES user(username) ON UPDATE NO ACTION ON DELETE CASCADE)");
			 
			 statement.executeUpdate();
			 statement.close();
			 
			}
			catch (SQLException e) {
	
			}
			finally {
				statement.close();
				connection.close();
			}
		}
		catch (SQLException e){
			e.printStackTrace();
			
		}		
		
	}// closing parenthesis for constructor
	
	/**
	 * Method to save restaurants onto the database
	 * @param restaurant
	 */
	//method to save restarant
	public void saveRestaurant(Restaurant restaurant) {
		try {
			//Establish connection
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			//Write a statement
			PreparedStatement statement = connection.prepareStatement("INSERT INTO restaurant( name,description, cuisine, contact, address) VALUES (?,?,?,?,?)");
			
			try{
				//SQL statements to assign value to the "?" 
				//statement.setString(1, restaurant.getId());
				statement.setString(1, restaurant.getName());
				statement.setString(2, restaurant.getRestaurantDescription());
				statement.setString(3, restaurant.getCuisine());
				statement.setString(4, restaurant.getContact());
				statement.setString(5, restaurant.getAddress());
				
				statement.executeUpdate();
				statement.close();	
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

	/**
	 * Method to save review onto the database
	 * @param user 
	 * @param restaurant 
	 * @param review 
	 *
	 */
	public void saveReview(User user,Restaurant restaurant, Review review) {
	
			try {
				//Establish a connection
				Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
				
				//Prepare a statement
				PreparedStatement statement = connection.prepareStatement("INSERT INTO review ( restId, rating, description,username) VALUES (?,?,?,?)");
				
				try {
					
					statement.setInt(1, restaurant.getId());
					statement.setInt(2, review.getRating());
					statement.setString(3, review.getDescription());
					statement.setString(4, user.getUsername());
					
					statement.executeUpdate();
					statement.close();
					
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				finally {
					statement.close();
					connection.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
	
		
	}
	/**
	 * Method to save user into the database
	 * @param user
	 */
	//method to save users
	public void saveUser(User user) {
		
		try {
			//Establish a connection
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			//prepare a statement
			PreparedStatement statement = connection.prepareStatement("INSERT INTO user (username,password,fName,sName,email) VALUES (?,?,?,?,?)");
			
			try {
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getfName());
				statement.setString(4, user.getsName());
				statement.setString(5, user.getEmail());
				
				statement.executeUpdate();
				statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Since we are letting the user to choose their own username, we need to check if the username is already taken
	 * @param username
	 * @return whether there is a match in username i.e expect true if username is already in the database
	 */
	public boolean checkUsername(String username) {
		try {
			//Establish a connection
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("SELECT username FROM user");
			
			try {
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					if (username.equals(results.getString("username"))) {
						return true;
					}
				}
				statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	
	/**
	 * Since we are letting the user to choose their own username, we need to check if the username is already taken
	 * @param username
	 * @param restId 
	 * @return whether there is a match in username i.e expect true if username is already in the database
	 */
	public boolean checkReview(String username, int restId) {
		try {
			//Establish a connection
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("SELECT username, restId FROM review");
			
			try {
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					if (username.equals(results.getString("username")) && restId == results.getInt("restId")) {
						return true;
					}
				}
				statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	
	
	/**
	 * Method to validate username and password for login
	 * @param username
	 * @param password
	 * @return if the username and password are valid i.e expects true if username and password match and are stored in the database
	 */
	public boolean validate (String username, String password) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("SELECT username, password FROM user");
			try {
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					if (username.equals(results.getString("username")) && password.equals(results.getString("password"))) {
						return true;
					}
				}
				statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				connection.close();
				statement.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * A method to retrieve a User object 
	 * @param username
	 * @param password
	 * @return User object
	 * @throws InputValidationException 
	 */
	public User openUser(String username) throws InputValidationException {
		User user;
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement1 = connection.prepareStatement("Select DISTINCT * FROM user WHERE username = ?");
			try {
				statement1.setString(1, username);
				
				// there are no if clause used here because this method will be called after validate method
				//therefore we know that the result exists and there are no null values
				ResultSet results = statement1.executeQuery();
				//System.out.println(results.isClosed());
				
				user = new User(results.getString("username"), results.getString("fName"), results.getString("sName"), results.getString("email"));
				
				return user;
				
				
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				connection.close();
				statement1.close();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method to retrieve all the users that are registered to the system
	 * @return collection of users
	 * @throws InputValidationException 
	 */
	public ArrayList<User> openAllUsers() throws InputValidationException{
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			PreparedStatement statement = connection.prepareStatement("Select * FROM user");
			
			try {
				ResultSet allUsers = statement.executeQuery();
				while(allUsers.next()) {
					users.add(new User(allUsers.getString("username"), allUsers.getString("fName"), allUsers.getString("sName"), allUsers.getString("email")));
				}
				
			}
			catch(SQLException e) {
			e.printStackTrace();
			}
			finally {
				connection.close();
				statement.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
		
	}
	
	
	/**
	 * 
	 * Method to retrieve Restaurant data held by the database
	 * @return Review Eat object (collection of restaurants)
	
	 */
	public TreeSet<Restaurant> openRestaurants()  {
		//we create a review eat object to populate it with details
		//ReviewEat re = new ReviewEat();
		
		TreeSet<Restaurant> re = new TreeSet<>();
		
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			
			//create a statement to get restaurants
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM restaurant ORDER BY name");
			try {
				ResultSet results = statement.executeQuery();
				//Loop to populate Review Eat object with restaurants
				while (results.next()) {
					Restaurant restaurant = new Restaurant (results.getInt("restId"), results.getString("name"), results.getString("description"), results.getString("cuisine"), results.getString("contact"), results.getString("address"));
					
					//restaurant.setReview(openReview(restaurant.getId()));
					 re.add(restaurant);
					
				}
				
			
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				connection.close();
				statement.close();
				
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
		
	}
	
	
	
	/**
	 *Method to open reviews from the database 
	 * @param restId
	 * @return arraylist
	 * @throws InputValidationException 
	 */
	public HashMap<String,Review> openReview(int restId) {
		HashMap<String,Review> reviews= new HashMap<String, Review>();
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM review WHERE restId = ?");
			try {
				statement.setInt(1, restId);
				
				ResultSet allReviews = statement.executeQuery();
				while(allReviews.next()) {
					
//					Review r = new Review(allReviews.getInt("rating"),allReviews.getString("description"));
					//User u = openUser(allReviews.getString("username"));
					reviews.put ( allReviews.getString("username"),new Review(allReviews.getInt("reviewId"),allReviews.getInt("rating"), allReviews.getString("description")));
					
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				connection.close();
				statement.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
		
	}

	
	/**
	 * Method to remove user from the database
	 * @param username
	 * @param password
	 */
	public void removeUser(String username) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			
			//create a statement to remove the user from the database
			PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE username = ?");
			
			try {
				statement.setString(1, username);
				statement.executeUpdate();
				
				statement.close();
				
				removeReview(username);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				connection.close();
				statement.close();
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Method to remove restaurant from the database
	 * @param restaurant
	 */
	public void removeRestaurant(Restaurant restaurant) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("DELETE FROM restaurant WHERE restId = ?");
			try {
				statement.setInt(1, restaurant.getId());
				statement.executeUpdate();
				
				statement.close();
				removeReview(restaurant);
				
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * I have added "ON CASCADE" constrain in reveiw class however during testing, review is not being deleted from the database when user is deleted
	 * hence i have created a method to force delete the review associated with the user
	 * @param username
	 */
	public void removeReview(String username) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("DELETE FROM review WHERE username = ?");
			try {
				statement.setString(1, username);
				statement.executeUpdate();
				
				statement.close();
				
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Same as above
	 * But here review is deleted when restaurant is deleted
	 * @param restaurant
	 */
	public void removeReview(Restaurant restaurant) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("DELETE FROM review WHERE restId = ?");
			try {
				statement.setInt(1, restaurant.getId());
				statement.executeUpdate();
				
				statement.close();
				
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method to update users password
	 * @param username
	 * @param newPassword
	 */
	public void updatePassword(String username, String newPassword) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("UPDATE user SET password = ? WHERE username = ?");
			
			try {
				statement.setString(1, newPassword);
				statement.setString(2, username);
				statement.executeUpdate();
				
				statement.close();
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Method to update restaurant
	 * @param restId 
	 * @param contact 
	 * @param address 
	 * @param description 
	 */
	public void updateRestaurant (int restId, String contact, String address, String description) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:reviewEat.sqlite");
			
			PreparedStatement statement = connection.prepareStatement("UPDATE restaurant SET contact = ?, address = ? , description =? WHERE restId = ?");
			
			try {
				statement.setString(1, contact );
				statement.setString(2, address);
				statement.setString(3, description);
				statement.setInt(4, restId);
				statement.executeUpdate();
				
				statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				statement.close();
				connection.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
