package com.example.template4fx.view.skin;

import com.example.template4fx.control.SVGLabel;
import com.example.template4fx.control.dialog.ErrorDialog;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorDialogSkin
    extends SkinBase<ErrorDialog>
{
    private final ErrorDialog dialog;

    public ErrorDialogSkin( ErrorDialog dialog )
    {
        super( dialog );
        this.dialog = dialog;
        getChildren().add( init() );
    }

    private Node init()
    {
        StackPane glass = new StackPane();
        glass.setAlignment( Pos.CENTER );
        glass.getStyleClass().add( "masker-glass" );

        VBox vBox = new VBox();
        vBox.setAlignment( Pos.CENTER );
        glass.getChildren().add( vBox );

        HBox hBox = new HBox();
        hBox.setAlignment( Pos.CENTER );
        vBox.getChildren().add( hBox );

        hBox.getChildren().add( createDialogSkin() );
        return glass;
    }

    private Node createDialogSkin()
    {
        VBox background = new VBox();
        background.getStyleClass().add( "error-dialog-background" );

        background.getChildren().addAll( createHeader(), createContent() );

        return background;
    }

    private Node createHeader()
    {
        HBox header = new HBox();
        header.getStyleClass().add( "error-dialog-header" );
        header.setAlignment( Pos.CENTER );

        Label headerText = new Label( dialog.getHeader() );

        Pane expander = new Pane();
        HBox.setHgrow( expander, Priority.ALWAYS );

        SVGLabel icon = new SVGLabel();
        icon.setSvg( "warning" );
        icon.setScale( 2.0 );

        header.getChildren().addAll( headerText, expander, icon );
        return header;
    }

    private Node createContent()
    {
        VBox content = new VBox();
        content.getStyleClass().add( "error-dialog-content" );
        VBox.setVgrow( content, Priority.ALWAYS );

        Label errorHeader = new Label( dialog.getError().getLocalizedMessage() );
        errorHeader.setWrapText( true );
        errorHeader.setTextAlignment( TextAlignment.JUSTIFY );

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter( sw );
        dialog.getError().printStackTrace( pw );

        TextArea errorText = new TextArea( sw.toString() );
        errorText.setFocusTraversable( false );
        errorText.setEditable( false );

        errorText.setMaxWidth( Double.MAX_VALUE );
        errorText.setMaxHeight( Double.MAX_VALUE );
        VBox.setVgrow( errorText, Priority.ALWAYS );

        ButtonBar buttonBar = new ButtonBar();

        Button ok = new Button( "OK" );
        ok.setDefaultButton( true );
        ok.setOnAction( event -> dialog.ok() );
        ButtonBar.setButtonData( ok, ButtonBar.ButtonData.OK_DONE );
        buttonBar.getButtons().add( ok );

        content.getChildren().addAll( errorHeader, errorText, buttonBar );
        return content;
    }
}
