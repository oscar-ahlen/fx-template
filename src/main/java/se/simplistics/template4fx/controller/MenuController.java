package se.simplistics.template4fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import se.simplistics.template4fx.FXClient;
import se.simplistics.template4fx.util.FXUtils;

public class MenuController
{
    @FXML
    private RadioMenuItem lightTheme, darkTheme;

    public void initialize()
    {
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

    public void showAppInfo()
    {
        FXUtils.showInfo( "Template4FX", FXClient.getString( "application.version" ) );
    }
}
