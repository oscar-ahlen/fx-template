package se.simplistics.template4fx.demo.model;

import javafx.beans.property.SimpleStringProperty;

public class Person
{
    private final SimpleStringProperty firstName = new SimpleStringProperty();

    private final SimpleStringProperty lastName = new SimpleStringProperty();

    private final SimpleStringProperty email = new SimpleStringProperty();

    public Person( String firstName, String lastName, String email )
    {
        this.firstName.set( firstName );
        this.lastName.set( lastName );
        this.email.set( email );
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
}
