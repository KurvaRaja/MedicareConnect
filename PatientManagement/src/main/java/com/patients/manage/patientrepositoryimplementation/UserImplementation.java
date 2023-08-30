package com.patients.manage.patientrepositoryimplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patients.manage.entites.User;
import com.patients.manage.patientrepositories.UserRepository;
import com.patients.manage.patientservice.UserService;

@Service
public class UserImplementation implements UserService 
{
	@Autowired
	private UserRepository userrepo;
	

	@Override
	public void saveUser(User user) 
	{
	 	userrepo.save(user);
		
	}


	


	@Override
	public User verifyEmail(String email)
	{
		User listOfUsers = userrepo.findByEmail(email);
		
	return listOfUsers;
	}

}