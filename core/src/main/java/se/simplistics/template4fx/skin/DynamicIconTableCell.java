package se.simplistics.template4fx.skin;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DynamicIconTableCell<S, T extends IDisplayable>
    extends TableCell<S, T>
{
    private Image image;

    private ImageView imageView;

    private final boolean displayText;

    private final String defaultText;

    public DynamicIconTableCell()
    {
        this( true, null );
    }

    public DynamicIconTableCell( boolean displayText )
    {
        this( displayText, null );
    }

    public DynamicIconTableCell( String defaultText )
    {
        this( true, defaultText );
    }

    public DynamicIconTableCell( boolean displayText, String defaultText )
    {
        this.displayText = displayText;
        this.defaultText = defaultText;
    }

    @Override
    protected void updateItem( T item, boolean empty )
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
