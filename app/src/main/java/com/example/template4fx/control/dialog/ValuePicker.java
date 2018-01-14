package com.example.template4fx.control.dialog;

import com.example.template4fx.Keys;
import com.example.template4fx.fx.DialogEvent;
import com.example.template4fx.skin.ValuePickerSkin;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.input.KeyEvent;

public class ValuePicker<T>
    extends AbstractDialog
{
    private final ObjectProperty<T> selected = new SimpleObjectProperty<>();

    private final FilteredList<T> items;

    private final Node source;

    public ValuePicker( Node source, ObservableList<T> items )
    {
        super( "", null );
        getStyleClass().add( "value-picker" );

        this.source = source;
        this.items = new FilteredList<>( items, p -> true );
        initialize();
    }

    @Override
    public void handleKeyEvent( KeyEvent event )
    {
        super.handleKeyEvent( event );

        if ( Keys.ESCAPE.match( event ) )
        {
            event.consume();
            cancel();
        }
        else if ( Keys.ENTER.match( event ) && selected.get() != null )
        {
            event.consume();
            success();
        }
    }

    private void initialize()
    {
        filter.addListener(
            ( observable, oldValue, newValue ) ->
                items.setPredicate( item -> newValue == null || newValue.isEmpty() || matches( item, newValue ) ) );

        selected.set( items.stream().findFirst().orElse( null ) );
    }

    private boolean matches( T item, String filter )
    {
        return item.toString().toLowerCase().startsWith( filter.toLowerCase() );
    }

    public void success()
    {
        fireEvent( DialogEvent.successEvent() );
        close();
    }

    public void cancel()
    {
        fireEvent( DialogEvent.cancelEvent() );
        close();
    }

    private void close()
    {
        setClosed( true );
        Platform.runLater( source::requestFocus );
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new ValuePickerSkin<>( this );
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return getUserAgentStylesheet( ValuePicker.class, "ValuePicker.css" );
    }

    private final StringProperty filter = new SimpleStringProperty();

    public String getFilter()
    {
        return filter.get();
    }

    public StringProperty filterProperty()
    {
        return filter;
    }

    public void setFilter( String filter )
    {
        this.filter.set( filter );
    }

    private final StringProperty promptText = new SimpleStringProperty();

    public String getPromptText()
    {
        return promptText.get();
    }

    public StringProperty promptTextProperty()
    {
        return promptText;
    }

    public void setPromptText( String promptText )
    {
        this.promptText.set( promptText );
    }

    public T getSelected()
    {
        return selected.get();
    }

    public ObjectProperty<T> selectedProperty()
    {
        return selected;
    }

    public void setSelected( T selected )
    {
        this.selected.set( selected );
    }

    public ObservableList<T> getItems()
    {
        return items;
    }
}
