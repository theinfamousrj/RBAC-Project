package edu.westga.cs3152.rbac;


import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Rule.java - class Rule
 * Provides a class to represent an access rule
 *
 * @author CS3152
 * @version Spring 2012
 */
public class Rule {
	// constants representing three differnt access forms
	public static final int NONE = 0;
	public static final int DENY = 1;
	public static final int READ = 2;
	public static final int WRITE = 3;
	public static final String[] accessStr = {"NONE","DENY","READ","WRITE"};
	 
	// the three parts of an access rule 
	private String role;
	private String object;
	private int access;
	
	/**
	 * Constructors
	 */
	public Rule(String r, String o, int a) 
	{
		role = r;
		object = o;
		access = checkAccess(a);
		if (access == -1)
		{
			access = NONE;
			System.out.println("*** invalid rule ***");
		}
	}

	public Rule(String r, String o, String a) 
	{
		role = r;
		object = o;
		access = checkAccess(a);
		if (access == -1)
		{
			access = NONE;
			System.out.println("*** invalid rule ***");
		}	
	}

	/**
	 * Checks whether the specified string a represents a 
	 * valid access code. Returns the code for the access 
	 * right if the specified string a is valid ("NONE", 
	 * "DENY", "READ", "WRITE"). Returns -1 if the spedicifed 
	 * string a does not represent a valid access right.
	 */
	 private static int checkAccess(String a)
	 {
	 	a = a.toUpperCase();
		for (int i=0; i<4; i++) 
		{
			if (a.equals(accessStr[i]))
			{
				return i;
			}
		}
		return -1;
	 }
	 
	/**
	 * Checks whether the specified value a is a valid
	 * access code. Returns the code for the access right 
	 * if the specified value a is valid (0-3). Returns -1
	 * if the spedicifed value a is invalid.
	 */
	 private static int checkAccess(int a)
	 {
	 	if (a >= 0 && a < 4)
	 	{
	 		return a;
	 	}
	 	return -1;
	 }
	 
	/**
 	 * get methods
 	 */
 	public String getRole()
 	{
 		return role;
 	}
 	
 	public String getObject()
 	{
 		return object;
 	}
 	
 	public int getAccess()
 	{
 		return access;
 	}
 	
 	/**
 	 * set methods
 	 */
 	public void setRole(String r)
 	{
 		role = r;
 	}
 	
 	public void setObject(String o)
 	{
 		object = o;
 	}
 	
 	public void setAccess(String a)
 	{
 		int aInt = checkAccess(a); 
 		if (aInt != -1)
 		{
 			access = aInt;
 		}
 	}
 	
 	public void setAccess(int a)
 	{
 		if (checkAccess(a) != -1)
 		{
 			access = a;
 		}
 	}
 	/**
 	 *overrides equals
 	 */
 	public boolean equals(Object o)
 	{
 		Rule r = (Rule)o;
 		return (r.role.equals(role) && r.object.equals(object) && r.access==access);
 	}
 	 
 	/**
 	 * Returns true if this rule matches the specified rule r.
 	 * This rule matches r if and only if r is not null and
 	 * (r.role==null ? True : r.role.equals(this.role)) and
 	 * (r.object==null ? True : r.object.equals(this.object)) and
 	 * (r.access==NONE ? True : r.access==this.access).
 	 */
 	public boolean matches(Rule r)
 	{
 		if (r==null) return false;
 		if (r.role != null && !r.role.equals(this.role)) return false;
		if (r.object != null && !r.object.equals(this.object)) return false;
		if (r.access != NONE && r.access != this.access) return false;
		return true;
 	}
 	
	/**
 	 * read rule file
 	 * <p>
 	 * precondition: the specified file ruleFileName 
 	 * has to meet the following format:
 	 * - each line contains exactly one rule
 	 * - each rule consists of the three strings representing the role, 
 	 *   the object, and the access, respectively
 	 * - the parts are separated by a single space character
 	 * <p>
 	 * postcondition: returns null iof the specified file ruleFileName 
 	 * could not be read or if the file is not formatted correctly; 
 	 * otherwise, returns a ;ist of the rules given in the specified 
 	 * file ruleFileName 
 	 */
	public static ArrayList<Rule> readRuleFile(String ruleFileName)
	{
		ArrayList<Rule> ruleList = new ArrayList<Rule>();
		String role, object, accessString;
		int access;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(ruleFileName));
			// read first rule
			String line = reader.readLine();
			while (line != null) 
			{
				// get role
				String[] ruleParts = line.split(" ", 4);
		   		if (ruleParts.length != 3) 
			   	{
			   		System.out.println("*** invalid rule ***");
		 			return null;
			   	}
			   	access = checkAccess(ruleParts[2]);
			   	if (access == -1 || access == NONE)
		 		{
		 			System.out.println("*** invalid rule - invalid access ***");
			   		return null;
			   	}
			   		
			   	// add new rule
			   	ruleList.add(new Rule(ruleParts[0], ruleParts[1], access));
			   	
			   	// read next rule
				line = reader.readLine();
			}
			reader.close();
		}
		catch (Exception e)
		{
			return null;
		}
		
		return ruleList;
	}

	/**
	 * test readRuleFile
	 */
	public static void main(String[] args)
	{
		ArrayList<Rule> rules = readRuleFile("rules.txt");
    	if (rules == null)
    	{
    		System.out.println("rule file could not be read");
    	}
    	else
    	{
    		for(Rule r : rules) 
    		{
    			System.out.println(r.getRole()+", "+r.getObject()+", "+Rule.accessStr[r.getAccess()]);
    		}
    	}
    }
}