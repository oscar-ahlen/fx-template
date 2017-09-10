package com.example.template4fx.view.skin;

import com.example.template4fx.control.SVGLabel;
import com.example.template4fx.control.dialog.InfoDialog;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class InfoDialogSkin
    extends SkinBase<InfoDialog>
{
    private final InfoDialog dialog;

    public InfoDialogSkin( InfoDialog dialog )
    {
        super( dialog );
        this.dialog = dialog;
        getChildren().add( init() );
    }

    private Node init()
    {
        StackPane glass = new StackPane();
        glass.getStyleClass().add( "masker-glass" );

        VBox vBox = new VBox();
        vBox.setAlignment( Pos.CENTER );

        HBox hBox = new HBox();
        hBox.setAlignment( Pos.CENTER );

        glass.getChildren().add( vBox );
        vBox.getChildren().add( hBox );
        hBox.getChildren().add( createDialogSkin() );

        return glass;
    }

    private Node createDialogSkin()
    {
        VBox background = new VBox();
        background.getStyleClass().add( "info-dialog-background" );

        background.getChildren().addAll( createHeader(), createContent() );
        return background;
    }

    private Node createHeader()
    {
        HBox header = new HBox();
        header.getStyleClass().add( "info-dialog-header" );
        header.setAlignment( Pos.CENTER );

        Label headerText = new Label( dialog.getHeader() );

        Pane expander = new Pane();
        HBox.setHgrow( expander, Priority.ALWAYS );

        SVGLabel icon = new SVGLabel();
        icon.setSvg( "info_outline" );
        icon.setScale( 2.0 );

        header.getChildren().addAll( headerText, expander, icon );
        return header;
    }

    private Node createContent()
    {
        VBox content = new VBox();
        content.getStyleClass().add( "info-dialog-content" );

        Label text = new Label( dialog.getContent() );
        text.setWrapText( true );
        text.setTextAlignment( TextAlignment.JUSTIFY );

        ButtonBar buttonBar = new ButtonBar();

        Button ok = new Button( "OK" );
        ok.setDefaultButton( true );
        ok.setOnAction( event -> dialog.ok() );
        ButtonBar.setButtonData( ok, ButtonBar.ButtonData.OK_DONE );
        buttonBar.getButtons().add( ok );

        content.getChildren().addAll( text, buttonBar );
        return content;
    }
}
