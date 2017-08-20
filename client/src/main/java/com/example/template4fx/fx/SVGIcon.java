package com.example.template4fx.fx;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

public class SVGIcon
{
    private final SVGPath svg;

    private final Group content;

    public SVGIcon()
    {
        SVGPath frame = new SVGPath();
        frame.setContent( "M0 0h24v24H0V0z" );
        frame.setFill( Paint.valueOf( "#00000000" ) );

        svg = new SVGPath();
        content = new Group( frame, svg );
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
