package com.example.template4fx.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class SVGIcon
{
    private final SVGPath frame = new SVGPath();

    private final SVGPath svg = new SVGPath();

    private final Group content;

    public SVGIcon()
    {
        frame.setContent( "M0 0h24v24H0V0z" );
        frame.setFill( Paint.valueOf( "#00000000" ) );

        content = new Group( frame, svg );
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

    public Node getContent()
    {
        return content;
    }
}
