package com.example.template4fx.service;

import com.example.template4fx.model.User;
import com.example.template4fx.error.HttpException;

import java.io.IOException;
import java.util.Collection;

public interface UserService
{
    Collection<User> getUsers()
        throws HttpException, IOException;
}
