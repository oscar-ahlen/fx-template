package com.example.template4fx.component;

import com.example.template4fx.ApplicationContext;
import com.example.template4fx.service.SettingsService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Control;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public abstract class View
    extends Component
{
    protected RootView rootView;

    protected ApplicationContext context;

    @Override
    protected View load( String fxml )
        throws IOException
    {
        View view = (View) super.load( fxml );

        view.setRootView( rootView );
        view.setContext( context );

        return view;
    }

    protected void popup( Control control )
    {
        rootView.popup( control );
    }

    protected <T> T service( Class<T> clazz )
    {
        return context.getService( clazz );
    }

    protected String setting( String key )
    {
        return context.getService( SettingsService.class ).getValue( key );
    }

    protected void run( Task<?> task )
    {
        if ( isIdle() )
        {
            task.setOnSucceeded( event -> stop() );
            task.setOnFailed( event -> stop() );
            task.setOnCancelled( event -> stop() );

            start();
            context.getService( ExecutorService.class ).submit( task );
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

    public void setContext( ApplicationContext context )
    {
        this.context = context;
    }

    private final BooleanProperty idle = new SimpleBooleanProperty( true );

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
