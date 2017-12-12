package com.example.template4fx.view;

import com.example.template4fx.control.SVGIcon;
import javafx.scene.control.TableCell;

public class SVGTableCell<S extends Displayable, T>
    extends TableCell<S, T>
{
    private final SVGIcon icon = new SVGIcon();

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
            Displayable displayable = getTableView().getItems().get( getIndex() );

            if ( displayable != null )
            {
                icon.getSVG().setContent( displayable.getContent() );
                setGraphic( icon );
                setText( displayable.getTitle() );
            }
            else
            {
                setGraphic( null );
                setText( null );
            }
        }
    }
}

