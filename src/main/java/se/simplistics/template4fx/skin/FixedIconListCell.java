package se.simplistics.template4fx.skin;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FixedIconListCell<S>
    extends ListCell<S>
{
    private final Image image;

    private ImageView imageView;

    public FixedIconListCell( Image image )
    {
        this.image = image;
    }

    @Override
    protected void updateItem( S item, boolean empty )
    {
        super.updateItem( item, empty );

        if ( !empty && item != null )
        {
            if ( imageView == null )
                imageView = new ImageView( image );

            setText( item.toString() );
            setGraphic( imageView );
        }
        else
        {
            setText( null );
            setGraphic( null );
            imageView = null;
        }
    }
}
