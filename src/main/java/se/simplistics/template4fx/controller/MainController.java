package se.simplistics.template4fx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.simplistics.template4fx.FXClient;
import se.simplistics.template4fx.fx.control.QuadTabPane;

import java.io.IOException;

public class MainController
{
    @FXML
    private QuadTabPane pane;

    public void initialize()
    {
        pane.initialize();

        pane.addWestTab( newTab( "View 1" ) );
        pane.addWestTab( newTab( "View 2" ) );
        pane.addWestTab( newTab( "View 3" ) );
        pane.addWestTab( newTab( "View 4" ) );

        pane.addEastTab( newTab( "View 5" ) );
        pane.addEastTab( newTab( "View 6" ) );
        pane.addEastTab( newTab( "View 7" ) );
        pane.addEastTab( newTab( "View 8" ) );
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
