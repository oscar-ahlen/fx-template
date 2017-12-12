package com.example.template4fx;

import com.example.template4fx.component.Component;
import com.example.template4fx.component.RootView;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class App
    extends Application
{
    private CoreContext context;

    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void init()
    {
        Injector injector = Guice.createInjector( new CoreModule(), new AppModule() );

        Component.setDefaultControllerFactory( injector::getInstance );

        context = injector.getInstance( CoreContext.class );
    }

    @Override
    public void start( Stage primaryStage )
        throws Exception
    {
        ResourceBundle resources = ResourceBundle.getBundle( "Template4FX", Locale.ENGLISH );
        RootView rootView = Component.load( "/fxml/RootView.fxml", resources );

        primaryStage.titleProperty().bindBidirectional( rootView.titleProperty() );
        primaryStage.setScene( new Scene( rootView.getParent() ) );
        primaryStage.setMaximized( true );
        primaryStage.show();
    }

    @Override
    public void stop()
        throws Exception
    {
        context.close();
    }
}
