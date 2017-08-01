package com.example.template4fx.facade;

import com.example.template4fx.SVG;
import com.example.template4fx.model.Contact;
import com.example.template4fx.view.Displayable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContactFacade
    implements FXFacade<Contact>, Displayable<String>
{
    private final StringProperty firstName = new SimpleStringProperty();

    private final StringProperty lastName = new SimpleStringProperty();

    private final StringProperty email = new SimpleStringProperty();

    private final Contact contact;

    public ContactFacade( Contact contact )
    {
        this.contact = contact;
        refresh();
    }

    @Override
    public Contact get()
    {
        return contact;
    }

    @Override
    public void refresh()
    {
        if ( contact != null )
        {
            firstName.set( contact.getFirstName() );
            lastName.set( contact.getLastName() );
            email.set( contact.getEmail() );
        }
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

    @Override
    public String getText()
    {
        return firstName.get();
    }

    @Override
    public String getContent()
    {
        return SVG.get( "file_image" );
    }
}
