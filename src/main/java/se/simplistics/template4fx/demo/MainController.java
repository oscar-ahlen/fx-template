package se.simplistics.template4fx.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import se.simplistics.template4fx.FXClient;
import se.simplistics.template4fx.StyleSheets;
import se.simplistics.template4fx.control.QuadTabPane;
import se.simplistics.template4fx.control.SearchBox;

import java.io.IOException;

public class MainController
{
    @FXML
    private SearchBox<SearchObject> searchBox;

    @FXML
    private QuadTabPane pane;

    @FXML
    private RadioMenuItem lightTheme, darkTheme, modena;

    private final ObservableList<SearchObject> searchObjects = FXCollections.observableArrayList();

    private int counter = 1;

    public void initialize()
        throws IOException
    {
        ToggleGroup themeGroup = new ToggleGroup();
        lightTheme.setToggleGroup( themeGroup );
        lightTheme.setSelected( true );

        darkTheme.setToggleGroup( themeGroup );
        modena.setToggleGroup( themeGroup );

        themeGroup.selectedToggleProperty().addListener(
            ( arg0, arg1, arg2 ) ->
            {
                if ( arg1 != arg2 )
                {
                    FXClient.getRoot().getStylesheets().clear();

                    if ( arg0.getValue() == modena )
                        FXClient.setStyleSheet( "modena" );
                    else if ( arg0.getValue() == lightTheme )
                        FXClient.setStyleSheet( "template4fx-light" );
                    else if ( arg0.getValue() == darkTheme )
                        FXClient.setStyleSheet( "template4fx-dark" );

                    if ( !FXClient.getStyleSheet().equals( "modena" ) )
                        FXClient.getRoot().getStylesheets().add( StyleSheets.getTheme( FXClient.getStyleSheet() ) );
                }
            } );

        searchBox.init( searchObjects );

        searchBox.setOnEnter(
            () ->
            {
                SearchObject searchObject = searchBox.getResultView().getSelectionModel().getSelectedItem();

                if ( searchObject != null )
                    pane.requestTabFocus( searchObject.getTab() );
            } );

        searchBox.setOnFocusedLost( () -> searchBox.setVisible( false ) );
    }

    public void initiateEventFilter( Scene scene )
    {
        pane.addKeyBindings( scene, KeyCombination.CONTROL_DOWN );

        scene.addEventFilter( KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            final KeyCombination keyComb = new KeyCodeCombination( KeyCode.E, KeyCombination.CONTROL_DOWN );

            @Override
            public void handle( KeyEvent event )
            {
                if ( keyComb.match( event ) )
                {
                    searchBox.setVisible( !searchBox.isVisible() );

                    if ( searchBox.isVisible() )
                        searchBox.requestFocus();

                    event.consume();
                }
            }
        } );
    }

    public void newMasterTab()
        throws IOException
    {
        Tab tab = new Tab( String.format( "Master Tab %d", counter++ ),
                           FXMLLoader.load( getClass().getResource( "/fxml/module.fxml" ) ) );

        tab.setGraphic( loadImageView( "icons/16/information.png" ) );

        SearchObject searchObject = new SearchObject( tab.getText(), tab );
        searchObjects.add( searchObject );

        pane.addMasterTab( tab, true, () -> searchObjects.remove( searchObject ) );
    }

    public void newSlaveTab()
        throws IOException
    {
        Tab tab = new Tab( String.format( "Child Tab %d", counter++ ),
                           FXMLLoader.load( getClass().getResource( "/fxml/module.fxml" ) ) );

        tab.setGraphic( loadImageView( "icons/16/information.png" ) );

        SearchObject searchObject = new SearchObject( tab.getText(), tab );
        searchObjects.add( searchObject );

        pane.addChildTab( tab, false, () -> searchObjects.remove( searchObject ) );
    }

    public void collapseAll()
    {
        pane.collapseAll();
    }

    public void showAppInfo()
    {
        FXUtils.showInfo( "Template4FX", "Version 0.2.0" );
    }

    private ImageView loadImageView( String resource )
    {
        ImageView imageView = new ImageView( new Image( resource ) );
        imageView.setFitWidth( 16 );
        imageView.setFitHeight( 16 );
        return imageView;
    }

    private class SearchObject
    {
        private final String title;

        private final Tab tab;

        public SearchObject( String title, Tab tab )
        {
            this.title = title;
            this.tab = tab;
        }

        public Tab getTab()
        {
            return tab;
        }

        @Override
        public String toString()
        {
            return title;
        }
    }
}
