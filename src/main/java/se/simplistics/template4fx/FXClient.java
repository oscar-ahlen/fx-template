package se.simplistics.template4fx;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class FXClient
    extends Application
{
    public static ResourceBundle locale;

    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void init()
        throws Exception
    {
        locale = ResourceBundle.getBundle( "localization.template4j", new Locale( "en", "EN" ) );
    }

    @Override
    public void start( Stage primaryStage )
        throws Exception
    {
        primaryStage.setTitle( locale.getString( "title" ) );

        // Init main scene
        // Scene scene = new Scene( null, 800, 600 );
        // primaryStage.setScene( scene );
        primaryStage.show();
    }
}
