package se.simplistics.template4fx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import se.simplistics.template4fx.FXClient;
import se.simplistics.template4fx.fx.control.QuadTabPane;
import se.simplistics.template4fx.util.FXUtils;

import java.io.IOException;

public class MainController
{
    @FXML
    private QuadTabPane pane;

    @FXML
    private RadioMenuItem lightTheme, darkTheme;

    public void initialize()
    {
        pane.addNorthWestTab( newTab( "View 1" ) );
        pane.addNorthWestTab( newTab( "View 2" ) );

        pane.addNorthEastTab( newTab( "View 3" ) );
        pane.addNorthEastTab( newTab( "View 4" ) );

        pane.addSouthWestPane( newTab( "View 5" ) );
        pane.addSouthWestPane( newTab( "View 6" ) );

        pane.addSouthEastPane( newTab( "View 7" ) );
        pane.addSouthEastPane( newTab( "View 8" ) );

        ToggleGroup themeGroup = new ToggleGroup();
        lightTheme.setToggleGroup( themeGroup );
        lightTheme.setSelected( true );

        darkTheme.setToggleGroup( themeGroup );

        themeGroup.selectedToggleProperty().addListener(
            ( arg0, arg1, arg2 ) ->
            {
                if ( arg1 != arg2 )
                {
                    FXClient.getRoot().getStylesheets().clear();

                    if ( arg0.getValue() == lightTheme )
                        FXClient.setStringProperty( "stylesheet", FXClient.getStringProperty( "stylesheet.light" ) );
                    else if ( arg0.getValue() == darkTheme )
                        FXClient.setStringProperty( "stylesheet", FXClient.getStringProperty( "stylesheet.dark" ) );

                    FXClient.getRoot().getStylesheets().add( FXClient.getStringProperty( "stylesheet" ) );
                }
            } );
    }

    public void initiateEventFilter( Scene scene )
    {
        pane.addKeyBindings( scene, KeyCombination.CONTROL_DOWN );
    }

    public void showAppInfo()
    {
        FXUtils.showInfo( FXClient.getString( "application.title" ), FXClient.getString( "application.version" ) );
    }

    // TODO Clean up
    private Tab newTab( String title )
    {
        Tab tab = new Tab();
        tab.setText( title );
        tab.setClosable( true );

        Image image = new Image( "icons/16/folder.png" );
        ImageView imageView = new ImageView();

        imageView.setFitWidth( 16 );
        imageView.setFitHeight( 16 );
        imageView.setImage( image );
        tab.setGraphic( imageView );

        try
        {
            tab.setContent( FXMLLoader.load( getClass().getResource( "/fxml/module.fxml" ), FXClient.getLocale() ) );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }

        return tab;
    }
}
