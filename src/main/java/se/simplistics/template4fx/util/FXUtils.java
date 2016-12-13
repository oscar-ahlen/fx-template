package se.simplistics.template4fx.util;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import se.simplistics.template4fx.FXClient;

public class FXUtils
{
    public static void showInfo( String header, String content )
    {
        Alert alert = customAlert( Alert.AlertType.INFORMATION, FXClient.getString( "alert.title.information" ) );
        alert.setHeaderText( header );
        alert.setContentText( content );
        alert.show();
    }

    public static void showWarning( String header, String content )
    {
        Alert alert = customAlert( Alert.AlertType.WARNING, FXClient.getString( "alert.title.warning" ) );
        alert.setHeaderText( header );
        alert.setContentText( content );
        alert.show();
    }

    public static void showError( String header, String content )
    {
        Alert alert = customAlert( Alert.AlertType.ERROR, FXClient.getString( "alert.title.error" ) );
        alert.setHeaderText( header );
        alert.setContentText( content );
        alert.show();
    }

    private static Alert customAlert( Alert.AlertType type, String title )
    {
        Alert alert = new Alert( type );
        alert.setTitle( title );

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add( FXClient.getStringProperty( "stylesheet" ) );

        return alert;
    }
}
