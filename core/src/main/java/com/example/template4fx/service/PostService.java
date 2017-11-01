package com.example.template4fx.service;

import com.example.template4fx.error.HttpException;
import com.example.template4fx.model.Post;

import java.io.IOException;
import java.util.Collection;

public interface PostService
{
    Post getPost( long id )
        throws HttpException, IOException;

    Collection<Post> getPosts()
        throws HttpException, IOException;

    Collection<Post> getPosts( long userId )
        throws HttpException, IOException;
}
