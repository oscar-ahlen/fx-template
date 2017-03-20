package se.simplistics.template4fx.skin;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DynamicIconListCell<S extends IDisplayable>
    extends ListCell<S>
{
    private Image image;

    private ImageView imageView;

    private final boolean displayText;

    private final String defaultText;

    public DynamicIconListCell()
    {
        this( true, null );
    }

    public DynamicIconListCell( boolean displayText )
    {
        this( displayText, null );
    }

    public DynamicIconListCell( String defaultText )
    {
        this( true, defaultText );
    }

    public DynamicIconListCell( boolean displayText, String defaultText )
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
