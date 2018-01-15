package com.example.template4fx.skin;

import com.example.template4fx.control.dialog.ValuePicker;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ValuePickerSkin<T>
    extends SkinBase<ValuePicker<T>>
{
    private TextField filter;

    private ListView<T> listView;

    private final ChangeListener<Boolean> focusListener = ( observable, oldValue, newValue ) -> {
        if ( !newValue && !hasFocus() )
            getSkinnable().cancel();
    };

    public ValuePickerSkin( ValuePicker<T> control )
    {
        super( control );
        getChildren().add( new Expander( createDialogSkin() ) );
    }

    private Node createDialogSkin()
    {
        VBox background = new VBox();
        background.getStyleClass().add( "dialog-background" );

        filter = new TextField();
        filter.textProperty().bindBidirectional( getSkinnable().filterProperty() );

        background.getChildren().add( filter );

        listView = new ListView<>();
        listView.setItems( getSkinnable().getItems() );
        listView.getSelectionModel().selectFirst();

        background.getChildren().add( listView );

        filter.textProperty().addListener( ( ( observable, oldValue, newValue ) -> {
            if ( !newValue.equals( oldValue ) )
                listView.getSelectionModel().selectFirst();
        } ) );

        filter.focusedProperty().addListener( focusListener );

        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener( ( observable, oldValue, newValue ) -> getSkinnable().setSelected( newValue ) );

        listView.setOnMouseClicked( event -> {
            if ( listView.getSelectionModel().getSelectedItem() != null )
                getSkinnable().success();
        } );

        listView.focusedProperty().addListener( focusListener );

        Platform.runLater( filter::requestFocus );

        return background;
    }

    private boolean hasFocus()
    {
        return filter.isFocused() || listView.isFocused();
    }
}
