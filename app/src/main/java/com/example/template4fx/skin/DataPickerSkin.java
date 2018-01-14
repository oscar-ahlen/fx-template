package com.example.template4fx.skin;

import com.example.template4fx.control.SVGButton;
import com.example.template4fx.control.dialog.DataPicker;
import com.example.template4fx.fx.Displayable;
import com.example.template4fx.view.SVGListCellFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
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
        content.setBottom( createBottomBar() );

        return content;
    }

    private Node createAvailableList()
    {
        available = createListView();
        getSkinnable().setFirst( available );

        VBox container = new VBox();
        container.getChildren().add( available );
        return container;
    }

    private Node createSelectedList()
    {
        selected = createListView();

        VBox container = new VBox();
        container.getChildren().add( selected );
        return container;
    }

    private ListView<T> createListView()
    {
        ListView<T> listView = new ListView<>( getSkinnable().getSelected() );

        listView.setCellFactory( new SVGListCellFactory<>() );
        listView.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
        listView.setPrefWidth( 300 );
        listView.setPrefHeight( 300 );

        return listView;
    }

    private Node createButtonBar()
    {
        VBox buttonBar = new VBox();
        buttonBar.getStyleClass().add( "vertical-bar" );

        SVGButton add = new SVGButton();
        add.setSvg( "chevron_right" );
        add.setOnAction( event -> getSkinnable().add( convert( available ) ) );

        SVGButton addAll = new SVGButton();
        addAll.setSvg( "chevron_double_right" );
        addAll.setOnAction( event -> getSkinnable().addAll() );

        SVGButton remove = new SVGButton();
        remove.setSvg( "chevron_left" );
        remove.setOnAction( event -> getSkinnable().remove( convert( selected ) ) );

        SVGButton removeAll = new SVGButton();
        removeAll.setSvg( "chevron_double_left" );
        removeAll.setOnAction( event -> getSkinnable().removeAll() );

        buttonBar.getChildren().addAll( add, addAll, remove, removeAll );
        return buttonBar;
    }

    private Node createBottomBar()
    {
        ButtonBar buttonBar = new ButtonBar();
        buttonBar.getStyleClass().add( "bottom-bar" );

        Button ok = new Button( "OK" );
        ok.setDefaultButton( true );
        ok.setOnAction( event -> getSkinnable().ok() );
        ButtonBar.setButtonData( ok, ButtonBar.ButtonData.OK_DONE );
        getSkinnable().setLast( ok );

        buttonBar.getButtons().add( ok );

        return buttonBar;
    }

    private Collection<T> convert( ListView<T> list )
    {
        return new ArrayList<>( list.getSelectionModel().getSelectedItems() );
    }
}
