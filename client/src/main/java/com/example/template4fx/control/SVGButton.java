package com.example.template4fx.control;

import com.example.template4fx.SVG;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class SVGButton
    extends Button
{
    public SVGButton()
    {
        super();
        getStyleClass().add( "svg-button" );

        SVGIcon icon = new SVGIcon();

        svg.addListener( ( observable, oldValue, newValue ) ->
                             icon.getSVG().contentProperty().set( SVG.INSTANCE.get( newValue ) ) );

        scale.addListener( ( observable, oldValue, newValue ) -> icon.scale( (Double) newValue ) );

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

    private final DoubleProperty scale = new SimpleDoubleProperty( 1.0 );

    public double getScale()
    {
        return scale.get();
    }

    public DoubleProperty scaleProperty()
    {
        return scale;
    }

    public void setScale( double scale )
    {
        this.scale.set( scale );
    }
}
