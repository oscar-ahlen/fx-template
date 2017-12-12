package com.example.template4fx.service.impl;

import com.example.template4fx.error.HttpException;
import com.example.template4fx.model.User;
import com.example.template4fx.service.HttpService;
import com.example.template4fx.service.UserService;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

public class UserServiceImpl
    implements UserService
{
    private final Type usersType = new TypeToken<List<User>>()
    {
    }.getType();

    private final HttpService service;

    @Inject
    public UserServiceImpl( HttpService service )
    {
        this.service = service;
    }

    @Override
    public Collection<User> getUsers()
        throws HttpException, IOException
    {
        try
        {
            return getUsers( new URIBuilder().setScheme( "https" )
                                             .setHost( "jsonplaceholder.typicode.com" )
                                             .setPath( "users" )
                                             .build() );
        }
        catch ( URISyntaxException exc )
        {
            throw new RuntimeException( exc );
        }
    }

    private Collection<User> getUsers( URI uri )
        throws HttpException, IOException
    {
        HttpGet httpGet = new HttpGet( uri );
        httpGet.addHeader( "Accept", "application/json" );

        return service.execute( httpGet, usersType );
    }
}
