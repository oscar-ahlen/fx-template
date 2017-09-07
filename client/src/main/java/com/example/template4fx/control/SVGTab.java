package com.example.template4fx.control;

import com.example.template4fx.SVG;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Tab;

public class SVGTab
    extends Tab
{
    private final StringProperty svg = new SimpleStringProperty( "" );

    public SVGTab()
    {
        super();
        getStyleClass().add( "svg-tab" );

        SVGIcon icon = new SVGIcon();

        svg.addListener(
            ( observable, oldValue, newValue ) -> icon.getSVG().contentProperty().set( SVG.get( newValue ) ) );

        setGraphic( icon );
    }

    public String getSvg()
    {
        return svg.get();
    }

    public StringProperty svgProperty()
    {
        return svg;
    }

    public void setSvg( String svgContent )
    {
        this.svg.set( svgContent );
    }
}
