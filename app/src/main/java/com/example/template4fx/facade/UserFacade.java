package com.example.template4fx.facade;

import com.example.template4fx.SVG;
import com.example.template4fx.fx.Displayable;
import com.example.template4fx.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class UserFacade
    implements FXFacade<User>, Displayable
{
    private final User user;

    public UserFacade( User user )
    {
        this.user = user;
        refresh();
    }

    @Override
    public User get()
    {
        return user;
    }

    @Override
    public void refresh()
    {
        if ( user != null )
        {
            name.set( user.getName() );
            username.set( user.getUsername() );
            email.set( user.getEmail() );
            phone.set( user.getPhone() );
            website.set( user.getWebsite() );
        }
    }

    private final StringProperty name = new SimpleStringProperty();

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name.set( name );
    }

    private final StringProperty username = new SimpleStringProperty();

    public String getUsername()
    {
        return username.get();
    }

    public StringProperty usernameProperty()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username.set( username );
    }

    private final StringProperty email = new SimpleStringProperty();

    public String getEmail()
    {
        return email.get();
    }

    public StringProperty emailProperty()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email.set( email );
    }

    private final StringProperty phone = new SimpleStringProperty();

    public String getPhone()
    {
        return phone.get();
    }

    public StringProperty phoneProperty()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone.set( phone );
    }

    private final StringProperty website = new SimpleStringProperty();

    public String getWebsite()
    {
        return website.get();
    }

    public StringProperty websiteProperty()
    {
        return website;
    }

    public void setWebsite( String website )
    {
        this.website.set( website );
    }

    @Override
    public String getTitle()
    {
        return name.get();
    }

    @Override
    public String getDescription()
    {
        return null;
    }

    @Override
    public String getContent()
    {
        return SVG.INSTANCE.get( "person" );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        UserFacade that = (UserFacade) o;
        return Objects.equals( user, that.user );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( user );
    }
}
