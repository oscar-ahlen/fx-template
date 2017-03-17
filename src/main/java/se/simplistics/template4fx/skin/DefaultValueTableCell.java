package se.simplistics.template4fx.skin;

import javafx.scene.control.TableCell;

public class DefaultValueTableCell<S, T>
    extends TableCell<S, T>
{
    private final String defaultValue;

    public DefaultValueTableCell( String defaultValue )
    {
        this.defaultValue = defaultValue;
    }

    @Override
    protected void updateItem( T item, boolean empty )
    {
        super.updateItem( item, empty );

        if ( !empty && item != null )
            setText( item.toString() );
        else if ( !empty )
            setText( defaultValue );
        else
            setText( null );
    }
}
