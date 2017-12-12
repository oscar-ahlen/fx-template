package com.example.template4fx.control;

import javafx.scene.control.Control;

public abstract class FXControl
    extends Control
{
    private String stylesheet;

    protected final String getUserAgentStylesheet( Class<?> clazz, String fileName )
    {
        if ( stylesheet == null )
            stylesheet = clazz.getResource( fileName ).toExternalForm();

        return stylesheet;
    }
}
