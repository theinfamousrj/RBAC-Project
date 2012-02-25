package edu.westga.cs3152.rbac;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * Set.java - Set Interface
 *
 * @author CS3152
 * @version Spring 2012
 */
public interface Set<T>
{
	/**
	 * Removes all elements from this set and adds 
	 * all elements of the specified ArrayList list 
	 * to this set.
	 * If the specified ArrayList list is empty, 
	 * this set is empty after the operation.
	 * If list is null, this set remains unchanged.
	 */
	public void init(ArrayList<T> list);
	
	/**
	 * Returns the number of elements in this set.
	 */
	public int size();
	
	/**
	 * Returns true if the specified element el is 
	 * contained in this set. Formally, returns true
	 * if and only if this set contains at least one
	 * element x such that 
	 * (el==null? x==null : el.equals(x)).
	 */
	public boolean contains(T el);
	
	/**
	 * Returns a subset of this set that contains
	 * exactly the elements of this set that match
	 * the specified element el.
	 * If T implements the method matches, then the
	 * returned set contains exactly the elements x
	 * of this set such that x.matches(el) returns 
	 * true.
	 * If T does not implement matches and el!=null, 
	 * the set contains all elements x of this set
	 * with (el==null? x==null : el.equals(x)).
	 */
	public Set getSubset(T el);
	
	/**
	 * Returns a set that is the union of this set
	 * and the specified set s. If the specified
	 * set s is null, then null is returned.
	 */
	public Set union(Set s);
	
	/**
	 * Returns a set that is the intersection of
	 * this set and the specified set s. If the 
	 * specified set s is null, then null is 
	 * returned.
	 */
	public Set intersection(Set s);
	
	/**
	 * Returns a set that is the set difference of
	 * this set in respect to the specified set s.
	 * If the specified set s is null, then null is 
	 * returned.
	 */
	public Set difference(Set s);
	
	/**
	 * Returns an iterator over this set. The
	 * elements may be retrieved in any order.
	 */
	public Iterator<T> iterator();
}