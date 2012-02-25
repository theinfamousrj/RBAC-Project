package edu.westga.cs3152.rbac;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.*;

/**
 * PDP.java - Policy Decision Point
 *
 * @author CS3151
 * @version Spring 2012
 */
public class PDP
{
	// constants defining policy options
	public static final byte DENY_OVERRIDES = 0;  // default policy
	public static final byte PERMIT_OVERRIDES = 1;
	public static final byte WRITE_WITHOUT_READ = 0;  // default policy
	public static final byte WRITE_IMPLIES_READ = 1;
	
	// Policies of this PDP
	private byte overridePolicy;
	private byte readWritePolicy;
	
	// set of rules
	private RuleSet rules; 

	/**
	 * constructors - initializes data members
	 * 
	 * the more conservative policies are chosen by default 
	 * if no policy or an invalid policy is selected
	 */
	public PDP()
    {
		overridePolicy = DENY_OVERRIDES;
    	readWritePolicy = WRITE_WITHOUT_READ;
    	rules = new RuleSet();
    }
    
	public PDP(RuleSet r)
    {
		overridePolicy = DENY_OVERRIDES;
    	readWritePolicy = WRITE_WITHOUT_READ;
    	rules = r;
    }

	public PDP(String ruleFileName)
    {
		overridePolicy = DENY_OVERRIDES;
    	readWritePolicy = WRITE_WITHOUT_READ;
    	setRules(ruleFileName);
    }
    
    public PDP(byte oPolicy, byte rwPolicy, RuleSet r)
    {
		setOverridePolicy(oPolicy);
    	setReadWritePolicy(rwPolicy);
		setRules(r);
    }

    public PDP(byte oPolicy, byte rwPolicy, String ruleFileName)
    {
		setOverridePolicy(oPolicy);
		setReadWritePolicy(rwPolicy);
		setRules(ruleFileName);
    }

	/**
	 * set methods for data members of this PDP
	 */
	public void setOverridePolicy(byte p)
	{
		if (p != DENY_OVERRIDES && p != PERMIT_OVERRIDES) 
		{
			overridePolicy = DENY_OVERRIDES;
    	}
    	else 
    	{
    		overridePolicy = p;
    	}
	} 

	public void setReadWritePolicy(byte p)
	{
		if (p != WRITE_WITHOUT_READ && p != WRITE_IMPLIES_READ) 
    	{
			readWritePolicy = WRITE_WITHOUT_READ;
    	}
    	else 
    	{
    		readWritePolicy = p;
    	}
	}
	
    public void setRules(String fileName)	
    {
    	rules = new RuleSet();
    	rules.init(Rule.readRuleFile(fileName));
    	if (rules == null)
    	{
    		rules = new RuleSet();
    	}
    }
	
	public void setRules(RuleSet r)	
	{
    	if (r == null)
    	{
    		rules = new RuleSet();
    	}
    	else
    	{
    		rules = r;
    	}
    }
    
	/**
	 * isGranted - determine whether access request is granted
	 * <p>
	 * Returns true if the specified request (role, object, access)
	 * should be granted. Otherwise, returns false. (See the project
	 * description for details.)
	 * <p>
	 * Precondition: role is a valid role, object is an existing object,
	 * access has value Request.READ (1) or Request.WRITE (2).
	 */
    public boolean requestGranted(String role, String object, byte access)
    {
    	boolean isGranted;
    	
    	Rule accessRule = new Rule(role, object, access);
    	
    	// check if a rule grants the requested access
    	isGranted = this.rules.contains(accessRule);
    	if (!isGranted && access == Rule.READ && this.readWritePolicy == WRITE_IMPLIES_READ) {
    		accessRule.setAccess(Rule.WRITE);
    		isGranted = this.rules.contains(accessRule);
    	}
    	
    	// check if no rule denies the requested access in case of DENY_OVERRIDES
    	if (isGranted && this.overridePolicy == DENY_OVERRIDES) {
    		Rule denyRule = new Rule(role, object, Rule.DENY);
    		isGranted = !this.rules.contains(denyRule);
    	}
    	
    	return isGranted;
    }
    
    /**
	 * isGranted - determine whether access request is granted
	 * <p>
	 * Returns true if the specified request (role, object, access)
	 * should be granted. Otherwise, returns false. (See the project
	 * description for details.)
	 * <p>
	 * Precondition: role is a valid role, object is an existing object,
	 * access has value Request.READ (1) or Request.WRITE (2).
	 */
    public boolean requestGranted(UserId user, String object, byte access)
    {
    	boolean isGranted;
    	String role = null;
    	
    	
    	Rule accessRule = new Rule(role, object, access);
    	
    	// check if a rule grants the requested access
    	isGranted = this.rules.contains(accessRule);
    	if (!isGranted && access == Rule.READ && this.readWritePolicy == WRITE_IMPLIES_READ) {
    		accessRule.setAccess(Rule.WRITE);
    		isGranted = this.rules.contains(accessRule);
    	}
    	
    	// check if no rule denies the requested access in case of DENY_OVERRIDES
    	if (isGranted && this.overridePolicy == DENY_OVERRIDES) {
    		Rule denyRule = new Rule(role, object, Rule.DENY);
    		isGranted = !this.rules.contains(denyRule);
    	}
    	
    	return isGranted;
    }
}