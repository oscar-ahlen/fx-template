package com.example.template4fx.control.dialog;

import com.example.template4fx.view.skin.ErrorDialogSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Skin;

public class ErrorDialog
    extends AbstractDialog
{
    private final ObjectProperty<Throwable> error = new SimpleObjectProperty<>();

    public ErrorDialog( String header, Throwable error )
    {
        super( header );
        getStyleClass().add( "error-dialog" );
        setError( error );
    }

    public void ok()
    {
        setVisible( false );
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new ErrorDialogSkin( this );
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return getUserAgentStylesheet( ErrorDialog.class, "ErrorDialog.css" );
    }

    public Throwable getError()
    {
        return error.get();
    }

    public ObjectProperty<Throwable> errorProperty()
    {
        return error;
    }

    public void setError( Throwable error )
    {
        this.error.set( error );
    }
}
