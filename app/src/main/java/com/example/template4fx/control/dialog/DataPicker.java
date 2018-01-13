package com.example.template4fx.control.dialog;

import com.example.template4fx.fx.DialogEvent;
import com.example.template4fx.skin.DataPickerSkin;
import com.example.template4fx.fx.Displayable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Skin;

import java.util.ArrayList;
import java.util.Collection;

public class DataPicker<T extends Displayable>
    extends AbstractDialog
{
    private final ObservableList<T> available;

    private final ObservableList<T> selected;

    public DataPicker( String header, String svg, Collection<T> available, Collection<T> selected )
    {
        super( header, svg );
        getStyleClass().add( "data-picker" );

        this.available = FXCollections.observableArrayList( available );
        this.selected = FXCollections.observableArrayList( selected );

        this.available.removeAll( selected );
    }

    public void ok()
    {
        setVisible( false );
        fireEvent( DialogEvent.successEvent() );
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new DataPickerSkin<>( this );
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return getUserAgentStylesheet( DataPicker.class, "DataPicker.css" );
    }

    public void add( Collection<T> items )
    {
        available.removeAll( items );
        selected.addAll( items );
    }

    public void addAll()
    {
        Collection<T> items = new ArrayList<>( available );
        available.clear();
        selected.addAll( items );
    }

    public void remove( Collection<T> items )
    {
        selected.removeAll( items );
        available.addAll( items );
    }

    public void removeAll()
    {
        Collection<T> items = new ArrayList<>( selected );
        selected.clear();
        available.addAll( items );
    }

    public ObservableList<T> getAvailable()
    {
        return available;
    }

    public ObservableList<T> getSelected()
    {
        return selected;
    }

    public void close()
    {
        setVisible( false );
        fireEvent( DialogEvent.successEvent() );
    }
}
