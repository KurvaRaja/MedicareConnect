package com.patients.manage.patientservice;

import com.patients.manage.entites.User;

public interface UserService 
{
    public void saveUser(User user);
    public User verifyEmail(String email);
}
