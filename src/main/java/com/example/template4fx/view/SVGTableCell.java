package com.example.template4fx.view;

import javafx.scene.control.TableCell;
import javafx.scene.shape.SVGPath;

public class SVGTableCell<S, T>
    extends TableCell<S, T>
{
    private final SVGPath icon;

    public SVGTableCell( String content )
    {
        icon = new SVGPath();
        icon.setContent( content );
        icon.fillProperty().bind( textFillProperty() );
    }

    @Override
    protected void updateItem( T item, boolean empty )
    {
        super.updateItem( item, empty );

        if ( empty || item == null )
        {
            setGraphic( null );
            setText( null );
        }
        else
        {
            setGraphic( icon );
            setText( item.toString() );
        }
    }
}
