package edu.westga.cs3152.rbac;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;


public class RuleSetTestIntersection {
	
	@Test
	public void testIntersectionNull() {
		RuleSet ruleSet = new RuleSet();
		
		assertEquals(ruleSet.intersection(null), null);
		assertEquals(ruleSet.size(), 0);
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		assertEquals(ruleSet.intersection(null), null);
		assertEquals(ruleSet.size(), 3);
	}
	
	@Test
	public void testIntersectionEmptySets() {	
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		RuleSet intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 0);
		assertEquals(ruleSet.size(), 0);
		assertEquals(ruleSetParm.size(), 0);
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 0);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 0);
		
		ruleSet.init(new ArrayList<Rule>());
		ruleSetParm.init(rules);
		intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 0);
		assertEquals(ruleSet.size(), 0);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testIntersectionEqualSets() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		ruleSetParm.init(rules);
		RuleSet intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 3);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
		
		rules = new ArrayList<Rule>();
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		ruleSetParm.init(rules);
		intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 3);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testIntersectionSubsets() {
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
		RuleSet intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 3);
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
		intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 3);
		assertEquals(ruleSet.size(), 5);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testIntersectionOverlappingSets() {
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
		RuleSet intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 2);
		assertEquals(ruleSet.size(), 6);
		assertEquals(ruleSetParm.size(), 5);
	}
	
	@Test
	public void testIntersectionDisjoint() {
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
		
		RuleSet intersection = ruleSet.intersection(ruleSetParm);
		assertEquals(intersection.size(), 0);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
	}
}
