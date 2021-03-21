package application;

/**
 * Represents user of the software
 * @author Yogesh Parajuli
 *
 */
public class SoftwareUsers{


	private String username;
	private String password;

	/**
	 * We use instance variable that is subclass of UsabilityPrivilege Interface
	 * 
	 */
	public UsabilityPrivilege privilege;

	/**
	 * Constructor for SoftwareUsers class
	 * @param username 
	 * @param password 
	 * @throws InputValidationException 
	 * 
	 */
	public SoftwareUsers(String username, String password) throws InputValidationException {
		setUsername(username);
		setPassword(password);

	}
	/**
	 * Second Constructor for Software User class
	 * This is used when retrieving data from the database
	 * @param username
	 */
	public SoftwareUsers(String username) {
		this.username = username;
	}

	/**
	 * Getter method for username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter method for username
	 * The user name should can have any alphabet and numbers.
	 * The length of username should be 3 or more and less than 10.
	 * @param username
	 * @throws InputValidationException 
	 */
	public void setUsername(String username) throws InputValidationException {
		if(username.matches("^[a-zA-Z0-9]{3,10}$")) {
			this.username = username;	
		}
		else {
			throw new InputValidationException();
		}

	}

	/**
	 * Getter method for password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter method for password
	 * The password can be any alphanumeric value.
	 * The length of password should be 6 letters or more and less than 20
	 * @param password
	 * @throws InputValidationException 
	 */
	public void setPassword(String password) throws InputValidationException {
		if(password.matches("^[a-zA-Z0-9]{6,20}$")) {
			this.password = password;	
		}
		else {	
			throw new InputValidationException();
		}
	}

	/**
	 * This method allows the subclasses to have their own adminPrivilege
	 * @return privilege
	 * 
	 */
	public boolean areYouAdmin() {
		return privilege.adminPrivilege();
	}

	@Override
	public int hashCode() {
		return getUsername().hashCode();
	}


}
