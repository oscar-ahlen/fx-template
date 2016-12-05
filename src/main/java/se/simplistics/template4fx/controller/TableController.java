package se.simplistics.template4fx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.simplistics.template4fx.FXClient;
import se.simplistics.template4fx.model.Person;
import se.simplistics.template4fx.util.FXUtils;

import java.io.IOException;

public class TableController
{
    @FXML
    private TabPane tabPane;

    public void initialize()
    {
        try
        {
            addTab( "Tab 1" );
            addTab( "Tab 2" );
            addTab( "Tab 3" );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }

    public void addTab()
    {
        try
        {
            addTab( "New Folder" );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }

    public void alertInfo()
    {
        FXUtils.showInfo( "Information header", "This is an information alert dialog" );
    }

    public void alertWarning()
    {
        FXUtils.showWarning( "Warning header", "This is a warning alert dialog" );
    }

    public void alertError()
    {
        FXUtils.showError( "Error header", "This is an error alert dialog" );
    }

    private void addTab( String title )
        throws IOException
    {
        Tab tab = new Tab();
        tab.setText( title );

        Image image = new Image( "icons/24/ic_folder_open_black_24dp.png" );
        ImageView imageView = new ImageView();
        imageView.setOpacity( 0.5 );
        imageView.setFitWidth( 24 );
        imageView.setFitHeight( 24 );
        imageView.setImage( image );
        tab.setGraphic( imageView );

        ScrollPane scrollPane = FXMLLoader.load( getClass().getResource( "/fxml/fragment/table_fragment.fxml" ),
                                                 FXClient.locale );
        tab.setContent( scrollPane );
        ( (TableView<Person>) scrollPane.getContent() ).getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        tabPane.getTabs().add( tab );
    }
}
