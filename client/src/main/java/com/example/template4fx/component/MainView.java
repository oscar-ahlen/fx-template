package com.example.template4fx.component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;

public abstract class MainView
    extends Component
{
    private RootView rootView;

    private final BooleanProperty refreshable = new SimpleBooleanProperty( true );

    private final BooleanProperty idle = new SimpleBooleanProperty( true );

    protected MainView view( String name )
    {
        return rootView.getView( name );
    }

    public abstract void refresh();

    private void start()
    {
        idle.set( false );
    }

    private void stop()
    {
        idle.set( true );
    }

    protected void run( Task<?> task )
    {
        if ( isIdle() )
        {
            task.setOnSucceeded( event -> stop() );
            task.setOnFailed( event -> stop() );
            task.setOnCancelled( event -> stop() );

            start();
            executorService().submit( task );
        }
    }

    public RootView getRootView()
    {
        return rootView;
    }

    public void setRootView( RootView rootView )
    {
        this.rootView = rootView;
    }

    public boolean isRefreshable()
    {
        return refreshable.get();
    }

    public BooleanProperty refreshableProperty()
    {
        return refreshable;
    }

    public void setRefreshable( boolean refreshable )
    {
        this.refreshable.set( refreshable );
    }

    public boolean isIdle()
    {
        return idle.get();
    }

    public BooleanProperty idleProperty()
    {
        return idle;
    }

    public void setIdle( boolean idle )
    {
        this.idle.set( idle );
    }
}
