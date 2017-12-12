package com.example.template4fx.view;

import com.example.template4fx.control.SVGIcon;
import javafx.scene.control.ListCell;

public class SVGListCell<S extends Displayable>
    extends ListCell<S>
{
    private final SVGIcon icon = new SVGIcon();

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
            setGraphic( icon );
            setText( item.getTitle() );
        }
    }
}
