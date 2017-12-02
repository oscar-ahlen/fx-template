package com.example.template4fx;

import com.example.template4fx.component.RootView;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App
    extends Application
{
    private ApplicationContext context;

    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void init()
        throws Exception
    {
        Injector injector = Guice.createInjector( new ApplicationModule() );
        context = injector.getInstance( ApplicationContext.class );
    }

    @Override
    public void start( Stage primaryStage )
        throws Exception
    {
        RootView rootView = loadRootView();

        primaryStage.titleProperty().bindBidirectional( rootView.titleProperty() );

        primaryStage.setScene( new Scene( rootView.getNode() ) );
        primaryStage.setMaximized( true );
        primaryStage.show();
    }

    @Override
    public void stop()
        throws Exception
    {
        context.close();
    }

    private RootView loadRootView()
        throws IOException
    {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/RootView.fxml" ) );
        loader.setResources( ResourceBundle.getBundle( "Template4FX", Locale.ENGLISH ) );

        Parent root = loader.load();
        root.getStylesheets().add( "/css/theme.css" );

        RootView rootView = loader.getController();

        rootView.setNode( root );
        rootView.setContext( context );
        rootView.setup();

        return rootView;
    }
}
