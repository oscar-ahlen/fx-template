package com.example.template4fx.view.skin;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class OverlaySkin<T extends Control>
    extends SkinBase<T>
{
    protected OverlaySkin( T control )
    {
        super( control );
    }

    protected void createOverlay( Node content )
    {
        StackPane glass = new StackPane();
        glass.getStyleClass().add( "masker-glass" );

        VBox vBox = new VBox();
        vBox.setAlignment( Pos.CENTER );

        HBox hBox = new HBox();
        hBox.setAlignment( Pos.CENTER );

        glass.getChildren().add( vBox );
        vBox.getChildren().add( hBox );
        hBox.getChildren().add( content );

        getChildren().add( glass );
    }
}
