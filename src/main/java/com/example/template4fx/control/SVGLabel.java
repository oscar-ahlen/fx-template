package com.example.template4fx.control;

import com.example.template4fx.SVG;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class SVGLabel
    extends Label
{
    private final StringProperty content = new SimpleStringProperty( "" );

    public SVGLabel()
    {
        super();
        getStyleClass().add( "svg-label" );

        SVGPath path = new SVGPath();
        path.getStyleClass().add( "svg" );

        content.addListener( ( observable, oldValue, newValue ) -> path.contentProperty().set( SVG.get( newValue ) ) );

        path.setFill( Paint.valueOf( "#000000" ) );
        setGraphic( path );
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
