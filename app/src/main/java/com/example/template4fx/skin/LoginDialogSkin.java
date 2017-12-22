package com.example.template4fx.skin;

import com.example.template4fx.control.dialog.LoginDialog;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginDialogSkin
    extends DialogSkin<LoginDialog>
{
    public LoginDialogSkin( LoginDialog dialog )
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

        Label usernameLabel = new Label( "Username" );

        TextField username = new TextField();
        username.textProperty().bindBidirectional( getSkinnable().usernameProperty() );
        getSkinnable().setFirst( username );

        Label passwordLabel = new Label( "Password" );

        PasswordField password = new PasswordField();
        password.textProperty().bindBidirectional( getSkinnable().passwordProperty() );

        ButtonBar buttonBar = new ButtonBar();

        Button ok = new Button( "OK" );
        ok.setDefaultButton( true );
        ButtonBar.setButtonData( ok, ButtonBar.ButtonData.OK_DONE );

        Button cancel = new Button( "Cancel" );
        cancel.setOnAction( event -> getSkinnable().cancel() );
        ButtonBar.setButtonData( cancel, ButtonBar.ButtonData.CANCEL_CLOSE );
        getSkinnable().setLast( cancel );

        buttonBar.getButtons().addAll( ok, cancel );

        content.getChildren().addAll( usernameLabel, username, passwordLabel, password, buttonBar );

        return content;
    }
}
