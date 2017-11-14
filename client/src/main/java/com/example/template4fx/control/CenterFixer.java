package com.example.template4fx.control;

import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@DefaultProperty( "children" )
public class CenterFixer
    extends VBox
{
    public CenterFixer()
    {
        setAlignment( Pos.CENTER );

        HBox hBox = new HBox();
        hBox.setAlignment( Pos.CENTER );
        super.getChildren().add( hBox );

        children = hBox.getChildren();
    }

    private final ObservableList<Node> children;

    @Override
    public ObservableList<Node> getChildren()
    {
        return children;
    }
}
