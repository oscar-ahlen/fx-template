package se.simplistics.template4fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
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
import javafx.scene.layout.StackPane;
import se.simplistics.template4fx.FXClient;
import se.simplistics.template4fx.fx.control.QuadTabPane;
import se.simplistics.template4fx.fx.control.SearchBox;
import se.simplistics.template4fx.util.FXUtils;

import java.io.IOException;

public class MainController
{
    @FXML
    private StackPane stackpane;

    @FXML
    private Group popup;

    @FXML
    private SearchBox<SearchObject> searchBox;

    @FXML
    private QuadTabPane pane;

    @FXML
    private RadioMenuItem lightTheme, darkTheme;

    private final ObservableList<SearchObject> searchObjects = FXCollections.observableArrayList();

    public void initialize()
    {
        stackpane.getChildren().remove( popup );

        pane.addTab( newTab( "View 1" ), QuadTabPane.Location.NORTH_WEST );
        pane.addTab( newTab( "View 2" ), QuadTabPane.Location.NORTH_WEST );

        pane.addTab( newTab( "View 3" ), QuadTabPane.Location.NORTH_EAST );
        pane.addTab( newTab( "View 4" ), QuadTabPane.Location.NORTH_EAST );

        pane.addTab( newTab( "View 5" ), QuadTabPane.Location.SOUTH_WEST );
        pane.addTab( newTab( "View 6" ), QuadTabPane.Location.SOUTH_WEST );

        pane.addTab( newTab( "View 7" ), QuadTabPane.Location.SOUTH_EAST );
        pane.addTab( newTab( "View 8" ), QuadTabPane.Location.SOUTH_EAST, true, null );

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

        searchBox.init( searchObjects );

        searchBox.setOnEnter(
            () ->
            {
                SearchObject so = searchBox.getResultView().getSelectionModel().getSelectedItem();
                pane.requestTabFocus( so.getTab() );
                stackpane.getChildren().remove( popup );
            } );

        searchBox.setOnEsc( () -> stackpane.getChildren().remove( popup ) );
    }

    public void initiateEventFilter( Scene scene )
    {
        pane.addKeyBindings( scene, KeyCombination.CONTROL_DOWN );

        scene.addEventFilter( KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            final KeyCombination keyComb = new KeyCodeCombination( KeyCode.E, KeyCombination.CONTROL_DOWN );

            public void handle( KeyEvent event )
            {
                if ( keyComb.match( event ) )
                {
                    if ( stackpane.getChildren().size() > 1 )
                        stackpane.getChildren().remove( popup );
                    else
                    {
                        stackpane.getChildren().add( popup );
                        searchBox.requestFocus();
                    }

                    event.consume();
                }
            }
        } );
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

        searchObjects.add( new SearchObject( tab.getText(), tab ) );
        return tab;
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

        public String getTitle()
        {
            return title;
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
