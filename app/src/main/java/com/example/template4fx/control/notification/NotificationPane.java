package com.example.template4fx.control.notification;

import com.example.template4fx.fx.DialogEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class NotificationPane
    extends AnchorPane
{
    private static final double HEIGHT = 40;

    public void setMainNode( Node node )
    {
        AnchorPane.setTopAnchor( node, 0d );
        AnchorPane.setRightAnchor( node, 0d );
        AnchorPane.setBottomAnchor( node, 0d );
        AnchorPane.setLeftAnchor( node, 0d );

        if ( getChildren().isEmpty() )
            getChildren().add( node );
        else
            getChildren().set( 0, node );
    }

    public void notification( Notification notification )
    {
        if ( getChildren().size() > 1 )
            getChildren().remove( 1 );

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
