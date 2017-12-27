package com.example.template4fx.control;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class SVGIcon
    extends Group
{
    private final SVGPath frame = new SVGPath();

    private final SVGPath svg = new SVGPath();

    public SVGIcon()
    {
        getStyleClass().add( "svg-icon" );
        getChildren().addAll( frame, svg );

        frame.setContent( "M0 0h24v24H0V0z" );
        frame.setFill( Paint.valueOf( "#00000000" ) );

        svg.getStyleClass().add( "svg" );
        svg.setFill( Paint.valueOf( "#000000" ) );
    }

    public SVGIcon( String content )
    {
        this();
        svg.setContent( content );
    }

    public void scale( double scale )
    {
        frame.setScaleX( scale );
        frame.setScaleY( scale );

        svg.setScaleX( scale );
        svg.setScaleY( scale );
    }

    public SVGPath getSVG()
    {
        return svg;
    }
}
