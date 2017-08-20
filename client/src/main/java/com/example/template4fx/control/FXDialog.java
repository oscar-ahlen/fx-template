package com.example.template4fx.control;

import com.example.template4fx.view.FXDialogSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Skin;

public class FXDialog
    extends FXControl
{
    private final BooleanProperty active = new SimpleBooleanProperty();

    public FXDialog()
    {
        getStyleClass().add( "dialog" );
        setActive( true );
    }

    public void setOnClose( Runnable runnable )
    {
        active.addListener(
            ( observable, oldValue, newValue ) -> {
                runnable.run();
                setVisible( false );
                setManaged( false );
            } );
    }

    public void action()
    {
        active.setValue( false );
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new FXDialogSkin( this );
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return getUserAgentStylesheet( FXDialog.class, "dialog.css" );
    }

    public boolean isActive()
    {
        return active.get();
    }

    public BooleanProperty activeProperty()
    {
        return active;
    }

    public void setActive( boolean active )
    {
        this.active.set( active );
    }
}
