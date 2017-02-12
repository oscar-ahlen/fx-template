package se.simplistics.template4fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class FXClient
    extends Application
{
    private static Properties props;

    private static ResourceBundle locale;

    private static Parent root;

    public static void main( String[] args )
    {
        launch( args );
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

        locale = ResourceBundle.getBundle( "localization.template4fx", new Locale( "en", "EN" ) );
    }

    @Override
    public void start( Stage primaryStage )
        throws Exception
    {
        initHeader( primaryStage );

        // Init main scene
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/main.fxml" ) );
        loader.setResources( locale );

        root = loader.load();
        root.getStylesheets().add( getStringProperty( "stylesheet" ) );

        primaryStage.setScene( new Scene( root, getIntProperty( "window.width" ), getIntProperty( "window.height" ) ) );
        primaryStage.show();
    }

    private void initHeader( Stage primaryStage )
    {
        primaryStage.setTitle( locale.getString( "application.title" ) );
        // primaryStage.getIcons().add( new Image( "/icons/16/logo.png" ) );
        // primaryStage.getIcons().add( new Image( "/icons/24/logo.png" ) );
        // primaryStage.getIcons().add( new Image( "/icons/32/logo.png" ) );
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

    /**
     * Sets a property of given key with given value
     *
     * @param key   the key of the desired property
     * @param value the string value of the desired property
     */
    public static void setStringProperty( String key, String value )
    {
        props.setProperty( key, value );
    }

    public static String getString( String key )
    {
        return locale.getString( key );
    }

    /**
     * Returns the currently loaded resource bundle locale of the application
     *
     * @return the resource bundle of the application
     */
    public static ResourceBundle getLocale()
    {
        return locale;
    }

    /**
     * Returns the parent node of the main application window
     *
     * @return the main application window node
     */
    public static Parent getRoot()
    {
        return root;
    }
}
