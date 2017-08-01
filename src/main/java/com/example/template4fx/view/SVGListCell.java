package com.example.template4fx.view;

import javafx.scene.control.ListCell;
import javafx.scene.shape.SVGPath;

public class SVGListCell<S extends Displayable<String>>
    extends ListCell<S>
{
    private final SVGPath icon;

    public SVGListCell()
    {
        icon = new SVGPath();
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
            icon.setContent( item.getContent() );
            setGraphic( icon );
            setText( item.getText() );
        }
    }
}
