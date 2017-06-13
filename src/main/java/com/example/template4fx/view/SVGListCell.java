package com.example.template4fx.view;

import javafx.scene.control.ListCell;
import javafx.scene.shape.SVGPath;

public class SVGListCell<S>
    extends ListCell<S>
{
    private final SVGPath icon;

    public SVGListCell( String content )
    {
        icon = new SVGPath();
        icon.setContent( content );
        icon.fillProperty().bind( textFillProperty() );
    }

    @Override
    protected void updateItem( S item, boolean empty )
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
