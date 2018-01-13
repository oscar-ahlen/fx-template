package com.example.template4fx.control.notification;

import com.example.template4fx.fx.DialogEvent;
import com.example.template4fx.control.SVGIcon;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class NotificationImpl
    extends HBox
    implements Notification
{
    public NotificationImpl( NotificationType type, String header )
    {
        getStyleClass().addAll( "notification", type.getStyleClass() );

        HBox iconPane = new HBox();
        iconPane.getStyleClass().add( "icon-frame" );

        SVGIcon icon = new SVGIcon( type.getSvg() );
        iconPane.getChildren().add( icon );

        Label headerLabel = new Label( header );
        headerLabel.getStyleClass().add( "notification-header" );

        Pane filler = new Pane();
        HBox.setHgrow( filler, Priority.ALWAYS );

        getChildren().addAll( iconPane, headerLabel, filler );
    }

    @Override
    public Node getNode()
    {
        return this;
    }

    @Override
    public void success()
    {
        fireEvent( DialogEvent.successEvent() );
    }

    @Override
    public void cancel()
    {
        fireEvent( DialogEvent.cancelEvent() );
    }

    public void addSuccessButton( String title )
    {
        Button button = new Button( title );
        button.setOnAction( event -> success() );
        getChildren().add( button );
    }

    public void addCancelButton( String title )
    {
        Button button = new Button( title );
        button.setOnAction( event -> cancel() );
        getChildren().add( button );
    }
}
