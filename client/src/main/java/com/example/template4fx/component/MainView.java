package com.example.template4fx.component;

import com.example.template4fx.ApplicationContext;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Control;
import javafx.scene.input.KeyEvent;

public abstract class MainView
    extends Component
{
    protected RootView rootView;

    protected ApplicationContext context;

    public abstract void handleKeyEvent( KeyEvent event );

    protected MainView view( View name )
    {
        return rootView.getView( name );
    }

    protected void showDialog( Control control )
    {
        rootView.showDialog( control );
    }

    protected String setting( String key )
    {
        return context.getSettingsService().getValue( key );
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

    public void setContext( ApplicationContext context )
    {
        this.context = context;
    }

    private final StringProperty title = new SimpleStringProperty();

    public String getTitle()
    {
        return title.get();
    }

    public StringProperty titleProperty()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title.set( title );
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
