package com.example.template4fx.view;

import com.example.template4fx.control.SVGIcon;
import javafx.scene.control.TableCell;

public class SVGTableCell<S, T extends Displayable<String>>
    extends TableCell<S, T>
{
    private final SVGIcon icon;

    public SVGTableCell()
    {
        icon = new SVGIcon();
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
            icon.getSVG().setContent( item.getContent() );
            setGraphic( icon );
            setText( item.getText() );
        }
    }
}
