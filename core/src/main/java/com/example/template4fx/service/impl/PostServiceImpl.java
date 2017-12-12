package com.example.template4fx.service.impl;

import com.example.template4fx.error.HttpException;
import com.example.template4fx.model.Post;
import com.example.template4fx.service.HttpService;
import com.example.template4fx.service.PostService;
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

public class PostServiceImpl
    implements PostService
{
    private final Type postType = new TypeToken<Post>()
    {
    }.getType();

    private final Type postsType = new TypeToken<List<Post>>()
    {
    }.getType();

    private final HttpService service;

    private final String host = "jsonplaceholder.typicode.com";

    @Inject
    public PostServiceImpl( HttpService service )
    {
        this.service = service;
    }

    @Override
    public Post getPost( long id )
        throws HttpException, IOException
    {
        try
        {
            return getPost( new URIBuilder().setScheme( "https" ).setHost( host ).setPath( "posts/" + id ).build() );
        }
        catch ( URISyntaxException exc )
        {
            throw new RuntimeException( exc );
        }
    }

    @Override
    public Collection<Post> getPosts()
        throws HttpException, IOException
    {
        try
        {
            return getPosts( new URIBuilder().setScheme( "https" ).setHost( host ).setPath( "posts" ).build() );
        }
        catch ( URISyntaxException exc )
        {
            throw new RuntimeException( exc );
        }
    }

    @Override
    public Collection<Post> getPosts( long userId )
        throws HttpException, IOException
    {
        try
        {
            URI uri = new URIBuilder().setScheme( "https" )
                                      .setHost( host )
                                      .setPath( "posts" )
                                      .addParameter( "userId", "" + userId )
                                      .build();

            return getPosts( uri );
        }
        catch ( URISyntaxException exc )
        {
            throw new RuntimeException( exc );
        }
    }

    private Post getPost( URI uri )
        throws HttpException, IOException
    {
        HttpGet httpGet = new HttpGet( uri );
        httpGet.addHeader( "Accept", "application/json" );

        return service.execute( httpGet, postType );
    }

    private Collection<Post> getPosts( URI uri )
        throws HttpException, IOException
    {
        HttpGet httpGet = new HttpGet( uri );
        httpGet.addHeader( "Accept", "application/json" );

        return service.execute( httpGet, postsType );
    }
}
