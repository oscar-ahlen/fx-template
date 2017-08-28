package com.example.template4fx;

import com.example.template4fx.component.RootView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class App
    extends Application
{
    private FXContext context;

    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void init()
        throws Exception
    {
        FXContextBuilder contextBuilder = new FXContextBuilder();

        contextBuilder.setExecutorService( Executors.newFixedThreadPool( 4 ) );
        contextBuilder.setSettings( initSettings() );

        context = contextBuilder.createFXContext();
    }

    @Override
    public void start( Stage primaryStage )
        throws Exception
    {
        RootView rootView = loadRootView();

        primaryStage.titleProperty().bindBidirectional( rootView.titleProperty() );

        primaryStage.setScene( new Scene( rootView.getRoot() ) );
        primaryStage.setMaximized( true );
        primaryStage.show();
    }

    @Override
    public void stop()
        throws Exception
    {
        context.shutdown();
    }

    private RootView loadRootView()
        throws IOException
    {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/RootView.fxml" ) );
        loader.setResources( ResourceBundle.getBundle( "Template4FX", Locale.ENGLISH ) );

        Parent root = loader.load();
        root.getStylesheets().add( "/css/theme.css" );

        RootView rootView = loader.getController();

        rootView.setRoot( root );
        rootView.setContext( context );
        rootView.setTitle( "Template4FX" );

        rootView.setup();

        return rootView;
    }

    private Settings initSettings()
        throws IOException
    {
        Settings settings;

        try
        {
            settings = Settings.load();
        }
        catch ( IOException exc )
        {
            settings = Settings.createDefault();
            settings.save();
        }

        return settings;
    }
}
