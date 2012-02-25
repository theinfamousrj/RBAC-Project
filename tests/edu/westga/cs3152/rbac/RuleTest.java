package edu.westga.cs3152.rbac;

import static org.junit.Assert.*;

import org.junit.Test;

public class RuleTest {

	@Test
	public void shouldNotMatchNull() {
		Rule aRule = new Rule("physician", "drug", Rule.READ);
		assertFalse(aRule.matches(null));
	}
	
	@Test
	public void shouldMatchValues() {
		Rule aRule = new Rule("nures", "drug", Rule.READ);
		Rule matchRule = new Rule("nures", "drug", Rule.READ);
		
		assertTrue(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldNotMatchRole() {
		Rule aRule = new Rule("physician", "drug", Rule.READ);
		Rule matchRule = new Rule("nurse", "drug", Rule.READ);
		
		assertFalse(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldNotMatchObject() {
		Rule aRule = new Rule("nurse", "address", Rule.READ);
		Rule matchRule = new Rule("nurse", "drug", Rule.READ);
		
		assertFalse(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldNotMatchAccess() {
		Rule aRule = new Rule("nurse", "drug", Rule.WRITE);
		Rule matchRule = new Rule("nurse", "drug", Rule.READ);
		
		assertFalse(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldMatchNullRole() {
		Rule aRule = new Rule("nurse", "drug", Rule.READ);
		Rule matchRule = new Rule(null, "drug", Rule.READ);
		
		assertTrue(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldMatchNullObject() {
		Rule aRule = new Rule("nurse", "drug", Rule.READ);
		Rule matchRule = new Rule("nurse", null, Rule.READ);
		
		assertTrue(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldMatchNoneAccess() {
		Rule aRule = new Rule("nurse", "drug", Rule.READ);
		Rule matchRule = new Rule("nurse", "drug", Rule.NONE);
		
		assertTrue(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldMatchAlNull() {
		Rule aRule = new Rule("nurse", "drug", Rule.READ);
		Rule matchRule = new Rule(null, null, Rule.NONE);
		
		assertTrue(aRule.matches(matchRule));
	}	
	
	@Test
	public void shouldNotMatchWithNullRole() {
		Rule aRule = new Rule("nurse", "address", Rule.READ);
		Rule matchRule = new Rule(null, "drug", Rule.READ);
		
		assertFalse(aRule.matches(matchRule));
	}	
	
	@Test
	public void shouldNotMatchWithNullObject() {
		Rule aRule = new Rule("physician", "drug", Rule.READ);
		Rule matchRule = new Rule("nurse", null, Rule.READ);
		
		assertFalse(aRule.matches(matchRule));
	}	
	
	@Test
	public void shouldNotMatchWithNoneAccess() {
		Rule aRule = new Rule("nurse", "address", Rule.READ);
		Rule matchRule = new Rule("nurse", "drug", Rule.NONE);
		
		assertFalse(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldNotMatchWithNullRoleInFirstRule() {
		Rule aRule = new Rule(null, "drug", Rule.READ);
		Rule matchRule = new Rule("nurse", "drug", Rule.READ);
		
		assertFalse(aRule.matches(matchRule));
	}	
	
	@Test
	public void shouldNotMatchWithNullObjectInFirstRule() {
		Rule aRule = new Rule("nurse", null, Rule.READ);
		Rule matchRule = new Rule("nurse", "drug", Rule.READ);
		
		assertFalse(aRule.matches(matchRule));
	}	
	
	@Test
	public void shouldNotMatchWithNoneAccessInFirstRule() {
		Rule aRule = new Rule("nurse", "drug", Rule.NONE);
		Rule matchRule = new Rule("nurse", "drug", Rule.READ);
		
		assertFalse(aRule.matches(matchRule));
	}	

	@Test
	public void shouldMatchWithNullRoleInFirstRule() {
		Rule aRule = new Rule(null, "drug", Rule.READ);
		Rule matchRule = new Rule(null, "drug", Rule.READ);
		
		assertTrue(aRule.matches(matchRule));
	}	

	@Test
	public void shouldMatchWithNullObjectInFirstRule() {
		Rule aRule = new Rule("nurse", null, Rule.READ);
		Rule matchRule = new Rule("nurse", null, Rule.READ);
		
		assertTrue(aRule.matches(matchRule));
	}
	
	@Test
	public void shouldMatchWithNoneAccessInFirstRule() {
		Rule aRule = new Rule("nurse", "drug", Rule.NONE);
		Rule matchRule = new Rule("nurse", "drug", Rule.NONE);
		
		assertTrue(aRule.matches(matchRule));
	}
}
