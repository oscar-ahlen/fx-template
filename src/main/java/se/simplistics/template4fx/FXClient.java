package se.simplistics.template4fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        initHeader( primaryStage );

        // Init main scene
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/main.fxml" ) );
        loader.setResources( locale );
        Parent root = loader.load();
        primaryStage.setScene( new Scene( root, 800, 600 ) );
        primaryStage.show();
    }

    private void initHeader( Stage primaryStage )
    {
        primaryStage.setTitle( locale.getString( "title" ) );
        primaryStage.getIcons().add( new Image( "/icons/16/brick.png" ) );
        primaryStage.getIcons().add( new Image( "/icons/32/brick.png" ) );
    }
}
