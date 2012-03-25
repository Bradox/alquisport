package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import es.tresw.db.entities.Role;
import es.tresw.db.entities.User;
import es.tresw.db.entities.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestUserRoleDao 
{
	@Autowired
	private I_UserDao  userDao;
	@Autowired
	private I_UserRoleDao userRoleDao;
	@Autowired
	private I_RoleDao  roleDao;

	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Role role = roleDao.readAll().get(0);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		UserRole userRole = new UserRole();
		List<User> usersList = userDao.readAll();
		Set<User> users = new HashSet<User>(usersList);
		userRole.setUser(users);
		userRole.setRoles(roles);
		userRoleDao.create(userRole);
		assertNotNull(userRoleDao.read(userRole.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		UserRole userRole = userRoleDao.readAll().get(0);
		int numRoles = userRole.getRoles().size();
		Role role = new Role();
		role.setName("Nuevo Role");
		roleDao.create(role);
		userRole.getRoles().add(role);
		userRoleDao.create(userRole);
		assertEquals(numRoles+1, userRoleDao.read(userRole.getId()).getRoles().size());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(userRoleDao.readAll().size()>0);
	}
	
	
	@Test
	public void testReadOne()
	{
		UserRole userRole = userRoleDao.readAll().get(0);
		assertNotNull(userRoleDao.read(userRole.getId()));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		UserRole userRole = userRoleDao.readAll().get(0);
		userRoleDao.delete(userRole);
		assertNull(userRoleDao.read(userRole.getId()));		
	}

	public void setUserDao(I_UserDao userDao) 
	{
		this.userDao = userDao;
	}

	public void setUserRoleDao(I_UserRoleDao userRoleDao) 
	{
		this.userRoleDao = userRoleDao;
	}

	public void setRoleDao(I_RoleDao roleDao) 
	{
		this.roleDao = roleDao;
	}

}



