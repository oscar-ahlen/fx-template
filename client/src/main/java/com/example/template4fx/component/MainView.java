package com.example.template4fx.component;

import com.example.template4fx.FXContext;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Control;
import javafx.scene.input.KeyEvent;

public abstract class MainView
    extends Component
{
    private RootView rootView;

    private FXContext context;

    private final BooleanProperty refreshable = new SimpleBooleanProperty( true );

    private final BooleanProperty idle = new SimpleBooleanProperty( true );

    public abstract void handleKeyEvent( KeyEvent event );

    public abstract void refresh();

    protected MainView view( String name )
    {
        return rootView.getView( name );
    }

    protected void showDialog( Control control )
    {
        rootView.showDialog( control );
    }

    protected String setting( String key )
    {
        return context.getSettings().getValue( key );
    }

    protected void run( Task<?> task )
    {
        if ( isIdle() )
        {
            task.setOnSucceeded( event -> stop() );
            task.setOnFailed( event -> stop() );
            task.setOnCancelled( event -> stop() );

            start();
            context.getExecutorService().submit( task );
        }
    }

    private void start()
    {
        idle.set( false );
    }

    private void stop()
    {
        idle.set( true );
    }

    public void setRootView( RootView rootView )
    {
        this.rootView = rootView;
    }

    public void setContext( FXContext context )
    {
        this.context = context;
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
