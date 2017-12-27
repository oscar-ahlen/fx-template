package com.example.template4fx.control.dialog;

import com.example.template4fx.skin.InfoDialogSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public class InfoDialog
    extends AbstractDialog
{
    public InfoDialog( String header, String content )
    {
        super( header, "info_outline" );
        getStyleClass().add( "info-dialog" );
        setContent( content );
    }

    public void ok()
    {
        setVisible( false );
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new InfoDialogSkin( this );
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return getUserAgentStylesheet( InfoDialog.class, "InfoDialog.css" );
    }

    private final StringProperty content = new SimpleStringProperty();

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
