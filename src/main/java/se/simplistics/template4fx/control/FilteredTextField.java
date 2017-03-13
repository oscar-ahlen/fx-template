package se.simplistics.template4fx.control;

import javafx.application.Platform;
import javafx.scene.control.TextField;

public class FilteredTextField
    extends TextField
{
    public FilteredTextField()
    {
        super();
        init();
    }

    private void init()
    {
        focusedProperty().addListener(
            ( observable, oldValue, newValue ) ->
                Platform.runLater(
                    () ->
                    {
                        String text = getText();

                        if ( isFocused() && text != null && !text.isEmpty() )
                            selectAll();
                    } )
        );
    }
}
