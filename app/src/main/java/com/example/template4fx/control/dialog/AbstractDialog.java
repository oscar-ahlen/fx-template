package com.example.template4fx.control.dialog;

import com.example.template4fx.control.FXControl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyEvent;

public abstract class AbstractDialog
    extends FXControl
{
    public AbstractDialog( String header, String svg )
    {
        setHeader( header );
        setSvg( svg );

        getStyleClass().add( "dialog" );
        visibleProperty().bindBidirectional( managedProperty() );
    }

    public void handleKeyEvent( KeyEvent event )
    {
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

    protected final StringProperty svg = new SimpleStringProperty();

    public String getSvg()
    {
        return svg.get();
    }

    public StringProperty svgProperty()
    {
        return svg;
    }

    public void setSvg( String svg )
    {
        this.svg.set( svg );
    }
}
