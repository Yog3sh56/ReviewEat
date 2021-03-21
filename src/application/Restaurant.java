/**
 * 
 */
package application;


import java.util.HashMap;
import java.util.Iterator;


/**
 * 
 * This class represents the Restaurant
 * Implements Comparable Interface to compare restaurants based on average ratings 
 * @author Yogesh Parajuli
 *
 */
public class Restaurant implements Comparable<Restaurant>, Iterable<Review>{
	
	private int id;
	private String name;
	private String restaurantDescription;
	private String cuisine;
	private String contact;
	private String address;
	private double averageRating = 0;
	private HashMap<String, Review> reviews  =  new HashMap<String, Review>(); 
	
	
	
	/**
	 * Constructor for restaurant.
	 * This is used when an restaurant object is created
	 * @param name
	 * @param restaurantDescription
	 * @param cuisine
	 * @param contact
	 * @param address 
	 * @throws InputValidationException 
	 */
	public Restaurant(String name, String restaurantDescription, String cuisine, String contact, String address) throws InputValidationException {
		setAddress(address);
		setContact(contact);
		setCuisine(cuisine);
		setName(name);
		setRestaurantDescription(restaurantDescription);
		
	}
	/**
	 * Empty Constructor
	 * 
	 */
	public Restaurant() {}

	/**
	 * Another constructor that takes in restID
	 * This will be used during data retireval from the database
	 * 
	 * @param restID
	 * @param name
	 * @param restaurantDescription
	 * @param cuisine
	 * @param contact
	 * @param address
	 * @throws InputValidationException
	 */
	public Restaurant(int restID, String name, String restaurantDescription, String cuisine, String contact, String address)  {
		this.id = restID;
//		setAddress(address);
//		setContact(contact);
//		setCuisine(cuisine);
//		setName(name);
//		setRestaurantDescription(restaurantDescription);
		this.address = address;
		this.name = name;
		this.contact = contact;
		this.cuisine = cuisine;
		this.restaurantDescription =  restaurantDescription;
		
	}
	
	/**
	 * Getter for reviews
	 * @return reviews
	 */
	public HashMap<String, Review> getReviews() {
		return reviews;
	}

	/**
	 * Setter for reviews
	 * Setter also calls setAverageRating method 
	 * because we want the average rating to change when review is added 
	 * in this case when a collection of reviews are input
	 * @param reviews
	 */
	public void setReviews(HashMap<String, Review>reviews) {
		this.reviews = reviews;
		setAverageRating();
	}

	


	/**
	 * Gettter method for average rating
	 * @return averageRating
	 */
	public double getAverageRating() {
		return averageRating;
	}
	
	/**
	 * Setter for averageRating
	 * 
	 */
	public void setAverageRating() {
		int totalRating= 0;
		int reviewCount = reviews.values().size();
		Iterator<Review> iter = iterator();
		if(reviewCount > 0) {
		while(iter.hasNext()) {
			
			totalRating = totalRating + iter.next().getRating();
		}
		this.averageRating = totalRating/reviewCount;
		}
		else {
			this.averageRating = 0;
		}
	}


	/**
	 * Getter method for ID
	 * @return id
	 */
	public int getId() {
		return id;
	}


	/**
	 * Setter method for ID
	 * The id is stored as integer in the database that automatically increases
	 * @param id
	 */
	public void setId(int id) {
		
		this.id = id;
	}


	/**
	 * Getter method for name
	 * @return name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Setter method for name
	 * @param name
	 * @throws InputValidationException 
	 */
	public void setName(String name) throws InputValidationException {
		if(name.matches("^[a-zA-Z ]{4,30}$")) {
		this.name = name;}
		else {
			throw new InputValidationException();
		}
	}


	/**
	 * Getter method for restaurantDescription
	 * @return restaruant Description
	 */
	public String getRestaurantDescription() {
		return restaurantDescription;
	}


	/**
	 * Setter method for restaurant Description
	 * @param restaurantDescription
	 * @throws InputValidationException 
	 */
	public void setRestaurantDescription(String restaurantDescription) throws InputValidationException {
		if(restaurantDescription.matches("\\p{ASCII}{4,100}")) {
		this.restaurantDescription = restaurantDescription;
		}
		else {
			throw new InputValidationException();
		}
	}


	/**
	 * Getter method for Cuisine
	 * @return cuisine
	 */
	public String getCuisine() {
		return cuisine;
	}


	/**
	 * Setter method for cuisine
	 * @param cuisine
	 * @throws InputValidationException 
	 */
	public void setCuisine(String cuisine) throws InputValidationException {
		if(cuisine.matches("^[a-zA-Z]{3,15}$")) {
		this.cuisine = cuisine;
		}
		else {
			throw new InputValidationException();
		}
		
	}


	/**
	 * Getter method for contacts
	 * @return contact
	 */
	public String getContact() {
		return contact;
	}


	/**
	 * Setter method for contacts
	 * @param contact
	 * @throws InputValidationException 
	 */
	public void setContact(String contact) throws InputValidationException {
		if(contact.matches("\\d{11}")) {
		this.contact = contact;
		}
		else {
			throw new InputValidationException();
		}
	}


	/**
	 * Getter method for address
	 * @return address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * Setter method for address
	 * The address should have digits followed by a space and string characters
	 * @param address
	 * @throws InputValidationException 
	 */
	public void setAddress(String address) throws InputValidationException {
		if(address.matches("^(\\d+)\\s(\\w+)\\s(\\w+)$")) {
			this.address = address;
		}
		else {
			throw new InputValidationException();
		}
		
	}	
	


	/**
	 * Add review when username and review are provided
	 * @param username
	 * @param r
	 * set average rating is called so that the average rating would change dynamically as the review is added.
	 */
	public void addReview( String username, Review r) {
		reviews.put(username,r);
		//updateAverageRating(getAverageRating());;	
		setAverageRating();
	}
	
	
	
	/**
	 * 
	 * Add review when user is provided and description and rating is provided for the review.
	 * @param username
	 * @param description
	 * @param rating
	 * @throws InputValidationException
	 * 
	 * set average rating is called so that the average rating would change dynamically as the review is added.
	 */
	public void addReview( String username, String description, int rating) throws InputValidationException {
		reviews.put(username, new Review(description, rating));
		setAverageRating();
	}
	
	/**
	 * method to remove reivew from a restuarnt
	 * @param user 
	 * @param review
	 */
	public void removeReview(User user, Review review) {
//		reviews.remove(user.getUsername(), review);
//		setAverageRating();
		
		reviews.remove(user.getUsername());
		setAverageRating();
	}


	
	//CompareTo method that we should implement as the class implements comparable
	//Here we are defining comparision constrains
	
	@Override
	public int compareTo(Restaurant o) {
		if (this.averageRating > o.getAverageRating()) {
			return -1;
		}
		else {
			return 1;
		}
		
	}
		
	
	@Override
	public String toString() {	
		
		return 
				name + "(" +  String.format("%.2f", getAverageRating()) + ")" + "\n" + restaurantDescription +"\nCuisine: " +cuisine + "\nAddress: " + address + "\nContact: " +contact; 
	}
	@Override
	public Iterator<Review> iterator() {
		return reviews.values().iterator();
	}
		
}


	


