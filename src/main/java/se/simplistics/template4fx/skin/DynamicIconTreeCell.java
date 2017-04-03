package se.simplistics.template4fx.skin;

import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DynamicIconTreeCell<S extends IDisplayable>
    extends TreeCell<S>
{
    private Image image;

    private ImageView imageView;

    private final boolean displayText;

    private final String defaultText;

    public DynamicIconTreeCell()
    {
        this( true, null );
    }

    public DynamicIconTreeCell( boolean displayText )
    {
        this( displayText, null );
    }

    public DynamicIconTreeCell( String defaultText )
    {
        this( true, defaultText );
    }

    public DynamicIconTreeCell( boolean displayText, String defaultText )
    {
        this.displayText = displayText;
        this.defaultText = defaultText;
    }

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
            image = null;
        }
    }
}
