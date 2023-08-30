package com.department.manage.userservice;

import com.department.manage.Entity.User;

public interface UserService 
{
   public void saveUser(User user);
   public User verifyEmail(String email);
}
