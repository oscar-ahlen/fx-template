package com.example.template4fx.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ResourceBundle;

public abstract class Component
{
    private Parent root;

    @FXML
    private ResourceBundle resources;

    protected Component load( String fxml )
        throws IOException
    {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( fxml ) );
        loader.setResources( resources );
        Parent root = loader.load();

        Component component = loader.getController();
        component.setRoot( root );
        return component;
    }

    protected String message( String key )
    {
        return resources.getString( key );
    }

    protected Alert alert( Alert.AlertType type, String header, String content )
    {
        Alert alert = new Alert( type );

        alert.initOwner( root.getScene().getWindow() );
        alert.getDialogPane().getStylesheets().add( "/css/theme.css" );

        alert.setHeaderText( header );
        alert.setContentText( content );
        return alert;
    }

    protected boolean isActive( Node node )
    {
        return node.isVisible() || node.isManaged();
    }

    protected void hide( Node node )
    {
        node.setVisible( false );
        node.setManaged( false );
    }

    protected void show( Node node )
    {
        node.setVisible( true );
        node.setManaged( true );
    }

    protected void switchNode( Pane parent, Node oldNode, Node newNode )
    {
        parent.getChildren().add( newNode );
        parent.getChildren().remove( oldNode );
    }

    public Parent getRoot()
    {
        return root;
    }

    public void setRoot( Parent root )
    {
        this.root = root;
    }

    public ResourceBundle getResources()
    {
        return resources;
    }
}
