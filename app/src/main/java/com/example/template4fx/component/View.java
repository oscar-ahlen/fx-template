package com.example.template4fx.component;

import com.example.template4fx.control.dialog.AbstractDialog;
import com.example.template4fx.control.notification.Notification;
import com.example.template4fx.service.SettingsService;
import com.google.inject.Inject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;

public abstract class View
    extends Component
{
    private RootView rootView;

    private SettingsService settingsService;

    private ExecutorService executorService;

    protected void notification( Notification notification )
    {
        rootView.getNotificationPane().notification( notification );
    }

    protected void popup( AbstractDialog dialog )
    {
        rootView.popup( dialog );
    }

    protected String setting( String key )
    {
        return settingsService.getValue( key );
    }

    protected void run( Task<?> task )
    {
        if ( isIdle() )
        {
            task.setOnSucceeded( event -> stop() );
            task.setOnFailed( event -> stop() );
            task.setOnCancelled( event -> stop() );

            start();
            executorService.submit( task );
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

    @Inject
    public void setRootView( RootView rootView )
    {
        this.rootView = rootView;
    }

    @Inject
    public void setSettingsService( SettingsService settingsService )
    {
        this.settingsService = settingsService;
    }

    @Inject
    public void setExecutorService( ExecutorService executorService )
    {
        this.executorService = executorService;
    }
}
