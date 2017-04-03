package se.simplistics.template4fx.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.simplistics.template4fx.StyleSheets;

public class FXClient
    extends Application
{
    private static Parent root;

    private static String styleSheet = "template4fx-light";

    public static void main( String[] args )
    {
        launch( args );
    }

    @Override
    public void start( Stage primaryStage )
        throws Exception
    {
        primaryStage.setTitle( "Template4FX" );

        // Init main scene
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/fxml/main.fxml" ) );
        root = loader.load();
        root.getStylesheets().add( StyleSheets.getTheme( styleSheet ) );

        primaryStage.setScene( new Scene( root, 800, 600 ) );
        primaryStage.show();
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

    public static String getStyleSheet()
    {
        return styleSheet;
    }

    public static void setStyleSheet( String styleSheet )
    {
        FXClient.styleSheet = styleSheet;
    }
}
