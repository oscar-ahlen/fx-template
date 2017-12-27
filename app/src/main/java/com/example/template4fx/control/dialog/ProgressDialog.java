package com.example.template4fx.control.dialog;

import com.example.template4fx.skin.ProgressDialogSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Skin;
import javafx.scene.input.KeyEvent;

public class ProgressDialog
    extends AbstractDialog
{
    public ProgressDialog( String header, Task task )
    {
        super( header, "timelapse" );
        getStyleClass().add( "progress-dialog" );
        setTask( task );
    }

    @Override
    public void handleKeyEvent( KeyEvent event )
    {
        if ( task.get().isRunning() )
            event.consume();
        else
            super.handleKeyEvent( event );
    }

    public void close()
    {
        setVisible( false );
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new ProgressDialogSkin( this );
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return getUserAgentStylesheet( ProgressDialog.class, "ProgressDialog.css" );
    }

    private final ObjectProperty<Task> task = new SimpleObjectProperty<>();

    public Task getTask()
    {
        return task.get();
    }

    public ObjectProperty<Task> taskProperty()
    {
        return task;
    }

    public void setTask( Task task )
    {
        this.task.set( task );
    }
}
