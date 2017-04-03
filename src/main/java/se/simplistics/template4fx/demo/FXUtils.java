package se.simplistics.template4fx.demo;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import se.simplistics.template4fx.StyleSheets;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FXUtils
{
    public static void showInfo( String header, String content )
    {
        Alert alert = customAlert( Alert.AlertType.INFORMATION );
        alert.setHeaderText( header );
        alert.setContentText( content );
        alert.showAndWait();
    }

    public static void showWarning( String header, String content )
    {
        Alert alert = customAlert( Alert.AlertType.WARNING );
        alert.setHeaderText( header );
        alert.setContentText( content );
        alert.showAndWait();
    }

    public static void showError( String header, Throwable t )
    {
        Alert alert = customAlert( Alert.AlertType.ERROR );
        alert.setHeaderText( header );
        alert.setContentText( t.getLocalizedMessage() );

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter( sw );
        t.printStackTrace( pw );
        pw.close();

        alert.getDialogPane().setExpandableContent( createTextArea( sw.toString() ) );
        alert.showAndWait();
    }

    private static Alert customAlert( Alert.AlertType type )
    {
        Alert alert = new Alert( type );
        alert.initOwner( FXClient.getRoot().getScene().getWindow() );
        DialogPane dialogPane = alert.getDialogPane();

        if ( !FXClient.getStyleSheet().equals( "modena" ) )
            dialogPane.getStylesheets().add( StyleSheets.getTheme( FXClient.getStyleSheet() ) );

        return alert;
    }

    private static GridPane createTextArea( String content )
    {
        TextArea textArea = new TextArea( content );
        textArea.setEditable( false );
        textArea.setWrapText( true );

        textArea.setMaxWidth( Double.MAX_VALUE );
        textArea.setMaxHeight( Double.MAX_VALUE );
        GridPane.setVgrow( textArea, Priority.ALWAYS );
        GridPane.setHgrow( textArea, Priority.ALWAYS );

        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth( Double.MAX_VALUE );
        gridPane.add( textArea, 0, 0 );

        return gridPane;
    }
}
