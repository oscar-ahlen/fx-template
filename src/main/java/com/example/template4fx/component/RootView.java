package com.example.template4fx.component;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RootView
    extends Component
{
    private final Map<String, MainView> views = new HashMap<>();

    @FXML
    private Pane sideBar;

    @FXML
    private StackPane center;

    @FXML
    private Button refresh;

    private MainView current;

    public void initialize()
        throws IOException
    {
        views.put( "ContactView", (MainView) load( "/fxml/ContactView.fxml" ) );
        views.put( "FileView", (MainView) load( "/fxml/FileView.fxml" ) );

        center.getChildren().add( views.get( "ContactView" ).getRoot() );
        current = views.get( "ContactView" );
    }

    public void showContactView()
    {
        switchView( views.get( "ContactView" ) );
    }

    public void showFileView()
    {
        switchView( views.get( "FileView" ) );
    }

    public void exit()
    {
        Platform.exit();
    }

    public void toggleSideBar()
    {
        if ( isActive( sideBar ) )
            hide( sideBar );
        else
            show( sideBar );
    }

    protected void switchView( MainView view )
    {
        if ( current == view )
            return;

        switchNode( center, current.getRoot(), view.getRoot() );
        current = view;

        refresh.disableProperty().unbind();
        refresh.disableProperty().bind( current.idleProperty().not() );
    }

    public void test()
    {
        context.getExecutorService().submit( new Task<Integer>()
        {
            @Override
            protected Integer call()
                throws Exception
            {
                int foo = 0;

                for ( int i = 0; i < 10; i++ )
                    foo++;

                return foo;
            }

            @Override
            protected void succeeded()
            {
                System.out.println( getValue() );
            }
        } );
    }
}
