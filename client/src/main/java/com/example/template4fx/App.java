package com.example.template4fx;

import com.example.template4fx.component.RootView;
import com.example.template4fx.fx.FXContext;
import com.example.template4fx.fx.FXContextBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class App
    extends Application
{
    private final Logger log = Logger.getLogger( getClass() );

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
        primaryStage.setTitle( "Template4FX" );

        RootView rootView = (RootView) context.Load( "/fxml/RootView.fxml",
                                                     ResourceBundle.getBundle( "Template4FX", Locale.ENGLISH ),
                                                     "/css/theme.css" );

        rootView.setup();
        rootView.setTitle( "Template4FX" );
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
