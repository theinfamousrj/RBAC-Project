package edu.westga.cs3152.rbac;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PDPTest {
	RuleSet ruleset;
	ArrayList<Rule> rules;
	PDP myPDP;
	
	public void initRules() {
		ruleset = new RuleSet();
		rules = new ArrayList<Rule>();
		rules.add(new Rule("staff", "diagnosis", Rule.DENY));
		rules.add(new Rule("physician", "drug", Rule.WRITE));
		rules.add(new Rule("nurse", "drug", Rule.WRITE));
		rules.add(new Rule("nurse", "drug", Rule.DENY));
		rules.add(new Rule("nurse", "drug", Rule.READ));
		rules.add(new Rule("visitor", "drug", Rule.DENY));
		
		rules.add(new Rule("staff", "address", Rule.READ));
		rules.add(new Rule("nurse", "address", Rule.READ));
		rules.add(new Rule("nurse", "address", Rule.DENY));
		rules.add(new Rule("administrator", "address", Rule.WRITE));
		
		rules.add(new Rule("physician", "diagnosis", Rule.WRITE));
		rules.add(new Rule("physician", "diagnosis", Rule.READ));
		rules.add(new Rule("administrator", "diagnosis", Rule.WRITE));
		rules.add(new Rule("administrator", "diagnosis", Rule.READ));
		rules.add(new Rule("administrator", "diagnosis", Rule.DENY));
		rules.add(new Rule("nurse", "diagnosis", Rule.WRITE));
		
		rules.add(new Rule("manager", "salary", Rule.WRITE));
		rules.add(new Rule("manager", "salary", Rule.DENY));
		ruleset.init(rules);
	}

	/**
	 *  PRERMIT OVERRIDES    WRITE WITHOUT READ
	 */
	@Test
	public void testRequestGranted_permitOverrides_WriteWithoutRead_() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);
		// no read - no write - no deny
		assertFalse((myPDP.requestGranted("staff", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("staff", "drug", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteWithoutRead_D() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);
		// no read - no write - deny
		assertFalse((myPDP.requestGranted("visitor", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("visitor", "drug", Request.READ)));	
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteWithoutRead_W() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);
		// no read - write - no deny
		assertTrue((myPDP.requestGranted("physician", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("physician", "drug", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteWithoutRead_WD() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);
		// no read - write  - deny
		assertTrue((myPDP.requestGranted("manager", "salary", Request.WRITE)));
		assertFalse((myPDP.requestGranted("manager", "salary", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteWithoutRead_R() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);	
		// read - no write - no deny
		assertFalse((myPDP.requestGranted("staff", "address", Request.WRITE)));
		assertTrue((myPDP.requestGranted("staff", "address", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteWithoutRead_RD() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);	
		// read - no write - deny
		assertFalse((myPDP.requestGranted("nurse", "address", Request.WRITE)));
		assertTrue((myPDP.requestGranted("nurse", "address", Request.READ)));
	}
		
	@Test
	public void testRequestGranted_permitOverrides_WriteWithoutRead_RW() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);	
		// read - write - no deny
		assertTrue((myPDP.requestGranted("physician", "diagnosis", Request.WRITE)));
		assertTrue((myPDP.requestGranted("physician", "diagnosis", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteWithoutRead_RWD() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);	
		// read - write  - deny
		assertTrue((myPDP.requestGranted("nurse", "drug", Request.WRITE)));
		assertTrue((myPDP.requestGranted("nurse", "drug", Request.READ)));
    }
	
	
	/**
	 *  PRERMIT OVERRIDES    WRITE IMPLIES READ
	 */
	@Test
	public void testRequestGranted_permitOverrides_WriteImpliesRead_() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);
		// no read - no write - no deny
		assertFalse((myPDP.requestGranted("staff", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("staff", "drug", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteImpliesRead_D() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);
		// no read - no write - deny
		assertFalse((myPDP.requestGranted("visitor", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("visitor", "drug", Request.READ)));	
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteImpliesRead_W() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);
		// no read - write - no deny
		assertTrue((myPDP.requestGranted("physician", "drug", Request.WRITE)));
		assertTrue((myPDP.requestGranted("physician", "drug", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteImpliesRead_WD() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);
		// no read - write  - deny
		assertTrue((myPDP.requestGranted("manager", "salary", Request.WRITE)));
		assertTrue((myPDP.requestGranted("manager", "salary", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteImpliesRead_R() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);	
		// read - no write - no deny
		assertFalse((myPDP.requestGranted("staff", "address", Request.WRITE)));
		assertTrue((myPDP.requestGranted("staff", "address", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteImpliesRead_RD() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);	
		// read - no write - deny
		assertFalse((myPDP.requestGranted("nurse", "address", Request.WRITE)));
		assertTrue((myPDP.requestGranted("nurse", "address", Request.READ)));
	}
		
	@Test
	public void testRequestGranted_permitOverrides_WriteImpliesRead_RW() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);	
		// read - write - no deny
		assertTrue((myPDP.requestGranted("physician", "diagnosis", Request.WRITE)));
		assertTrue((myPDP.requestGranted("physician", "diagnosis", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_permitOverrides_WriteImpliesRead_RWD() {
		initRules();
		myPDP = new PDP(PDP.PERMIT_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);	
		// read - write  - deny
		assertTrue((myPDP.requestGranted("nurse", "drug", Request.WRITE)));
		assertTrue((myPDP.requestGranted("nurse", "drug", Request.READ)));
    }
	
	
	/**
	 *  DENY OVERRIDES    WRITE WITHOUT READ
	 */
	@Test
	public void testRequestGranted_denyOverrides_WriteWithoutRead_() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);
		// no read - no write - no deny
		assertFalse((myPDP.requestGranted("staff", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("staff", "drug", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteWithoutRead_D() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);
		// no read - no write - deny
		assertFalse((myPDP.requestGranted("visitor", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("visitor", "drug", Request.READ)));	
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteWithoutRead_W() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);
		// no read - write - no deny
		assertTrue((myPDP.requestGranted("physician", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("physician", "drug", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteWithoutRead_WD() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);
		// no read - write  - deny
		assertFalse((myPDP.requestGranted("manager", "salary", Request.WRITE)));
		assertFalse((myPDP.requestGranted("manager", "salary", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteWithoutRead_R() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);	
		// read - no write - no deny
		assertFalse((myPDP.requestGranted("staff", "address", Request.WRITE)));
		assertTrue((myPDP.requestGranted("staff", "address", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteWithoutRead_RD() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);	
		// read - no write - deny
		assertFalse((myPDP.requestGranted("nurse", "address", Request.WRITE)));
		assertFalse((myPDP.requestGranted("nurse", "address", Request.READ)));
	}
		
	@Test
	public void testRequestGranted_denyOverrides_WriteWithoutRead_RW() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);	
		// read - write - no deny
		assertTrue((myPDP.requestGranted("physician", "diagnosis", Request.WRITE)));
		assertTrue((myPDP.requestGranted("physician", "diagnosis", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteWithoutRead_RWD() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_WITHOUT_READ, ruleset);	
		// read - write  - deny
		assertFalse((myPDP.requestGranted("nurse", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("nurse", "drug", Request.READ)));
    }
	
	
	/**
	 *  DENY OVERRIDES    WRITE IMPLIES READ
	 */
	@Test
	public void testRequestGranted_denyOverrides_WriteImpliesRead_() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);
		// no read - no write - no deny
		assertFalse((myPDP.requestGranted("staff", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("staff", "drug", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteImpliesRead_D() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);
		// no read - no write - deny
		assertFalse((myPDP.requestGranted("visitor", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("visitor", "drug", Request.READ)));	
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteImpliesRead_W() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);
		// no read - write - no deny
		assertTrue((myPDP.requestGranted("physician", "drug", Request.WRITE)));
		assertTrue((myPDP.requestGranted("physician", "drug", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteImpliesRead_WD() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);
		// no read - write  - deny
		assertFalse((myPDP.requestGranted("manager", "salary", Request.WRITE)));
		assertFalse((myPDP.requestGranted("manager", "salary", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteImpliesRead_R() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);	
		// read - no write - no deny
		assertFalse((myPDP.requestGranted("staff", "address", Request.WRITE)));
		assertTrue((myPDP.requestGranted("staff", "address", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteImpliesRead_RD() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);	
		// read - no write - deny
		assertFalse((myPDP.requestGranted("nurse", "address", Request.WRITE)));
		assertFalse((myPDP.requestGranted("nurse", "address", Request.READ)));
	}
		
	@Test
	public void testRequestGranted_denyOverrides_WriteImpliesRead_RW() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);	
		// read - write - no deny
		assertTrue((myPDP.requestGranted("physician", "diagnosis", Request.WRITE)));
		assertTrue((myPDP.requestGranted("physician", "diagnosis", Request.READ)));
	}
	
	@Test
	public void testRequestGranted_denyOverrides_WriteImpliesRead_RWD() {
		initRules();
		myPDP = new PDP(PDP.DENY_OVERRIDES, PDP.WRITE_IMPLIES_READ, ruleset);	
		// read - write  - deny
		assertFalse((myPDP.requestGranted("nurse", "drug", Request.WRITE)));
		assertFalse((myPDP.requestGranted("nurse", "drug", Request.READ)));
    }
}
