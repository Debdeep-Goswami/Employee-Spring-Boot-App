package com.employee.main.service;



import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.main.dao.RoleDao;
import com.employee.main.dao.UserDao;
import com.employee.main.entity.Role;
import com.employee.main.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	public User registerNewUser(User user) {
		
		Role role=roleDao.findById("User").get();
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		user.setRole(roles);
		
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		
		return userDao.save(user);
	}
	
	
	// Method to populate the tables with predefined data when application starts 
	public void initRolesAndUser() {
		
		// ******************* Role Creation **********************
		
		//_____________________Creating Admin role__________________
		
		Role adminRole=new Role();
		
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Administrator for the application");
		
		roleDao.save(adminRole);
		
		//___________________ End of Creating Admin Role_________
		
		
		//_______________________Creating User role______________
		
		Role userRole=new Role();
		
		userRole.setRoleName("User");
		userRole.setRoleDescription("Normal user for the application");
		
		roleDao.save(userRole);
		//___________________ End of Creating User Role_________
		
		
		//*********************** End of Role creation *******************
		
		//#####################################################################
		
		// ******************* User Creation **********************
		
		
		//_________________	Creating Admin user___________________
		
		User adminUser=new User();
		adminUser.setUserFirstName("Debdeep");
		adminUser.setUserLastName("Goswami");
		adminUser.setUserName("admin");
		adminUser.setUserPassword(getEncodedPassword("admin"));
		
		Set<Role> adminRoles=new HashSet<>();
		adminRoles.add(adminRole);
		
		adminUser.setRole(adminRoles);
		userDao.save(adminUser);
		
		
		//___________________ End of Creating Admin User_________
		
		
		//_____________________Creating Normal User_______________
		
//		User normalUser=new User();
//		normalUser.setUserFirstName("Baladev");
//		normalUser.setUserLastName("Goswami");
//		normalUser.setUserName("user1");
//		normalUser.setUserPassword(getEncodedPassword("password"));
//		
//		Set<Role> userRoles= new HashSet<>();
//		userRoles.add(userRole);
//		
//		normalUser.setRole(userRoles);
//		userDao.save(normalUser);	
		
		//___________________ End of Creating Normal User_________
		
		
		//*********************** End of User creation *******************
		
	}
	
	public String getEncodedPassword(String password) {
		return passwordencoder.encode(password);
	}

}
