package com.example.template4fx.control.notification;

import com.example.template4fx.SVG;

public enum NotificationType
{
    INFO( "info", "help_outline" ),
    SUCCESS( "success", "info_outline" ),
    WARNING( "warning", "warning" ),
    ERROR( "error", "error_outline" );

    private final String styleClass;

    private final String icon;

    NotificationType( String styleClass, String icon )
    {
        this.styleClass = styleClass;
        this.icon = icon;
    }

    public String getStyleClass()
    {
        return styleClass;
    }

    public String getSvg()
    {
        return SVG.INSTANCE.get( icon );
    }
}
