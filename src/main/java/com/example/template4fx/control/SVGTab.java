package com.example.template4fx.control;

import com.example.template4fx.SVG;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Tab;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class SVGTab
    extends Tab
{
    private final StringProperty svgContent = new SimpleStringProperty( "" );

    public SVGTab()
    {
        super();
        getStyleClass().add( "svg-tab" );

        SVGPath path = new SVGPath();
        path.getStyleClass().add( "svg" );

        svgContent
            .addListener( ( observable, oldValue, newValue ) -> path.contentProperty().set( SVG.get( newValue ) ) );

        path.setFill( Paint.valueOf( "#000000" ) );
        setGraphic( path );
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
