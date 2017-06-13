package com.example.template4fx.control;

import com.example.template4fx.SVG;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class SVGButton
    extends Button
{
    private final StringProperty content = new SimpleStringProperty( "" );

    public SVGButton()
    {
        super();
        getStyleClass().add( "svg-button" );

        SVGPath path = new SVGPath();
        path.getStyleClass().add( "svg" );

        content.addListener( ( observable, oldValue, newValue ) -> path.contentProperty().set( SVG.get( newValue ) ) );

        path.setFill( Paint.valueOf( "#000000" ) );
        setGraphic( path );

        setPrefWidth( 26 );
        setPrefHeight( 25 );
    }

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
