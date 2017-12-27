package com.example.template4fx.skin;

import com.example.template4fx.control.dialog.InfoDialog;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class InfoDialogSkin
    extends DialogSkin<InfoDialog>
{
    public InfoDialogSkin( InfoDialog dialog )
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

        getSkinnable().setFirst( ok );
        getSkinnable().setLast( ok );
        buttonBar.getButtons().add( ok );

        content.getChildren().addAll( text, buttonBar );
        return content;
    }
}
