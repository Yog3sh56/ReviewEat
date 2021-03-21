/**
 * 
 */
package application;

/**
 * This class represents the review.
 * 
 * @author Yogesh Parajuli
 * 
 */
public class Review {
	private int reviewId;
	private String description;
	private int rating;
	
	
	/**
	 * Constructor for Review class
	 * @param description
	 * @param rating
	 * @throws InputValidationException 
	 */
	public Review(String description, int rating) throws InputValidationException {
		setDescription(description);
		setRating(rating);
		
	}
	/**
	 * Constructor used during data retrival from database
	 * @param reviewId 
	 * @param rating
	 * @param description
	 * @throws InputValidationException 
	 */
	public Review(int reviewId, int rating, String description)  {
		
		this.description =  description;
		this.rating = rating;
		setReviewId(reviewId);
	}


	/**
	 * Getter for review id
	 * @return reviewID
	 */
	public int getReviewId() {
		return reviewId;
	}
	
	/**
	 * Setter for reviewId
	 * @param reviewId
	 */
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	/**
	 * Getter method for description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * Setter method for description
	 * It has a regex to validate the input.
	 * @param description
	 * @throws InputValidationException 
	 */
	public void setDescription(String description) throws InputValidationException {
		if(description.matches("(\\p{ASCII}){4,100}")) {
			
		this.description = description;
		}
		else {
			throw new InputValidationException();
		}
	}

	/**
	 * Getter method for rating
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}


	/**
	 * Setter method for rating 
	 * @param rating
	 * @throws InputValidationException 
	 */
	public void setRating(int rating) throws InputValidationException {
		if(rating<=10 && rating >0) {
			this.rating = rating;
		}
		else {
			throw new InputValidationException();
			
		}
	}


	@Override
	public String toString() {
		return getDescription() + "\n Rating: " + getRating();
	}
	
	

}
