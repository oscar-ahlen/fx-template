package se.simplistics.template4fx.skin;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FixedIconTableCell<S, T>
    extends TableCell<S, T>
{
    private final Image image;

    private ImageView imageView;

    public FixedIconTableCell( Image image )
    {
        this.image = image;
    }

    @Override
    protected void updateItem( T item, boolean empty )
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
