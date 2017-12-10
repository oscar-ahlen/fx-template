package com.example.template4fx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum SVG
{
    INSTANCE;

    private final Properties svgPaths = load();

    public String get( String key )
    {
        return svgPaths.getProperty( key, "" );
    }

    private Properties load()
    {
        try ( InputStream inputStream = getClass().getResourceAsStream( "/icons/svg.properties" ) )
        {
            Properties properties = new Properties();
            properties.load( inputStream );
            return properties;
        }
        catch ( IOException exc )
        {
            throw new RuntimeException( "Could not initialize SVG resources", exc );
        }
    }
}
