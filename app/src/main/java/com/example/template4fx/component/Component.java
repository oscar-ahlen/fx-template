package com.example.template4fx.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ResourceBundle;

public abstract class Component
{
    private static Callback<Class<?>, Object> CONTROLLER_FACTORY;

    public static void setDefaultControllerFactory( Callback<Class<?>, Object> controllerFactory )
    {
        CONTROLLER_FACTORY = controllerFactory;
    }

    public static <T> T load( String fxml, ResourceBundle resources )
        throws IOException
    {
        FXMLLoader loader = new FXMLLoader( Component.class.getResource( fxml ), resources );
        loader.setControllerFactory( CONTROLLER_FACTORY );
        loader.load();

        return loader.getController();
    }

    @FXML
    private ResourceBundle resources;

    protected <T> T load( String fxml )
        throws IOException
    {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( fxml ), resources );
        loader.setControllerFactory( CONTROLLER_FACTORY );
        loader.load();

        return loader.getController();
    }

    public abstract Parent getParent();

    protected String message( String key )
    {
        return resources.getString( key );
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
}
