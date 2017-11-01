package com.example.template4fx.util;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HistoryList<T>
{
    private final ObservableList<T> items = FXCollections.observableArrayList();

    private int pointer = -1;

    private final int limit;

    public HistoryList()
    {
        this( 20 );
    }

    public HistoryList( int limit )
    {
        this.limit = limit;
    }

    public T getCurrent()
    {
        if ( !items.isEmpty() )
            return items.get( pointer );

        return null;
    }

    public void add( T item )
    {
        if ( getCurrent() == item )
            return;

        resetForwardHistory();

        items.add( item );
        pointer++;

        if ( items.size() > limit )
            trim();

        updateProperties();
    }

    public T backward()
    {
        if ( pointer > 0 )
            pointer--;

        updateProperties();

        return items.get( pointer );
    }

    public T forward()
    {
        if ( pointer < items.size() - 1 )
            pointer++;

        updateProperties();

        return items.get( pointer );
    }

    private void resetForwardHistory()
    {
        if ( pointer < items.size() - 1 )
            items.remove( pointer + 1, items.size() );
    }

    private void updateProperties()
    {
        if ( pointer > 0 )
            backwardEnabled.set( true );
        else
            backwardEnabled.set( false );

        if ( pointer < items.size() - 1 )
            forwardEnabled.set( true );
        else
            forwardEnabled.set( false );
    }

    private void trim()
    {
        items.remove( 0 );
        pointer--;
    }

    private final BooleanProperty backwardEnabled = new SimpleBooleanProperty( false );

    public boolean isBackwardEnabled()
    {
        return backwardEnabled.get();
    }

    public BooleanProperty backwardEnabledProperty()
    {
        return backwardEnabled;
    }

    private final BooleanProperty forwardEnabled = new SimpleBooleanProperty( false );

    public boolean isForwardEnabled()
    {
        return forwardEnabled.get();
    }

    public BooleanProperty forwardEnabledProperty()
    {
        return forwardEnabled;
    }
}
