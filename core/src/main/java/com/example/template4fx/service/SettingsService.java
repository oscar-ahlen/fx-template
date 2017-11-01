package com.example.template4fx.service;

import java.io.IOException;

public interface SettingsService
{
    String getValue( String key );

    void setValue( String key, String value );

    void store()
        throws IOException;
}
