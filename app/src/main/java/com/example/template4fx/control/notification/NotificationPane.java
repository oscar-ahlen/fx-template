package com.example.template4fx.control.notification;

import com.example.template4fx.Keys;
import com.example.template4fx.fx.DialogEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class NotificationPane
    extends AnchorPane
{
    private static final double HEIGHT = 40;

    private Notification current;

    public boolean hasNotification()
    {
        return getChildren().size() > 1;
    }

    public void handleEvent( KeyEvent event )
    {
        if ( Keys.ENTER.match( event ) )
        {
            event.consume();
            current.success();
        }
        else if ( Keys.ESCAPE.match( event ) )
        {
            event.consume();
            current.cancel();
        }
    }

    public void notification( Notification notification )
    {
        remove( current );
        current = notification;

        Node node = notification.getNode();

        node.addEventHandler( DialogEvent.SUCCESS, event -> getChildren().remove( node ) );
        node.addEventHandler( DialogEvent.CANCEL, event -> getChildren().remove( node ) );

        AnchorPane.setTopAnchor( node, 0d );
        AnchorPane.setRightAnchor( node, 0d );
        AnchorPane.setLeftAnchor( node, 0d );

        getChildren().add( node );

        Rectangle clip = new Rectangle( Double.MAX_VALUE, HEIGHT );

        clip.translateYProperty().set( HEIGHT );
        node.setClip( clip );
        node.translateYProperty().set( -HEIGHT );

        animation( node, clip ).play();
    }

    private void remove( Notification notification )
    {
        if ( notification != null )
            getChildren().remove( notification.getNode() );
    }

    private Timeline animation( Node node, Rectangle clip )
    {
        Timeline timeline = new Timeline();

        timeline.setCycleCount( 1 );
        timeline.setAutoReverse( true );
        timeline.setOnFinished( event -> node.setClip( null ) );

        final KeyValue kv1 = new KeyValue( clip.translateYProperty(), 0 );
        final KeyValue kv2 = new KeyValue( node.translateYProperty(), 0 );

        timeline.getKeyFrames().add( new KeyFrame( Duration.millis( 200 ), kv1, kv2 ) );

        return timeline;
    }
}
