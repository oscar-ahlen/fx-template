package com.example.template4fx.control;

import com.example.template4fx.SVG;
import com.example.template4fx.fx.SVGIcon;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class SVGLabel
    extends Label
{
    private final StringProperty svgContent = new SimpleStringProperty( "" );

    public SVGLabel()
    {
        super();
        getStyleClass().add( "svg-label" );

        SVGIcon icon = new SVGIcon();
        icon.getSVG().getStyleClass().add( "svg" );

        svgContent.addListener(
            ( observable, oldValue, newValue ) -> icon.getSVG().contentProperty().set( SVG.get( newValue ) ) );

        setGraphic( icon.getContent() );
    }

    public String getSvgContent()
    {
        return svgContent.get();
    }

    public StringProperty svgContentProperty()
    {
        return svgContent;
    }

    public void setSvgContent( String svgContent )
    {
        this.svgContent.set( svgContent );
    }
}
