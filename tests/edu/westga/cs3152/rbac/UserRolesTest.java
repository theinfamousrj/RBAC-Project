package edu.westga.cs3152.rbac;

import static org.junit.Assert.*;

import java.io.File;
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
	
	//Testing add()
	
	@Test
	public void shouldNotContainAnyUserIdWhenGivenNullRoleList() {
		setup();
		roles.addWithRoleList(userid, roleList);
		assertFalse(roles.contains(userid));
	}
	
	@Test
	public void shouldContainCorrectUserIdAndRoleWhenGivenUserIdAndRoleAsAString() {
		setup();
		String nurseStr = "nurse";
		addARoleToList(nurseStr);
		roles.addWithRoleString(userid, nurseStr);
		assertTrue(roles.contains(userid) && roles.getValues(userid).equals(roleList));
	}
	
	@Test
	public void shouldContainCorrectUserIdAndRoleWhenGivenUserIdAndRoleAsAnArrayList() {
		setup();
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		assertTrue(roles.contains(userid) && roles.getValues(userid).equals(roleList));
	}
	
	@Test
	public void shouldContainOnlyOneUserIdWhenAddMultipleEqualUserIds() {
		setup();
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		roles.addWithRoleList(userid, roleList);
		roles.addWithRoleList(userid, roleList);
		assertTrue(roles.contains(userid) && roles.getSize() == 1);
	}
	
	//Testing getValues()
	
	@Test
	public void shouldHaveMultipleRolesForUserIdWhenAddedWithMultipleRoles() {
		setup();
		addARoleToList("nurse");
		addARoleToList("doctor");
		addARoleToList("visitor");
		roles.addWithRoleList(userid, roleList);
		assertTrue(roles.contains(userid) && roles.getValues(userid).equals(roleList));
	}
	
	//Testing contains()
	
	@Test
	public void shouldNotContainAnyUserIdWhenGivenNullRoles() {
		setup();
		roles.addWithRoleString(userid, null);
		assertFalse(roles.contains(userid));
	}
	
	@Test
	public void shouldContainThreeUserIdsWhenAddThreeUserIds() {
		setup();
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		roles.addWithRoleList(userid2, roleList);
		roles.addWithRoleList(userid3, roleList);
		assertTrue(roles.contains(userid) && roles.contains(userid2) && roles.contains(userid3) && roles.getSize() == 3);
	}
	
	//Testing remove()
	
	@Test
	public void shouldRemoveCorrectUserIdAndRoleWhenGivenUserIdToRemove() {
		setup();
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
		setup();
		addARoleToList("nurse");
		roles.addWithRoleList(userid, roleList);
		roles.remove(userid2);
		assertTrue(roles.contains(userid) && roles.getSize() == 1);
	}
	
	//Testing file import
	@Test
	public void shouldHaveThreeUserIdsEachWithCorrectUserRoles() {
		File theFile = new File("./UserRoles.txt");
		roles = new UserRoles(theFile);
		userid = new UserId("csm65490");
		userid2 = new UserId("ctm21392");
		userid3 = new UserId("del24690");
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("nurse");
		list.add("administrator");
		
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("nurse");
		list2.add("visitor");
		
		ArrayList<String> list3 = new ArrayList<String>();
		list3.add("doctor");
		list3.add("visitor");
		
		boolean containsAllUserIds = roles.contains(userid) && roles.contains(userid2) && roles.contains(userid3);
		boolean containsAllUserRoles = roles.getValues(userid).get(0).equals(list.get(0)) && roles.getValues(userid).get(1).equals(list.get(1))
				&& roles.getValues(userid2).get(0).equals(list2.get(0)) && roles.getValues(userid2).get(1).equals(list2.get(1))
				&& roles.getValues(userid3).get(0).equals(list3.get(0)) && roles.getValues(userid3).get(1).equals(list3.get(1));
		
		assertTrue(containsAllUserIds && containsAllUserRoles && roles.getSize() == 3);
	}
	
	//Helper methods
	
	public void setup() {
		roles = new UserRoles();
		userid = new UserId("csm65490");
		userid2 = new UserId("ctm21392");
		userid3 = new UserId("del24690");
		roleList = new ArrayList<String>();
	}
	
	public void addARoleToList(String roleStr) {
		roleList.add(roleStr);
	}
	
}
