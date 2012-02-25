package edu.westga.cs3152.rbac;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * RuleSet.java - class RuleSet
 * Provides a class that implements Set<Rule>
 *
 * @author CS3152
 * @version Spring 2012
 */
public class RuleSet implements Set<Rule>, Iterable<Rule>
{
	
	private ArrayList<Rule> rules;
	
	/**
	 * Creates an empty rule set
	 */
	public RuleSet()
	{
		this.rules = new ArrayList<Rule>();
	}
	
	/**
	 * Returns the number of elements in this set.
	 */
	public int size()
	{
		return rules.size();
	} 
	
	/**
	 * Removes all elements from this RuleSet and adds 
	 * all elements of the specified ArrayList list 
	 * to this RuleSet.
	 * If the specified ArrayList list is empty, 
	 * this RuleSet is empty after the operation.
	 * If list is null, this set remains unchanged.
	 */
	public void init(ArrayList<Rule> list)
	{
		if (list == null) return;
		
		rules = new ArrayList<Rule>();
		for (Rule r : list) {
			if (!this.rules.contains(r)) {
				this.rules.add(r);
			}
		}
	}
	
	/**
	 * Returns true if the specified rule r is 
	 * contained in this set. Formally, returns true
	 * if and only if this set contains at least one
	 * rule x such that 
	 * (r==null? x==null : el.equals(x)).
	 */
	public boolean contains(Rule r)
	{
		return this.rules.contains(r);
	}
	
	/**
	 * Returns a subset of this RuleSet that contains
	 * exactly the elements x of this RuleSet such that
	 * x.matches(r) returns true.
	 */
	public RuleSet getSubset(Rule r)
	{	
		RuleSet subset = new RuleSet();
		for (Rule aRule : this.rules){
			if (aRule.matches(r)){
				subset.add(aRule);
			}
		}
			
		return subset;
	}
	
	/**
	 * Returns a RuleSet that is the union of this set
	 * and the specified set s. If the specified
	 * set s is null, then null is returned.
	 *
	 * Precondition: the specified set is a RuleSet
	 */
	public RuleSet union(Set set)
	{
		if (set == null) {
			return null;
		}
		RuleSet ruleset = (RuleSet)set;
		RuleSet union = new RuleSet();
		for (Rule aRule : this){
			if (!union.contains(aRule)){
				union.add(aRule);
			}
		}
		for (Rule aRule : ruleset){
			if (!union.contains(aRule)){
				union.add(aRule);
			}
		}
		return union;
	} 
	
	/**
	 * Returns a RuleSet that is the intersection of
	 * this RuleSet and the specified set s. If the 
	 * specified set s is null, then null is 
	 * returned.
	 *
	 * Precondition: the specified set is a RuleSet
	 */
	public RuleSet intersection(Set set)
	{
		if (set == null) {
			return null;
		}
		RuleSet ruleset = (RuleSet)set;
		RuleSet intersection = new RuleSet();
		for (Rule aRule : this){
			if (ruleset.contains(aRule) && !intersection.contains(aRule)){
				intersection.add(aRule);
			}
		}
		return intersection;
	}
	
	/**
	 * Returns a RuleSet that is the set difference of
	 * this set in respect to the specified set s.
	 * If the specified set s is null, then null is 
	 * returned.
	 *
	 * Precondition: the specified set is a RuleSet
	 */
	public RuleSet difference(Set set)
	{
		if (set == null) {
			return null;
		}
		RuleSet ruleset = (RuleSet)set;
		RuleSet difference = new RuleSet();
		for (Rule aRule : this){
			if (!set.contains(aRule)){
				difference.add(aRule);
			}
		}
		return difference;
	}
	
	/**
	 * Returns an iterator over this set. The
	 * elements may be retrieved in any order.
	 */
	public Iterator<Rule> iterator()
	{
		return this.rules.iterator();
	}
	
	/**
	 * Adds the specified rule r to this set
	 * iff the rule is not null and not yet 
	 * contained in this RuleSet.
	 */
	public void add(Rule r)
	{
		if (r != null && !this.rules.contains(r)){
			this.rules.add(r);
		}
	}
	
	/**
	 * Returns true iff this set contains the same 
	 * rules as the specified RuleSet set
	 */
	public boolean equals(RuleSet set)
	{	
		for (Rule aRule : this.rules){
			if (!set.contains(aRule)){
				return false;
			}
		}
		for (Rule aRule : set){
			if (!this.contains(aRule)){
				return false;
			}
		}	
		return true;
	}
}
