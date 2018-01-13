package com.example.template4fx.fx;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

public interface Popup
{
    Node getNode();

    BooleanProperty closedProperty();

    void handleKeyEvent( KeyEvent event );
}
