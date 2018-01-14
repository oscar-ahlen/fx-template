package com.example.template4fx.control.dialog;

import com.example.template4fx.Keys;
import com.example.template4fx.control.FXControl;
import com.example.template4fx.fx.Popup;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

public abstract class AbstractDialog
    extends FXControl
    implements Popup
{
    protected AbstractDialog( String header, String svg )
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
        if ( ( Keys.TAB.match( event ) || Keys.RIGHT.match( event ) ) && getLast().isFocused() )
        {
            event.consume();
            Platform.runLater( () -> getFirst().requestFocus() );
        }
        else if ( ( Keys.SHIFT_TAB.match( event ) || Keys.LEFT.match( event ) ) && getFirst().isFocused() )
        {
            event.consume();
            Platform.runLater( () -> getLast().requestFocus() );
        }
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

    private final ObjectProperty<Node> first = new SimpleObjectProperty<>();

    public Node getFirst()
    {
        return first.get();
    }

    public ObjectProperty<Node> firstProperty()
    {
        return first;
    }

    public void setFirst( Node first )
    {
        this.first.set( first );
    }

    private final ObjectProperty<Node> last = new SimpleObjectProperty<>();

    public Node getLast()
    {
        return last.get();
    }

    public ObjectProperty<Node> lastProperty()
    {
        return last;
    }

    public void setLast( Node last )
    {
        this.last.set( last );
    }
}
