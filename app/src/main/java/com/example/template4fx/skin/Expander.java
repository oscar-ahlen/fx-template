package com.example.template4fx.skin;

import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

@DefaultProperty( "items" )
public class Expander
    extends StackPane
{
    private final ObservableList<Node> items;

    public Expander()
    {
        getStyleClass().add( "masker-glass" );

        VBox vbox = new VBox();
        vbox.setAlignment( Pos.CENTER );
        getChildren().add( vbox );

        HBox hbox = new HBox();
        hbox.setAlignment( Pos.CENTER );
        vbox.getChildren().add( hbox );

        items = hbox.getChildren();
    }

    public Expander( Node... nodes )
    {
        this();
        items.addAll( nodes );
    }

    public ObservableList<Node> getItems()
    {
        return items;
    }
}
