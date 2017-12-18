package com.example.template4fx.skin;

import com.example.template4fx.control.dialog.ValuePicker;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ValuePickerSkin<T>
    extends OverlaySkin<ValuePicker<T>>
{
    public ValuePickerSkin( ValuePicker<T> control )
    {
        super( control );
        createOverlay( createDialogSkin() );
    }

    private Node createDialogSkin()
    {
        VBox background = new VBox();
        background.getStyleClass().add( "value-picker-background" );

        TextField filter = new TextField();
        filter.textProperty().bindBidirectional( getSkinnable().filterProperty() );

        getSkinnable().setFirst( filter );
        background.getChildren().add( filter );

        ListView<T> listView = new ListView<>();
        listView.setItems( getSkinnable().getItems() );
        listView.getSelectionModel().selectFirst();

        getSkinnable().setLast( listView );
        background.getChildren().add( listView );

        filter.textProperty().addListener( ( ( observable, oldValue, newValue ) -> {
            if ( !newValue.equals( oldValue ) )
                listView.getSelectionModel().selectFirst();
        } ) );

        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener( ( observable, oldValue, newValue ) -> getSkinnable().setSelected( newValue ) );

        listView.setOnMouseClicked( event -> {
            if ( listView.getSelectionModel().getSelectedItem() != null )
                getSkinnable().success();
        } );

        Platform.runLater( filter::requestFocus );

        return background;
    }
}
