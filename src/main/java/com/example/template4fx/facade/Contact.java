package com.example.template4fx.facade;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact
{
    private final StringProperty firstName = new SimpleStringProperty();

    private final StringProperty lastName = new SimpleStringProperty();

    private final StringProperty email = new SimpleStringProperty();

    public Contact()
    {
    }

    public Contact( String firstName, String lastName, String email )
    {
        this.firstName.set( firstName );
        this.lastName.set( lastName );
        this.email.set( email );
    }

    public String getFirstName()
    {
        return firstName.get();
    }

    public StringProperty firstNameProperty()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName.set( firstName );
    }

    public String getLastName()
    {
        return lastName.get();
    }

    public StringProperty lastNameProperty()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName.set( lastName );
    }

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
}
