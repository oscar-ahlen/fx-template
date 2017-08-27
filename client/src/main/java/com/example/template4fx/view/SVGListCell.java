package com.example.template4fx.view;

import javafx.scene.control.ListCell;

public class SVGListCell<S extends Displayable<String>>
    extends ListCell<S>
{
    private final SVGIcon icon;

    public SVGListCell()
    {
        icon = new SVGIcon();
        icon.getSVG().fillProperty().bind( textFillProperty() );
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
            icon.getSVG().setContent( item.getContent() );
            setGraphic( icon.getContent() );
            setText( item.getText() );
        }
    }
}
