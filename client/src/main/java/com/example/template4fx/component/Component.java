package com.example.template4fx.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ResourceBundle;

public abstract class Component
{
    private Parent node;

    @FXML
    private ResourceBundle resources;

    protected Component load( String fxml )
        throws IOException
    {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( fxml ) );
        loader.setResources( resources );
        Parent root = loader.load();

        Component component = loader.getController();
        component.setNode( root );
        return component;
    }

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

    public Parent getNode()
    {
        return node;
    }

    public void setNode( Parent node )
    {
        this.node = node;
    }

    public ResourceBundle getResources()
    {
        return resources;
    }
}
