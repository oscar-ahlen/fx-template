package com.example.template4fx.view;

import com.example.template4fx.control.SVGIcon;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SVGListCellFactory<S extends Displayable>
    implements Callback<ListView<S>, ListCell<S>>
{
    @Override
    public ListCell<S> call( ListView<S> param )
    {
        return new ListCell<S>()
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
        };
    }
}
