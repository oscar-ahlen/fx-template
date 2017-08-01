package com.example.template4fx.component;

import com.example.template4fx.facade.ContactFacade;
import com.example.template4fx.model.Contact;
import com.example.template4fx.view.SVGTableCell;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContactView
    extends MainView
{
    @FXML
    private TableView<ContactFacade> contactView;

    @FXML
    private TableColumn<ContactFacade, ContactFacade> contactNameColumn;

    private final ObservableList<ContactFacade> contacts = FXCollections.observableArrayList();

    public void initialize()
    {
        contactView.setItems( contacts );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );
        contacts.add( new ContactFacade( new Contact( "John", "Doe", "john.doe@example.com" ) ) );

        contactNameColumn.setCellValueFactory( v -> new SimpleObjectProperty<>( v.getValue() ) );
        contactNameColumn.setCellFactory( callback -> new SVGTableCell<>() );
    }
}
