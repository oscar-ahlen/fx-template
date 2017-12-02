package com.example.template4fx.view.skin;

import com.example.template4fx.Keys;
import com.example.template4fx.control.dialog.ValuePicker;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ValuePickerSkin<T>
    extends SkinBase<ValuePicker<T>>
{
    private TextField filter;

    private ListView<T> listView;

    private ChangeListener<Boolean> focusListener = ( observable, oldValue, newValue ) ->
    {
        if ( !newValue && lostFocus() )
            close();
    };

    public ValuePickerSkin( ValuePicker<T> control )
    {
        super( control );
        getChildren().add( initialize() );
    }

    public Node initialize()
    {
        StackPane glass = new StackPane();
        glass.getStyleClass().add( "masker-glass" );

        VBox vBox = new VBox();
        vBox.setAlignment( Pos.CENTER );
        glass.getChildren().add( vBox );

        HBox hBox = new HBox();
        hBox.setAlignment( Pos.CENTER );
        vBox.getChildren().add( hBox );

        hBox.getChildren().add( createDialogSkin() );

        return glass;
    }

    private Node createDialogSkin()
    {
        VBox background = new VBox();
        background.getStyleClass().add( "value-picker-background" );

        filter = new TextField();
        filter.textProperty().bindBidirectional( getSkinnable().filterProperty() );

        background.getChildren().add( filter );

        Platform.runLater( filter::requestFocus );

        listView = new ListView<>();
        listView.setItems( getSkinnable().getItems() );
        listView.getSelectionModel().selectFirst();

        background.getChildren().add( listView );

        filter.focusedProperty().addListener( focusListener );
        listView.focusedProperty().addListener( focusListener );

        filter.textProperty().addListener( ( ( observable, oldValue, newValue ) -> {
            if ( !newValue.equals( oldValue ) )
                listView.getSelectionModel().selectFirst();
        } ) );

        filter.setOnKeyPressed( this::OnKeyPressed );
        listView.setOnKeyPressed( this::OnKeyPressed );

        listView.getSelectionModel().selectedItemProperty()
                .addListener( ( observable, oldValue, newValue ) -> getSkinnable().setSelected( newValue ) );

        listView.setOnMouseClicked( event -> {
            if ( listView.getSelectionModel().getSelectedItem() != null )
                close();
        } );

        background.addEventFilter( KeyEvent.KEY_PRESSED, event -> {
            if ( Keys.ESCAPE.match( event ) )
                close();
        } );

        return background;
    }

    private boolean lostFocus()
    {
        return !filter.isFocused() && !listView.isFocused();
    }

    private void OnKeyPressed( KeyEvent event )
    {
        if ( Keys.ENTER.match( event ) && listView.getSelectionModel().getSelectedItem() != null )
            close();
    }

    private void close()
    {
        getSkinnable().setVisible( false );
        Platform.runLater( () -> getSkinnable().getSource().requestFocus() );
    }
}
