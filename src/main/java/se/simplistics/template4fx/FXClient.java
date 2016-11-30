package se.simplistics.template4fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class FXClient
    extends Application
{
    public static Properties props;

    public static ResourceBundle locale;

    public static void main( String[] args )
    {
        launch( args );
    }

    /**
     * Tries to parse a property value as a string.
     *
     * @param key the key of the desired property
     * @return the string value of the desired property
     */
    public static String getStringProperty( String key )
    {
        return props.getProperty( key );
    }

    /**
     * Tries to parse a property value as an integer.
     *
     * @param key the key of the desired property
     * @return the integer value of the desired property
     * @throws NumberFormatException if the property can not be parsed as an integer
     */
    public static int getIntProperty( String key )
        throws NumberFormatException
    {
        return Integer.valueOf( props.getProperty( key ) );
    }

    public static String getString( String key )
    {
        return locale.getString( key );
    }

    @Override
    public void init()
        throws Exception
    {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream( "config.properties" ))
        {
            props = new Properties();
            props.load( in );
        }

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
        primaryStage.setScene( new Scene( root, getIntProperty( "window_width" ), getIntProperty( "window_height" ) ) );
        primaryStage.show();
    }

    private void initHeader( Stage primaryStage )
    {
        primaryStage.setTitle( locale.getString( "application.title" ) );
        primaryStage.getIcons().add( new Image( "/icons/16/logo.png" ) );
        primaryStage.getIcons().add( new Image( "/icons/24/logo.png" ) );
        primaryStage.getIcons().add( new Image( "/icons/32/logo.png" ) );
    }
}
