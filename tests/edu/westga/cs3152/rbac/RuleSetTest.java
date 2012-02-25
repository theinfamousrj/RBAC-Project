package edu.westga.cs3152.rbac;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import java.util.Iterator;

public class RuleSetTest {

	@Test
	public void constructorEmptySet() {
		RuleSet ruleSet = new RuleSet();
		
		assertEquals(ruleSet.size(), 0);
	}

	@Test
	public void testInit() {
		RuleSet ruleSet = new RuleSet();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		rules.add(new Rule("nurse", "drug", Rule.READ));
		ruleSet.init(rules);
		assertEquals(ruleSet.size(), 1);
		
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		assertEquals(ruleSet.size(), 3);
		
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		assertEquals(ruleSet.size(), 3);
	}
	
	@Test
	public void testInitNull() {
		RuleSet ruleSet = new RuleSet();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		ruleSet.init(null);
		assertEquals(ruleSet.size(), 3);
	}
	
	@Test
	public void testInitAfter() {
		RuleSet ruleSet = new RuleSet();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		
		rules.remove(0);
		
		assertEquals(ruleSet.size(), 3);
	}


	@Test
	public void testContains() {
		RuleSet ruleSet = new RuleSet();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		
		assertTrue(ruleSet.contains(new Rule("nurse", "drug", Rule.READ)));
		assertTrue(ruleSet.contains(new Rule("physician", "drug", Rule.WRITE)));
		assertTrue(ruleSet.contains(new Rule("administrator", "address", Rule.WRITE)));
		assertFalse(ruleSet.contains(null));
		assertFalse(ruleSet.contains(new Rule("nurse", "address", Rule.READ)));
		
		rules.add(null);
		ruleSet.init(rules);
		assertTrue(ruleSet.contains(null));
	}

	@Test
	public void testGetSubset() {
		RuleSet ruleSet = new RuleSet();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		RuleSet subset = ruleSet.getSubset(new Rule(null, null, Rule.NONE));
		assertEquals(subset.size(), 0);
		
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		subset = ruleSet.getSubset(new Rule(null, null, Rule.NONE));
		assertEquals(subset.size(), 3);

		subset = ruleSet.getSubset(new Rule(null, "drug", Rule.NONE));
		assertEquals(subset.size(), 2);
		
		subset = ruleSet.getSubset(new Rule(null, "drug", Rule.WRITE));
		assertEquals(subset.size(), 1);
		
		subset = ruleSet.getSubset(new Rule("nurse", "drug", Rule.WRITE));
		assertEquals(subset.size(), 0);
		
		subset = ruleSet.getSubset(null);
		assertEquals(subset.size(), 0);
		
		subset = ruleSet.getSubset(new Rule(null, "drug", Rule.NONE));
		assertEquals(subset.size(), 2);
	}

	@Test
	public void testIterator() {
		RuleSet ruleSet = new RuleSet();
		Iterator<Rule> it = ruleSet.iterator();
		int counter = 0;
		while (it.hasNext()){
			it.next();
			counter++;
		}
		assertEquals(counter, 0);
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("staff", "drug", Rule.DENY));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("visitor", "drug", Rule.DENY));
		rules.add(new Rule("nurse", "drug", Rule.WRITE));
		rules.add(new Rule("staff", "address", Rule.READ));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		it = ruleSet.iterator();
		counter = 0;
		while (it.hasNext()){
			it.next();
			counter++;
		}
		assertEquals(counter, 6);
	}

}
