package com.example.template4fx.component;

import com.google.inject.Singleton;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;

@Singleton
public class SettingsView
    extends MainView
{
    @FXML
    private Parent root;

    public void initialize()
    {
        setTitle( message( "title.settings" ) );
        setSvg( "settings" );
    }

    @Override
    public Parent getParent()
    {
        return root;
    }

    @Override
    public void handleKeyEvent( KeyEvent event )
    {

    }
}
