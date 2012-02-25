package edu.westga.cs3152.rbac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author theinfamousrj
 *
 */
public class UserRoles {
	
	Map<UserId, ArrayList<String>> userRoles;
	
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
	 * Precondition: userid != null
	 * Precondition: roles.isEmpty() != true
	 * 
	 * @param userid The UserId to be added to this dictionary
	 * @param roles The ArrayList of role Strings to be added to this dictionary
	 */
	public void add (UserId userid, ArrayList<String> roles)
	{
		if (userid.toString() == null || roles.isEmpty()) {
			return;
		}
		userRoles.put(userid, roles);
	}
	
	/**
	 * Add a UserId to this dictionary
	 * 
	 * Precondition: userid != null
	 * Precondition: role != null
	 *  
	 * @param userid The UserId to be added to this dictionary
	 * @param role The singular role String to be added to this dictionary
	 */
	public void add (UserId userid, String role)
	{
		if (userid.toString() == null || role == null) {
			return;
		}
		ArrayList<String> roles = new ArrayList<String>();
		roles.add(role);
		userRoles.put(userid, roles);
	}
	
	/**
	 * Remove a UserId from this dictionary
	 * 
	 * Precondition: userid != null
	 * 
	 * @param userid The UserId to be removed from this dictionary   
	 */	
	private void remove(UserId userid) {
		
		if (userid.toString() == null) {
			return;
		}
		// remove word from the collection of words
		this.userRoles.remove(userid);
	}

	/**
	 * Return a boolean indicating if the UserId parameter, userid, is contained 
	 * in this dictionary.  
	 * 
	 * @param userid The UserId to check if is contained in this dictionary
	 * @return true iff the specified UserId is contained in this dictionary   
	 */
	public boolean contains(UserId userid) {
		
		// check whether word is contained in the collection of words
		return this.userRoles.containsKey(userid);
	}
	
}
