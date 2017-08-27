package com.example.template4fx.control;

import com.example.template4fx.SVG;
import com.example.template4fx.view.SVGIcon;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class SVGLabel
    extends Label
{
    private final StringProperty svg = new SimpleStringProperty( "" );

    private final DoubleProperty scale = new SimpleDoubleProperty( 1.0 );

    public SVGLabel()
    {
        super();
        getStyleClass().add( "svg-label" );

        SVGIcon icon = new SVGIcon();
        icon.getSVG().getStyleClass().add( "svg" );

        svg.addListener(
            ( observable, oldValue, newValue ) -> icon.getSVG().contentProperty().set( SVG.get( newValue ) ) );

        scale.addListener( ( observable, oldValue, newValue ) -> icon.scale( (Double) newValue ) );

        setGraphic( icon.getContent() );
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
