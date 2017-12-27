package com.example.template4fx.skin;

import com.example.template4fx.control.SVGButton;
import com.example.template4fx.control.dialog.DataPicker;
import com.example.template4fx.view.Displayable;
import com.example.template4fx.view.SVGListCellFactory;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collection;

public class DataPickerSkin<T extends Displayable>
    extends DialogSkin<DataPicker<T>>
{
    private ListView<T> available;

    private ListView<T> selected;

    public DataPickerSkin( DataPicker<T> control )
    {
        super( control );
        createOverlay( createDialogSkin() );
    }

    private Node createDialogSkin()
    {
        VBox background = new VBox();
        background.getStyleClass().add( "dialog-background" );

        background.getChildren().addAll( createHeader(), createContent() );
        return background;
    }

    private Node createContent()
    {
        BorderPane content = new BorderPane();
        content.getStyleClass().add( "dialog-content" );

        content.setLeft( createAvailableList() );
        content.setRight( createSelectedList() );
        content.setCenter( createButtonBar() );

        return content;
    }

    private Node createAvailableList()
    {
        VBox container = new VBox();

        available = new ListView<>( getSkinnable().getAvailable() );
        available.setCellFactory( new SVGListCellFactory<>() );
        available.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
        available.setPrefWidth( 400 );

        container.getChildren().add( available );
        return container;
    }

    private Node createSelectedList()
    {
        VBox container = new VBox();
        container.getStyleClass().add( "list-container" );

        selected = new ListView<>( getSkinnable().getSelected() );
        selected.setCellFactory( new SVGListCellFactory<>() );
        selected.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
        selected.setPrefWidth( 400 );

        container.getChildren().add( selected );
        return container;
    }

    private Node createButtonBar()
    {
        VBox buttonBar = new VBox();
        buttonBar.getStyleClass().add( "button-bar" );

        SVGButton add = new SVGButton();
        add.setSvg( "chevron_right" );
        add.setOnAction( event -> getSkinnable().add( convert( available ) ) );

        SVGButton addAll = new SVGButton();
        addAll.setSvg( "chevron_right_double" );
        addAll.setOnAction( event -> getSkinnable().addAll() );

        SVGButton remove = new SVGButton();
        remove.setSvg( "chevron_left" );
        remove.setOnAction( event -> getSkinnable().remove( convert( selected ) ) );

        SVGButton removeAll = new SVGButton();
        removeAll.setSvg( "chevron_left_double" );
        removeAll.setOnAction( event -> getSkinnable().removeAll() );

        buttonBar.getChildren().addAll( add, addAll, remove, removeAll );
        return buttonBar;
    }

    private Collection<T> convert( ListView<T> list )
    {
        return new ArrayList<>( list.getSelectionModel().getSelectedItems() );
    }
}
