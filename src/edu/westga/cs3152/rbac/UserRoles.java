package edu.westga.cs3152.rbac;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author theinfamousrj
 *
 */
public class UserRoles {
	
	Map<String, ArrayList<String>> user;
	
	public UserRoles (String userid, ArrayList<String> roles)
	{
		user.put(userid, roles);
	}
	
	public UserRoles (String userid, String role)
	{
		ArrayList<String> roles = new ArrayList<String>();
		roles.add(role);
		user.put(userid, roles);
	}

}
