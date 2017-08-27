package com.example.template4fx.control.dialog;

import com.example.template4fx.view.ConfirmDialogSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public class ConfirmDialog
    extends AbstractDialog
{
    private final StringProperty content = new SimpleStringProperty();

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
            runnable.run();
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

    public StringProperty contentProperty()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content.set( content );
    }
}
