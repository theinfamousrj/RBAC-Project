package edu.westga.cs3152.rbac;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Raymond Hill
 *
 */
public class UserRolesTest {

	@Test
	public void shouldNotMatchNull() {
		Rule aRule = new Rule("physician", "drug", Rule.READ);
		assertFalse(aRule.matches(null));
	}
	
}
