package se.simplistics.template4fx.skin;

import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FixedIconTreeCell<S>
    extends TreeCell<S>
{
    private final Image image;

    private ImageView imageView;

    private final boolean displayText;

    private final String defaultText;

    public FixedIconTreeCell( Image image )
    {
        this( image, true, null );
    }

    public FixedIconTreeCell( Image image, boolean displayText )
    {
        this( image, displayText, null );
    }

    public FixedIconTreeCell( Image image, String defaultText )
    {
        this( image, true, defaultText );
    }

    public FixedIconTreeCell( Image image, boolean displayText, String defaultText )
    {
        this.image = image;
        this.displayText = displayText;
        this.defaultText = defaultText;
    }

    @Override
    protected void updateItem( S item, boolean empty )
    {
        super.updateItem( item, empty );

        if ( !empty && item != null )
        {
            if ( imageView == null )
                imageView = new ImageView( image );

            if ( displayText )
                setText( item.toString() );
            else
                setText( null );

            setGraphic( imageView );
        }
        else
        {
            setText( defaultText );
            setGraphic( null );
            imageView = null;
        }
    }
}
