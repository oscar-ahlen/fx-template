package com.example.template4fx.component;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;

public class DialogView
    extends MainView
{
    @FXML
    private Parent root;

    public void initialize()
    {
        setTitle( message( "title.dialog" ) );
        setSvg( "application" );
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
