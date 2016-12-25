package se.simplistics.template4fx.model;

import javafx.beans.property.SimpleStringProperty;

public class Person
{
    public final SimpleStringProperty firstName;

    public final SimpleStringProperty lastName;

    public final SimpleStringProperty email;

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

    public SimpleStringProperty firstNameProperty()
    {
        return firstName;
    }

    public SimpleStringProperty lastNameProperty()
    {
        return lastName;
    }

    public SimpleStringProperty emailProperty()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return firstName.get();
    }
}
