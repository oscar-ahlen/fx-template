package com.example.template4fx;

import javafx.scene.paint.Paint;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SVG
{
    public static Paint BASE = Paint.valueOf( "#0288D1" );

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
        catch ( IOException exc )
        {
            System.err.println( exc.getMessage() );
        }
    }

    public static String get( String key )
    {
        return svgPaths.getProperty( key, "" );
    }
}
