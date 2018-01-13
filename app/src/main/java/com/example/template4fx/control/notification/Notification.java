package com.example.template4fx.control.notification;

import javafx.scene.Node;

public interface Notification
{
    Node getNode();

    void success();

    void cancel();
}
