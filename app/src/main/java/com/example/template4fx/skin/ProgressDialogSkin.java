package com.example.template4fx.skin;

import com.example.template4fx.control.SVGLabel;
import com.example.template4fx.control.dialog.ProgressDialog;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ProgressDialogSkin
    extends OverlaySkin<ProgressDialog>
{
    public ProgressDialogSkin( ProgressDialog dialog )
    {
        super( dialog );
        createOverlay( createDialogSkin() );
    }

    private Node createDialogSkin()
    {
        VBox background = new VBox();
        background.getStyleClass().add( "progress-dialog-background" );

        background.getChildren().addAll( createHeader(), createContent() );
        return background;
    }

    private Node createHeader()
    {
        HBox header = new HBox();
        header.getStyleClass().add( "progress-dialog-header" );
        header.setAlignment( Pos.CENTER );

        Label headerText = new Label();
        headerText.textProperty().bind( getSkinnable().headerProperty() );

        Pane expander = new Pane();
        HBox.setHgrow( expander, Priority.ALWAYS );

        SVGLabel icon = new SVGLabel();
        icon.setSvg( "timelapse" );
        icon.setScale( 2.0 );

        header.getChildren().addAll( headerText, expander, icon );
        return header;
    }

    private Node createContent()
    {
        VBox content = new VBox();
        content.setAlignment( Pos.CENTER );
        content.getStyleClass().add( "progress-dialog-content" );

        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind( getSkinnable().getTask().progressProperty() );
        progressBar.setMaxWidth( Double.MAX_VALUE );

        Label status = new Label();
        status.textProperty().bind( getSkinnable().getTask().messageProperty() );
        status.setWrapText( true );

        ButtonBar buttonBar = new ButtonBar();

        Button ok = new Button( "Close" );
        ok.setDefaultButton( true );
        ok.setOnAction( event -> getSkinnable().close() );

        ok.disableProperty().bind( getSkinnable().getTask().runningProperty() );

        ButtonBar.setButtonData( ok, ButtonBar.ButtonData.OK_DONE );
        buttonBar.getButtons().add( ok );

        content.getChildren().addAll( progressBar, status, buttonBar );
        return content;
    }
}
