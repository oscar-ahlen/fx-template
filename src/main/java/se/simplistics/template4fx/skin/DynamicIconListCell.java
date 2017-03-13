package se.simplistics.template4fx.skin;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DynamicIconListCell<S extends IDisplayable>
    extends ListCell<S>
{
    private Image image;

    private ImageView imageView;

    @Override
    protected void updateItem( S item, boolean empty )
    {
        super.updateItem( item, empty );

        if ( !empty && item != null )
        {
            if ( image == null || image != item.getImage() )
            {
                image = item.getImage();
                imageView = new ImageView( item.getImage() );
            }

            setText( item.toString() );
            setGraphic( imageView );
        }
        else
        {
            setText( null );
            setGraphic( null );
            imageView = null;
            image = null;
        }
    }
}
