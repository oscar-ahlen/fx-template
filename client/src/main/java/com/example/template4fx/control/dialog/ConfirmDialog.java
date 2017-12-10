package com.example.template4fx.control.dialog;

import com.example.template4fx.view.skin.ConfirmDialogSkin;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public class ConfirmDialog
    extends AbstractDialog
{
    private Runnable ok, cancel;

    public ConfirmDialog( String header, String content )
    {
        super( header );
        getStyleClass().add( "confirm-dialog" );
        setContent( content );
    }

    public void setOnOK( Runnable ok )
    {
        this.ok = ok;
    }

    public void setOnCancel( Runnable cancel )
    {
        this.cancel = cancel;
    }

    public void ok()
    {
        run( ok );
    }

    public void cancel()
    {
        run( cancel );
    }

    private void run( Runnable runnable )
    {
        setVisible( false );

        if ( runnable != null )
            Platform.runLater( runnable );
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new ConfirmDialogSkin( this );
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return getUserAgentStylesheet( ConfirmDialog.class, "ConfirmDialog.css" );
    }

    public String getContent()
    {
        return content.get();
    }

    private final StringProperty content = new SimpleStringProperty();

    public StringProperty contentProperty()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content.set( content );
    }
}
