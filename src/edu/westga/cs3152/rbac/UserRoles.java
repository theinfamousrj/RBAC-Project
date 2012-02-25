package edu.westga.cs3152.rbac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raymond Hill
 *
 */
public class UserRoles {
	
	private Map<UserId, ArrayList<String>> userRoles;
	
	/**
	 * Instantiate the userRoles hashmap.
	 */
	public UserRoles ()
	{
		this.userRoles = new HashMap<UserId, ArrayList<String>>();
	}
	
	/**
	 * Add a UserId to this dictionary
	 * 
	 * Precondition: userid != null && roles.isEmpty() != true && !this.contains(userid)
	 * 
	 * @param userid The UserId to be added to this dictionary
	 * @param roles The ArrayList of role Strings to be added to this dictionary
	 */
	public void addWithRoleList (UserId userid, ArrayList<String> roles)
	{
		if (userid.toString() == null || roles.isEmpty() || this.contains(userid)) {
			return;
		}
		// add userid to the collection of UserIds
		userRoles.put(userid, roles);
	}
	
	/**
	 * Add a UserId to this dictionary
	 * 
	 * Precondition: userid != null && role != null && !this.contains(userid)
	 *  
	 * @param userid The UserId to be added to this dictionary
	 * @param role The singular role String to be added to this dictionary
	 */
	public void addWithRoleString (UserId userid, String role)
	{
		if (userid.toString() == null || role == null || this.contains(userid)) {
			return;
		}
		ArrayList<String> roles = new ArrayList<String>();
		roles.add(role);
		// add userid to the collection of UserIds
		userRoles.put(userid, roles);
	}
	
	/**
	 * Remove a UserId from this dictionary
	 * 
	 * Precondition: userid != null && this.contains(userid)
	 * 
	 * @param userid The UserId to be removed from this dictionary   
	 */	
	public void remove (UserId userid)
	{
		if (userid.toString() == null || !this.contains(userid)) {
			return;
		}
		// remove userid from the collection of UserIds
		this.userRoles.remove(userid);
	}

	/**
	 * Return a boolean indicating if the UserId parameter, userid, is contained 
	 * in this dictionary.  
	 * 
	 * @param userid The UserId to check if is contained in this dictionary
	 * @return true iff the specified UserId is contained in this dictionary   
	 */
	public boolean contains(UserId userid)
	{	
		// check whether userid is contained in the collection of UserIds
		return this.userRoles.containsKey(userid);
	}
	
	/**
	 * Return an ArrayList of Strings as a value with userid as a key
	 * 
	 * Precondition: userid != null && this.contains(userid)
	 * 
	 * @param userid The UserId to get the ArrayList of values for
	 * @return
	 */
	public ArrayList<String> getValues (UserId userid)
	{
		if (userid.toString() == null || !this.contains(userid)) {
			return null;
		}
		return this.userRoles.get(userid);
	}
	
	/**
	 * Return an integer value indicating the size of the dictionary
	 * 
	 * @return the size of the dictionary
	 */
	public int getSize ()
	{
		return this.userRoles.size();
	}
}
