package se.simplistics.template4fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import se.simplistics.template4fx.FXClient;

public class SettingsController
{
    @FXML
    private ComboBox<Setting> comboBox;

    public void initialize()
    {
        ObservableList<Setting> comboBoxList = FXCollections.observableArrayList();

        comboBoxList.add( new Setting( FXClient.getString( "setting.theme.light" ),
                                       FXClient.getStringProperty( "stylesheet.light" ) ) );

        comboBoxList.add( new Setting( FXClient.getString( "setting.theme.dark" ),
                                       FXClient.getStringProperty( "stylesheet.dark" ) ) );

        comboBox.setItems( comboBoxList );
        comboBox.getSelectionModel().select( 0 );
    }

    public void changeTheme()
    {
        FXClient.root.getStylesheets().clear();
        FXClient.root.getStylesheets().add( comboBox.getSelectionModel().getSelectedItem().getValue() );
    }

    private class Setting
    {
        private final String name;

        private final String value;

        public Setting( String name, String value )
        {
            this.name = name;
            this.value = value;
        }

        public String getName()
        {
            return name;
        }

        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return name;
        }
    }
}
