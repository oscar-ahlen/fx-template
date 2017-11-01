package com.example.template4fx.component;

import javafx.scene.input.KeyEvent;

public class SettingsView
    extends MainView
{
    public void initialize()
    {
        setTitle( message( "title.settings" ) );
        setSvg( "settings" );
    }

    @Override
    public void handleKeyEvent( KeyEvent event )
    {

    }
}
