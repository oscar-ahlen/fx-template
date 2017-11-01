package com.example.template4fx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum SVG
{
    INSTANCE
        {
            @Override
            String getResource()
            {
                return "/icons/svg.properties";
            }
        };

    private final Properties svgPaths = load( getResource() );

    abstract String getResource();

    public String get( String key )
    {
        return svgPaths.getProperty( key, "" );
    }

    private Properties load( String resource )
    {
        try ( InputStream inputStream = SVG.class.getResourceAsStream( resource ) )
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
