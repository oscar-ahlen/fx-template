package com.example.template4fx;

import com.example.template4fx.component.Component;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App
    extends Application
{
    private FXContext context;

    @Override
    public void init()
        throws Exception
    {
        try
        {
            context = new FXContext( Settings.load( "settings.json" ) );
        }
        catch ( IOException exc )
        {
            context = new FXContext( Settings.createDefault( "settings.json" ) );
        }
    }

    @Override
    public void start( Stage primaryStage )
        throws Exception
    {
        primaryStage.setTitle( "Template4FX" );

        Component mainComponent = context.Load( "/fxml/RootView.fxml",
                                                ResourceBundle.getBundle( "Template4FX", Locale.ENGLISH ),
                                                context.getSettings().getValue( "theme" ) );

        primaryStage.setScene( new Scene( mainComponent.getRoot() ) );
        primaryStage.setMaximized( true );
        primaryStage.show();
    }

    @Override
    public void stop()
        throws Exception
    {
        context.shutdown();
    }
}
