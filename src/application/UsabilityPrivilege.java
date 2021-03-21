package application;

/**
 * 
 * Using Strategy Design Pattern
 * Has methods that indicates if the software user has admin privilege or not.
 * @author Yogesh Parajuli
 *
 */
public interface UsabilityPrivilege {
	/**
	 * @return true or false i.e. if they have privilege or not. 
	 */
	boolean adminPrivilege();
}

class Privileged implements UsabilityPrivilege{
	public boolean adminPrivilege() {
		return true;
	}
}

class notPrivileged implements UsabilityPrivilege{
	public boolean adminPrivilege() {
		return false;
	}
}
