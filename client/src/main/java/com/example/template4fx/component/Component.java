package com.example.template4fx.component;

import com.example.template4fx.fx.FXContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

public abstract class Component
{
    private Parent root;

    @FXML
    private ResourceBundle resources;

    private FXContext context;

    protected Component load( String fxml )
        throws IOException
    {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( fxml ) );
        loader.setResources( resources );
        Parent root = loader.load();

        Component component = loader.getController();
        component.setRoot( root );
        component.setContext( context );
        return component;
    }

    protected String message( String key )
    {
        return resources.getString( key );
    }

    protected ExecutorService executorService()
    {
        return context.getExecutorService();
    }

    protected String setting( String key )
    {
        return context.getSettings().getValue( key );
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

    public FXContext getContext()
    {
        return context;
    }

    public void setContext( FXContext context )
    {
        this.context = context;
    }

    private static GridPane createTextArea( String content )
    {
        TextArea textArea = new TextArea( content );
        textArea.setEditable( false );
        textArea.setWrapText( true );

        textArea.setMaxWidth( Double.MAX_VALUE );
        textArea.setMaxHeight( Double.MAX_VALUE );
        GridPane.setVgrow( textArea, Priority.ALWAYS );
        GridPane.setHgrow( textArea, Priority.ALWAYS );

        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth( Double.MAX_VALUE );
        gridPane.add( textArea, 0, 0 );

        return gridPane;
    }
}
