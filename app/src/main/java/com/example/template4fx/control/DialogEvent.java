package com.example.template4fx.control;

import javafx.event.Event;
import javafx.event.EventType;

public class DialogEvent
    extends Event
{
    public static final EventType<DialogEvent> SUCCESS = new EventType<>( Event.ANY, "SUCCESS" );

    public static final EventType<DialogEvent> CANCEL = new EventType<>( Event.ANY, "CANCEL" );

    public static DialogEvent successEvent()
    {
        return new DialogEvent( SUCCESS );
    }

    public static DialogEvent cancelEvent()
    {
        return new DialogEvent( CANCEL );
    }

    private DialogEvent( EventType<DialogEvent> eventType )
    {
        super( eventType );
    }
}
