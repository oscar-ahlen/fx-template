package com.example.template4fx.control.dialog;

import com.example.template4fx.skin.ConfirmDialogSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public class ConfirmDialog
    extends AbstractDialog
{
    public ConfirmDialog( String header, String content )
    {
        super( header, "help_outline" );
        getStyleClass().add( "confirm-dialog" );
        setContent( content );
    }

    public void ok()
    {
        setVisible( false );
        fireEvent( DialogEvent.successEvent() );
    }

    public void cancel()
    {
        setVisible( false );
        fireEvent( DialogEvent.cancelEvent() );
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
