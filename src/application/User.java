/**
 * 
 */
package application;

/**
 * This is sub class of SoftwareUser class.
 * It represents just regular user
 * @author Yogesh Parajuli
 *
 */
public class User extends SoftwareUsers{
	
	private String fName,sName,email;
	
	/**
	 * Constructor for User class
	 * We also set the privilege to Not Privileged as regular user 
	 * doesnot have admin privilege.
	 * 
	 * @param username
	 * @param password
	 * @param fName
	 * @param sName
	 * @param email
	 * @throws InputValidationException 
	 */
	
	public User(String username, String password, String fName, String sName, String email) throws InputValidationException {
		super(username, password);
		setfName(fName);
		setEmail(email);
		setsName(sName);	
		privilege = new notPrivileged();
		
	}
	
	/**
	 * Constructor for User class
	 * We also set the privilege to Not Privileged as regular user 
	 * doesnot have admin privilege.
	 * 
	 * @param username
	 * @param password
	 * @param fName
	 * @param sName
	 * @param email
	 * @throws InputValidationException 
	  
	 */
	public User(String username, String fName, String sName, String email) throws InputValidationException{
		super(username);
				setfName(fName);
				setEmail(email);
				setsName(sName);
//		this.fName = fName;
//		this.email = email;
//		this.sName = sName;
		privilege = new notPrivileged();

	}
	
	
	

	/**
	 * Getter method for fName
	 * @return fName
	 */
	public String getfName() {
		return fName;
	}

	
	/**
	 * Setter method for fName
	 * The name has to be all alphabetic characters. 
	 *
	 * @param fName
	 * @throws InputValidationException 
	 */
	public void setfName(String fName) throws InputValidationException {
		if(fName.matches("^[a-zA-Z]{3,15}$")) {
			this.fName = fName;
		}
		else {
			throw new InputValidationException();
		}
	}

	/**
	 * Getter method for sName
	 * @return sName
	 */
	public String getsName() {
		return sName;
	}

	/**
	 * Setter method for sName
	 * Again, similar Regex pattern to the fName
	 * @param sName
	 * @throws InputValidationException 
	 */
	public void setsName(String sName) throws InputValidationException {
		if(sName.matches("^[a-zA-Z]{3,15}$")) {
			this.sName = sName;
		}
		else {
			throw new InputValidationException();
		}
	}

	/**
	 * Getter method for email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	
	
	/**
	 * Setter method for email
	 * Appropriate regex has been used.
	 * @param email
	 * @throws InputValidationException 
	 */
	public void setEmail(String email) throws InputValidationException {
		if(email.matches("^(.+)@(.+)$")) {
			this.email = email;	
		}
		else {
			throw new InputValidationException();
		}
		
	}

	@Override
	public String toString() {
		//return "User [fName=" + getfName() + ", sName=" + getsName() + ", email=" + getEmail() + ", Username=" + getUsername()
		//		+ "]";
		return "Username: " + getUsername() + "\nEmail: " + getEmail();
	}
	

	
	

}
