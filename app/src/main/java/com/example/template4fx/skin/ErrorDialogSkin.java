package com.example.template4fx.skin;

import com.example.template4fx.control.dialog.ErrorDialog;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorDialogSkin
    extends DialogSkin<ErrorDialog>
{
    public ErrorDialogSkin( ErrorDialog dialog )
    {
        super( dialog );
        getChildren().add( new Expander( createDialogSkin() ) );
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
        VBox.setVgrow( content, Priority.ALWAYS );

        Label errorHeader = new Label( getSkinnable().getError().getLocalizedMessage() );
        errorHeader.setWrapText( true );
        errorHeader.setTextAlignment( TextAlignment.JUSTIFY );

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter( sw );
        getSkinnable().getError().printStackTrace( pw );

        TextArea errorText = new TextArea( sw.toString() );
        errorText.setEditable( false );

        errorText.setMaxWidth( Double.MAX_VALUE );
        errorText.setMaxHeight( Double.MAX_VALUE );
        VBox.setVgrow( errorText, Priority.ALWAYS );

        getSkinnable().setFirst( errorText );

        ButtonBar buttonBar = new ButtonBar();

        Button ok = new Button( "OK" );
        ok.setDefaultButton( true );
        ok.setOnAction( event -> getSkinnable().ok() );
        ButtonBar.setButtonData( ok, ButtonBar.ButtonData.OK_DONE );
        getSkinnable().setLast( ok );

        buttonBar.getButtons().add( ok );

        content.getChildren().addAll( errorHeader, errorText, buttonBar );
        return content;
    }
}
