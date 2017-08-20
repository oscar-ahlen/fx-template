package com.example.template4fx.control;

import com.example.template4fx.SVG;
import com.example.template4fx.fx.SVGIcon;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Tab;

public class SVGTab
    extends Tab
{
    private final StringProperty svgContent = new SimpleStringProperty( "" );

    public SVGTab()
    {
        super();
        getStyleClass().add( "svg-tab" );

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
