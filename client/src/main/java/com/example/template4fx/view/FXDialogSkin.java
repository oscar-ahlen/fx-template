package com.example.template4fx.view;

import com.example.template4fx.control.FXDialog;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FXDialogSkin
    extends SkinBase<FXDialog>
{
    public FXDialogSkin( FXDialog dialog )
    {
        super( dialog );
        getChildren().add( init( dialog ) );
    }

    private Node init( FXDialog dialog )
    {
        VBox vBox = new VBox();
        vBox.setAlignment( Pos.CENTER );
        vBox.setSpacing( 10.0 );

        Button button = new Button( "Close dialog" );
        button.setOnAction( event -> dialog.action() );

        vBox.getChildren().add( button );

        HBox hBox = new HBox();
        hBox.setAlignment( Pos.CENTER );
        hBox.getChildren().addAll( vBox );

        StackPane glass = new StackPane();
        glass.setAlignment( Pos.CENTER );
        glass.getStyleClass().add( "masker-glass" );
        glass.getChildren().add( hBox );

        return glass;
    }
}
