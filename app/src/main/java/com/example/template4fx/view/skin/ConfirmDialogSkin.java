package com.example.template4fx.view.skin;

import com.example.template4fx.control.SVGLabel;
import com.example.template4fx.control.dialog.ConfirmDialog;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ConfirmDialogSkin
    extends OverlaySkin<ConfirmDialog>
{
    public ConfirmDialogSkin( ConfirmDialog dialog )
    {
        super( dialog );
        createOverlay( createDialogSkin() );
    }

    private Node createDialogSkin()
    {
        VBox background = new VBox();
        background.getStyleClass().add( "confirm-dialog-background" );

        background.getChildren().addAll( createHeader(), createContent() );
        return background;
    }

    private Node createHeader()
    {
        HBox header = new HBox();
        header.getStyleClass().add( "confirm-dialog-header" );
        header.setAlignment( Pos.CENTER );

        Label headerText = new Label();
        headerText.textProperty().bind( getSkinnable().headerProperty() );

        Pane expander = new Pane();
        HBox.setHgrow( expander, Priority.ALWAYS );

        SVGLabel icon = new SVGLabel();
        icon.setSvg( "help_outline" );
        icon.setScale( 2.0 );

        header.getChildren().addAll( headerText, expander, icon );
        return header;
    }

    private Node createContent()
    {
        VBox content = new VBox();
        content.getStyleClass().add( "confirm-dialog-content" );

        Label text = new Label();
        text.textProperty().bind( getSkinnable().contentProperty() );
        text.setWrapText( true );
        text.setTextAlignment( TextAlignment.JUSTIFY );

        ButtonBar buttonBar = new ButtonBar();

        Button ok = new Button( "OK" );
        ok.setDefaultButton( true );
        ok.setOnAction( event -> getSkinnable().ok() );
        ButtonBar.setButtonData( ok, ButtonBar.ButtonData.OK_DONE );

        Button cancel = new Button( "Cancel" );
        cancel.setOnAction( event -> getSkinnable().cancel() );
        ButtonBar.setButtonData( cancel, ButtonBar.ButtonData.OK_DONE );

        buttonBar.getButtons().addAll( ok, cancel );

        content.getChildren().addAll( text, buttonBar );
        return content;
    }
}
