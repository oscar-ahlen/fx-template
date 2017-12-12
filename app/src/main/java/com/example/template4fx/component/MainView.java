package com.example.template4fx.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyEvent;

public abstract class MainView
    extends View
{
    public abstract void handleKeyEvent( KeyEvent event );

    private final StringProperty title = new SimpleStringProperty();

    public String getTitle()
    {
        return title.get();
    }

    public StringProperty titleProperty()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title.set( title );
    }

    private final StringProperty svg = new SimpleStringProperty();

    public String getSvg()
    {
        return svg.get();
    }

    public StringProperty svgProperty()
    {
        return svg;
    }

    public void setSvg( String svg )
    {
        this.svg.set( svg );
    }
}
