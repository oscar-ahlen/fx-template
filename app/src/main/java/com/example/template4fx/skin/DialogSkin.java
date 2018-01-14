package com.example.template4fx.skin;

import com.example.template4fx.control.SVGLabel;
import com.example.template4fx.control.dialog.AbstractDialog;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public abstract class DialogSkin<T extends AbstractDialog>
    extends SkinBase<T>
{
    protected DialogSkin( T dialog )
    {
        super( dialog );
    }

    protected Node createHeader()
    {
        HBox header = new HBox();
        header.getStyleClass().add( "dialog-header" );

        Label headerText = new Label();
        headerText.textProperty().bind( getSkinnable().headerProperty() );

        Pane expander = new Pane();
        HBox.setHgrow( expander, Priority.ALWAYS );

        SVGLabel icon = new SVGLabel();
        icon.svgProperty().bind( getSkinnable().svgProperty() );
        icon.setScale( 2.0 );

        header.getChildren().addAll( headerText, expander, icon );
        return header;
    }
}
