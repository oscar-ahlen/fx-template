package com.example.template4fx.control.dialog;

import com.example.template4fx.skin.LoginDialogSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Skin;

public class LoginDialog
    extends AbstractDialog
{
    public LoginDialog( String header )
    {
        super( header, "shield_half_full" );
        getStyleClass().add( "login-dialog" );
    }

    public void ok()
    {
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
        return new LoginDialogSkin( this );
    }

    @Override
    public String getUserAgentStylesheet()
    {
        return getUserAgentStylesheet( ConfirmDialog.class, "LoginDialog.css" );
    }

    private final StringProperty username = new SimpleStringProperty();

    public String getUsername()
    {
        return username.get();
    }

    public StringProperty usernameProperty()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username.set( username );
    }

    private final StringProperty password = new SimpleStringProperty();

    public String getPassword()
    {
        return password.get();
    }

    public StringProperty passwordProperty()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password.set( password );
    }
}
