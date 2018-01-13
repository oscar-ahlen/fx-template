package com.example.template4fx.skin;

import com.example.template4fx.control.dialog.ConfirmDialog;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ConfirmDialogSkin
    extends DialogSkin<ConfirmDialog>
{
    public ConfirmDialogSkin( ConfirmDialog dialog )
    {
        super( dialog );
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
        VBox content = new VBox();
        content.getStyleClass().add( "dialog-content" );

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
        ButtonBar.setButtonData( cancel, ButtonBar.ButtonData.CANCEL_CLOSE );

        buttonBar.getButtons().addAll( ok, cancel );

        content.getChildren().addAll( text, buttonBar );
        return content;
    }
}
