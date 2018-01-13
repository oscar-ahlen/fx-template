package com.example.template4fx.control.notification;

public class Notifications
{
    public static Notification info( String header, String ok, String cancel )
    {
        NotificationImpl info = new NotificationImpl( NotificationType.INFO, header );
        info.addSuccessButton( ok );
        info.addCancelButton( cancel );
        return info;
    }

    public static Notification success( String header, String close )
    {
        NotificationImpl success = new NotificationImpl( NotificationType.SUCCESS, header );
        success.addCancelButton( close );
        return success;
    }

    public static Notification warning( String header, String close )
    {
        NotificationImpl warning = new NotificationImpl( NotificationType.WARNING, header );
        warning.addCancelButton( close );
        return warning;
    }

    public static Notification error( String header, String close )
    {
        NotificationImpl error = new NotificationImpl( NotificationType.ERROR, header );
        error.addCancelButton( close );
        return error;
    }
}
