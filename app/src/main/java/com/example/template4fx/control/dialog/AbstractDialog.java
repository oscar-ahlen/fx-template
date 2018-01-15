package com.example.template4fx.control.dialog;

import com.example.template4fx.control.FXControl;
import com.example.template4fx.fx.Popup;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

public abstract class AbstractDialog
    extends FXControl
    implements Popup
{
    AbstractDialog( String header, String svg )
    {
        setHeader( header );
        setSvg( svg );

        getStyleClass().add( "dialog" );
    }

    @Override
    public Node getNode()
    {
        return this;
    }

    @Override
    public void handleKeyEvent( KeyEvent event )
    {

    }

    private final BooleanProperty closed = new SimpleBooleanProperty();

    public boolean isClosed()
    {
        return closed.get();
    }

    @Override
    public BooleanProperty closedProperty()
    {
        return closed;
    }

    public void setClosed( boolean closed )
    {
        this.closed.set( closed );
    }

    private final StringProperty header = new SimpleStringProperty();

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

    private final StringProperty svg = new SimpleStringProperty();

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
