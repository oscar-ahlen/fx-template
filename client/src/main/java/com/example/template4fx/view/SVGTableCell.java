package com.example.template4fx.view;

import com.example.template4fx.control.SVGIcon;
import javafx.scene.control.TableCell;

public class SVGTableCell<S extends Displayable<String>, T>
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
            S object = getTableView().getItems().get( getIndex() );
            icon.getSVG().setContent( object.getContent() );

            setGraphic( icon );
            setText( item.toString() );
        }
    }
}
