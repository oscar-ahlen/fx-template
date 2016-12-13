package se.simplistics.template4fx.model;

import javafx.beans.property.SimpleStringProperty;

public class Person
{
    private final SimpleStringProperty firstName;

    private final SimpleStringProperty lastName;

    private final SimpleStringProperty email;

    public Person()
    {
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        email = new SimpleStringProperty();
    }

    public Person( String firstName, String lastName, String email )
    {
        this.firstName = new SimpleStringProperty( firstName );
        this.lastName = new SimpleStringProperty( lastName );
        this.email = new SimpleStringProperty( email );
    }

    public String getFirstName()
    {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty()
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

    public SimpleStringProperty lastNameProperty()
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

    public SimpleStringProperty emailProperty()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email.set( email );
    }

    @Override
    public String toString()
    {
        return firstName.get();
    }
}
