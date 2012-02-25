package edu.westga.cs3152.rbac;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RuleSetTestDifference {
	
	@Test
	public void testDifferenceNull() {
		RuleSet ruleSet = new RuleSet();
		
		assertEquals(ruleSet.difference(null), null);
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		assertEquals(ruleSet.difference(null), null);
	}
	
	@Test
	public void testDifferenceEmptySets() {	
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		RuleSet difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 0);
		assertEquals(ruleSet.size(), 0);
		assertEquals(ruleSetParm.size(), 0);
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 3);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 0);
		
		ruleSet.init(new ArrayList<Rule>());
		ruleSetParm.init(rules);
		difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 0);
		assertEquals(ruleSet.size(), 0);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testDifferenceEqualSets() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		ruleSetParm.init(rules);
		RuleSet difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 0);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
		
		rules = new ArrayList<Rule>();
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		ruleSetParm.init(rules);
		difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 0);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testDifferenceSubsets() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		rules = new ArrayList<Rule>();
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		rules.add(new Rule("visitor", "address", Rule.READ));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("nurse", "drug", Rule.DENY));
		ruleSetParm.init(rules);
		RuleSet difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 0);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 5);
		
		rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSetParm.init(rules);
		rules = new ArrayList<Rule>();
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		rules.add(new Rule("visitor", "address", Rule.READ));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("nurse", "drug", Rule.DENY));
		ruleSet.init(rules);
		difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 2);
		assertEquals(ruleSet.size(), 5);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testDifferneceOverlappingSets() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("staff", "drug", Rule.DENY));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("visitor", "drug", Rule.DENY));
		rules.add(new Rule("nurse", "drug", Rule.WRITE));
		rules.add(new Rule("staff", "address", Rule.READ));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		rules = new ArrayList<Rule>();
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		rules.add(new Rule("visitor", "address", Rule.READ));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("nurse", "drug", Rule.DENY));
		ruleSetParm.init(rules);
		RuleSet difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 4);
		assertEquals(ruleSet.size(), 6);
		assertEquals(ruleSetParm.size(), 5);
	}
	
	@Test
	public void testDifferenceDisjoint() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		
		rules = new ArrayList<Rule>();
		rules.add(new Rule("visitor", "address", Rule.READ));
		rules.add(new Rule("visitor", "drug", Rule.DENY));
		rules.add(new Rule("nurse", "drug", Rule.DENY));
		ruleSetParm.init(rules);
		
		RuleSet difference = ruleSet.difference(ruleSetParm);
		assertEquals(difference.size(), 3);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
	}
}
