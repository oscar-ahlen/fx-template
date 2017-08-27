package com.example.template4fx.control.dialog;

import com.example.template4fx.control.FXControl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class AbstractDialog
    extends FXControl
{
    protected final StringProperty header = new SimpleStringProperty();

    public AbstractDialog( String header )
    {
        setHeader( header );
        visibleProperty().bindBidirectional( managedProperty() );
    }

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
