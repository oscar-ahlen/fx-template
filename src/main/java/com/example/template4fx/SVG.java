package com.example.template4fx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SVG
{
    private static final Properties svgPaths = new Properties();

    static
    {
        try ( InputStream in = SVG.class.getResourceAsStream( "/icons/svg.properties" ) )
        {
            if ( in != null )
            {
                svgPaths.load( in );
            }
        }
        catch ( IOException ignored )
        {

        }
    }

    public static String get( String key )
    {
        return svgPaths.getProperty( key, "" );
    }
}
