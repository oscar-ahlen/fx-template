package com.example.template4fx.component;

import com.example.template4fx.util.FXColor;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class SettingsView
    extends MainView
{
    @FXML
    private ComboBox<String> themeBox;

    @FXML
    private ComboBox<FXColor> colorBox;

    public void initialize()
    {
        idle.set( false );

        colorBox.getItems().addAll( FXColor.COLORS );
        colorBox.getSelectionModel().selectFirst();
    }

    public void save()
    {

    }
}
