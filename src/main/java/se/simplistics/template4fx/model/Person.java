package se.simplistics.template4fx.model;

import javafx.beans.property.SimpleStringProperty;

public class Person
{
    public final SimpleStringProperty firstName = new SimpleStringProperty();

    public final SimpleStringProperty lastName = new SimpleStringProperty();

    public final SimpleStringProperty email = new SimpleStringProperty();

    public Person()
    {

    }

    public Person( String firstName, String lastName, String email )
    {
        this.firstName.set( firstName );
        this.lastName.set( lastName );
        this.email.set( email );
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
