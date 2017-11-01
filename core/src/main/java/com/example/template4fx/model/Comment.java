package com.example.template4fx.model;

public class Comment
{
    private long id;

    private long postId;

    private String name;

    private String email;

    private String body;

    public Comment()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public long getPostId()
    {
        return postId;
    }

    public void setPostId( long postId )
    {
        this.postId = postId;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody( String body )
    {
        this.body = body;
    }
}
