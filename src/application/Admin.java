/**
 * 
 */
package application;

/**
 * This is a sub class of SoftwareUsers.
 * 
 * Uses static attributes for admin Username and admin Password
 * so that they are not changable.
 * Represents user with admin privilege
 * 
 * @author Yogesh Parajuli
 *  Graded Unit Project 
 *
 */



public class Admin  extends SoftwareUsers{
	private static String adminUsername = "admin";
	private static String adminPassword = "administrator";	


	/**
	 * Constructor for admin.
	 * We set the privilege to Privileged as admin has admin privileges. 
	 * @throws InputValidationException 
	 * 
	 */
	public Admin() throws InputValidationException {
		super(adminUsername, adminPassword);
		privilege = new Privileged();
	}


	/**
	 * Getter method for admin username
	 * @return Admin's username
	 */
	public static String getAdminUsername() {
		return adminUsername;
	}

	
	/**
	 * Getter method for admin password.
	 * @return Admin's Password
	 */
	public static String getAdminPassword() {
		return adminPassword;
	}
	
	
	

}
