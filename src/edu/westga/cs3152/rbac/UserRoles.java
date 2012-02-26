package edu.westga.cs3152.rbac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Raymond Hill
 *
 */
public class UserRoles {
	
	private File theFile;
	
	//a dictionary of user roles
	private Map<UserId, ArrayList<String>> userRoles;
	
	/**
	 * Instantiate the userRoles hashmap.
	 */
	public UserRoles ()
	{
		this.userRoles = new HashMap<UserId, ArrayList<String>>();
	}
	
	/**
	 * Instantiate the userRoles hashmap with a text file.
	 */
	public UserRoles (File theFile)
	{
		this.userRoles = new HashMap<UserId, ArrayList<String>>();
		this.theFile = theFile;
		
		this.buildDictionary();
	}
	
	/**
	 * Add a UserId with roles to this dictionary
	 * 
	 * Precondition: userid != null && roles.isEmpty() != true && !this.contains(userid)
	 * 
	 * @param userid The UserId to be added to this dictionary
	 * @param roles The ArrayList of role Strings to be added to this dictionary
	 */
	public void addWithRoleList (UserId userid, ArrayList<String> roles)
	{
		if (userid == null || roles.isEmpty() || this.contains(userid)) {
			return;
		}
		// add userid to the collection of Strings
		userRoles.put(userid, roles);
	}
	
	/**
	 * Add a UserId with roles to this dictionary
	 * 
	 * Precondition: userid != null && role != null && !this.contains(userid)
	 *  
	 * @param userid The UserId to be added to this dictionary
	 * @param role The singular role String to be added to this dictionary
	 */
	public void addWithRoleString (UserId userid, String role)
	{
		if (userid == null || role == null || this.contains(userid)) {
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
		if (userid == null || !this.contains(userid)) {
			return;
		}
		// remove userid from the collection of Strings
		this.userRoles.remove(userid);
	}

	/**
	 * Return a boolean indicating if the UserId parameter, userid, is contained 
	 * in this dictionary.  
	 * 
	 * @param userid The UserId to check if is contained in this dictionary
	 * @return true iff the specified UserId is contained in this dictionary   
	 */
	public boolean contains (UserId userid)
	{	
		// check whether userid is contained in the collection of UserIds
		return this.userRoles.containsKey(userid);
	}
	
	/**
	 * Return an ArrayList of UserIds as a value with userid as a key
	 * 
	 * Precondition: userid != null && this.contains(userid)
	 * 
	 * @param userid The UserId to get the ArrayList of values for
	 * @return an ArrayList of roles contained in this dictionary
	 */
	public ArrayList<String> getValues (UserId userid)
	{
		if (userid == null || !this.contains(userid)) {
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
	
	/**
	 * Parse the input UserRoles file and put each userid into the dictionary.  The
	 * value of each userid is used as the key, and an ArrayList<String> of roles is
	 * placed in the corresponding value.
	 */
	private void buildDictionary () {
			
		try {
			
			String line = "";
			
			FileInputStream inStream = new FileInputStream(this.theFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			
			while ((line = reader.readLine()) != null) {
				
				ArrayList<String> roles = new ArrayList<String>();
				String[] lineStr = line.split(":");
				UserId userid = new UserId(lineStr[0]);
				String[] roleStr = lineStr[1].split(",");
				for (String role : roleStr) {
					roles.add(role);
				}
				
				this.addWithRoleList(userid, roles);
			}
		} catch (FileNotFoundException ex) {
			
			System.err.println("Could not locate the file " + this.theFile.getName() 
					+ " to build the dictionary from.\nPlease make sure the file exists.");
			
		} catch (IOException e) {
			
			System.err.println("An I/O exception has occured when attempting the parse the file.");
		}
	}
}
