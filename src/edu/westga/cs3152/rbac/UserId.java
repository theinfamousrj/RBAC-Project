package edu.westga.cs3152.rbac;

import java.lang.Exception;

public class UserId {
	
	// userid
	private String id;
	
	/**
	 *  construct and initialize new userid
	 * 
	 * Precondition: userid is a valid userid
	 *               a valid id consists of three lower case letters followed by 5 digits
	 *               the first letter is a 'c' or a 'd'
	 *               the last two digits are 90, 91, or 92
	 * 
	 * @param newId userid of this UserId object
	 */
	public UserId (String newId)
	{
		boolean invalid = false;
		
		this.id = newId;
		
		try {
			if (this.id.length() != 8) {
				throw new Exception("userid '" + this.id +
				"' has not exactly 8 characters\n");
			}
			else if (!this.id.startsWith("c") &&
				!this.id.startsWith("d")) {
				throw new Exception("userid '" + this.id +
						"' does not start with 'c' or 'd'\n");
			}
			else if (!Character.isLowerCase(this.id.charAt(1)) ||
					!Character.isLowerCase(this.id.charAt(2))) {
				throw new Exception("userid '" + this.id +
					"' does not start with three lowercase letters\n");
			}
			else if (!Character.isDigit(this.id.charAt(3)) ||
					!Character.isDigit(this.id.charAt(4)) ||
					!Character.isDigit(this.id.charAt(5)) ||
					!Character.isDigit(this.id.charAt(6)) ||
					!Character.isDigit(this.id.charAt(7))) {
				throw new Exception("userid '" + this.id +
					"' does not end with five digits\n");
			}
			else if (this.id.charAt(6) != '9' ||
					 this.id.charAt(7) != '0' &&
					 this.id.charAt(7) != '1' &&
					 this.id.charAt(7) != '2') {
				throw new Exception("userid '" + this.id +
					"' does not end with the digits 90, 91, or 92 \n");
			}
		}
		catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	/**
	 * return this userid represented as string
	 * 
	 * @return userid
	 */
	public String toString()
	{
		return this.id;
	}
	
	/**
	 * return the hospital location where this user has been registered
	 * 
	 * Precondition: this userid starts with 'c' or 'd'
	 * @return "Carrollton" if userid starts with 'c' and
	 *         "Douglasville" if userid starts with 'd' 
	 */
	public String registeredAt()
	{
		if (this.id.startsWith("c")) {
			return "Carrollton";
		}
		return "Douglasville";
	}
	
	/**
	 * returns the hash code for the specific userid
	 * 
	 * Precondition: this userid is a valid userid
	 * @return a positive hash code based on the userid
	 */
	public int hashCode()
	{
		return Math.abs(this.id.hashCode());
	}
}
