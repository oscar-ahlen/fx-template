package com.example.template4fx.control;

import com.example.template4fx.SVG;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class SVGTextButton
    extends Button
{
    public SVGTextButton()
    {
        super();
        getStyleClass().add( "svg-text-button" );

        SVGIcon icon = new SVGIcon();

        svg.addListener( ( observable, oldValue, newValue ) ->
                             icon.getSVG().contentProperty().set( SVG.INSTANCE.get( newValue ) ) );

        setGraphic( icon );
    }

    private final StringProperty svg = new SimpleStringProperty( "" );

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
