package com.department.manage.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.manage.Entity.User;
import com.department.manage.repository.UserRepository;
import com.department.manage.userservice.UserService;

@Service
public class UserServiceImplemenation implements UserService
{
	@Autowired 
	private  UserRepository userrepository;

	@Override
	public void saveUser(User user)
	{
		
		userrepository.save(user);
	}

	@Override
	public User verifyEmail(String email) 
	{
		User findByEmail = userrepository.findByEmail(email);
		return findByEmail;
 	}
    
}
