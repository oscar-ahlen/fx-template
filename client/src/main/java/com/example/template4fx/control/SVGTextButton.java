package com.example.template4fx.control;

import com.example.template4fx.SVG;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class SVGTextButton
    extends Button
{
    private final StringProperty svg = new SimpleStringProperty( "" );

    public SVGTextButton()
    {
        super();
        getStyleClass().add( "svg-text-button" );

        SVGPath path = new SVGPath();
        path.getStyleClass().add( "svg" );

        svg.addListener(
            ( observable, oldValue, newValue ) -> path.contentProperty().set( SVG.get( newValue ) ) );

        path.setFill( Paint.valueOf( "#000000" ) );
        setGraphic( path );

        setMaxHeight( 25 );
    }

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
