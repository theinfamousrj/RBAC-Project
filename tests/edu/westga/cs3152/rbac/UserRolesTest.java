package edu.westga.cs3152.rbac;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Raymond Hill
 *
 */
public class UserRolesTest {

	ArrayList<String> roleList;
	UserRoles roles;
	UserId userid;
	UserId userid2;
	UserId userid3;
	
	@Before
	public void Setup() {
		roles = new UserRoles();
		userid = new UserId("csm65490");
		userid2 = new UserId("ctm21392");
		userid3 = new UserId("del24690");
		roleList = new ArrayList<String>();
	}
	
	//Testing add()
	
	@Test
	public void shouldNotContainAnyUserIdWhenGivenNullRoleList() {
		roles.addWithRoleList(userid, roleList);
		assertFalse(roles.contains(userid));
	}
	
	@Test
	public void shouldContainCorrectUserIdAndRoleWhenGivenUserIdAndRoleAsAString() {
		String nurseStr = "nurse";
		addARoleToList(nurseStr);
		roles.addWithRoleString(userid, nurseStr);
		assertTrue(roles.contains(userid) && roles.getValues(userid).equals(roleList));
	}
	
	@Test
	public void shouldContainCorrectUserIdAndRoleWhenGivenUserIdAndRoleAsAnArrayList() {
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		assertTrue(roles.contains(userid) && roles.getValues(userid).equals(roleList));
	}
	
	@Test
	public void shouldContainOnlyOneUserIdWhenAddMultipleEqualUserIds() {
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		roles.addWithRoleList(userid, roleList);
		roles.addWithRoleList(userid, roleList);
		assertTrue(roles.contains(userid) && roles.getSize() == 1);
	}
	
	//Testing getValues()
	
	@Test
	public void shouldHaveMultipleRolesForUserIdWhenAddedWithMultipleRoles() {
		addARoleToList("nurse");
		addARoleToList("doctor");
		addARoleToList("visitor");
		roles.addWithRoleList(userid, roleList);
		assertTrue(roles.contains(userid) && roles.getValues(userid).equals(roleList));
	}
	
	//Testing contains()
	
	@Test
	public void shouldNotContainAnyUserIdWhenGivenNullRoles() {
		roles.addWithRoleString(userid, null);
		assertFalse(roles.contains(userid));
	}
	
	@Test
	public void shouldContainThreeUserIdsWhenAddThreeUserIds() {
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		roles.addWithRoleList(userid2, roleList);
		roles.addWithRoleList(userid3, roleList);
		assertTrue(roles.contains(userid) && roles.contains(userid2) && roles.contains(userid3) && roles.getSize() == 3);
	}
	
	//Testing remove()
	
	@Test
	public void shouldRemoveCorrectUserIdAndRoleWhenGivenUserIdToRemove() {
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		roles.addWithRoleList(userid2, roleList);
		roles.addWithRoleList(userid3, roleList);
		boolean didContainUserId = roles.contains(userid);
		roles.remove(userid);
		assertTrue(!roles.contains(userid) && didContainUserId);
	}
	
	@Test
	public void shouldNotRemoveAnyUserIdWhenRemovingUserIdThatIsNotInDictionary() {
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		roles.remove(userid2);
		assertTrue(roles.contains(userid) && roles.getSize() == 1);
	}
	
	//Helper methods
	
	public void addARoleToList(String roleStr) {
		roleList.add(roleStr);
	}
	
}
