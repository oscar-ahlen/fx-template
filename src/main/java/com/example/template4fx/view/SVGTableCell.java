package com.example.template4fx.view;

import javafx.scene.control.TableCell;
import javafx.scene.shape.SVGPath;

public class SVGTableCell<S, T extends Displayable<String>>
    extends TableCell<S, T>
{
    private final SVGPath icon;

    public SVGTableCell()
    {
        icon = new SVGPath();
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
            icon.setContent( item.getContent() );
            setGraphic( icon );
            setText( item.getText() );
        }
    }
}
