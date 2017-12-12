package com.example.template4fx.control.dialog;

import com.example.template4fx.control.FXControl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class AbstractDialog
    extends FXControl
{
    public AbstractDialog( String header )
    {
        getStyleClass().add( "dialog" );

        setHeader( header );
        visibleProperty().bindBidirectional( managedProperty() );
    }

    protected final StringProperty header = new SimpleStringProperty();

    public String getHeader()
    {
        return header.get();
    }

    public StringProperty headerProperty()
    {
        return header;
    }

    public void setHeader( String header )
    {
        this.header.set( header );
    }
}
