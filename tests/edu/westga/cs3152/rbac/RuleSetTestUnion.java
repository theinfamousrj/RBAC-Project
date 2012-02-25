package edu.westga.cs3152.rbac;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RuleSetTestUnion {

	@Test
	public void testUnionNull() {
		RuleSet ruleSet = new RuleSet();
		
		assertEquals(ruleSet.union(null), null);
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		assertEquals(ruleSet.union(null), null);
	}
	
	@Test
	public void testUnionEmptySets() {	
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		RuleSet union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 0);
		assertEquals(ruleSet.size(), 0);
		assertEquals(ruleSetParm.size(), 0);
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 3);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 0);
		
		ruleSet.init(new ArrayList<Rule>());
		ruleSetParm.init(rules);
		union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 3);
		assertEquals(ruleSet.size(), 0);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testUnionEqualSets() {
		RuleSet ruleSet = new RuleSet();
		RuleSet ruleSetParm = new RuleSet();
		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		ruleSet.init(rules);
		ruleSetParm.init(rules);
		RuleSet union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 3);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
		
		rules = new ArrayList<Rule>();
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		ruleSetParm.init(rules);
		union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 3);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testUnionSubsets() {
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
		RuleSet union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 5);
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
		union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 5);
		assertEquals(ruleSet.size(), 5);
		assertEquals(ruleSetParm.size(), 3);
	}
	
	@Test
	public void testUnionOverlappingSets() {
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
		RuleSet union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 9);
		assertEquals(ruleSet.size(), 6);
		assertEquals(ruleSetParm.size(), 5);
	}
	
	@Test
	public void testUnionDisjoint() {
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
		
		RuleSet union = ruleSet.union(ruleSetParm);
		assertEquals(union.size(), 6);
		assertEquals(ruleSet.size(), 3);
		assertEquals(ruleSetParm.size(), 3);
	}

}
