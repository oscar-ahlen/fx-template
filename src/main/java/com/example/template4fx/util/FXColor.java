package com.example.template4fx.util;

import javafx.collections.FXCollections;

import java.util.List;

public class FXColor
{
    public static final List<FXColor> COLORS = FXCollections.unmodifiableObservableList(
        FXCollections.observableArrayList( new FXColor( "Blue", "#1976D2", "#2196F3" ),
                                           new FXColor( "Pink", "#C2185B", "#E91E63" ),
                                           new FXColor( "Teal", "#00796B", "#009688" ),
                                           new FXColor( "Purple", "#7B1fA2", "#9C27B0" ) ) );

    private String name;

    private String accent;

    private String focus;

    public FXColor()
    {
    }

    public FXColor( String name, String accent, String focus )
    {
        this.name = name;
        this.accent = accent;
        this.focus = focus;
    }

    public String getCss()
    {
        return String.format( "-fx-accent: %s;\n-fx-focus-color: %s;", accent, focus );
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getAccent()
    {
        return accent;
    }

    public void setAccent( String accent )
    {
        this.accent = accent;
    }

    public String getFocus()
    {
        return focus;
    }

    public void setFocus( String focus )
    {
        this.focus = focus;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
