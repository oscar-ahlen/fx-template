package com.example.template4fx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Settings
{
    private transient String path;

    private final Map<String, String> values = new HashMap<>();

    public static Settings createDefault( String path )
    {
        Settings settings = new Settings();
        settings.path = path;
        settings.values.put( "theme", "/css/material-design.css" );
        return settings;
    }

    public static Settings load( String path )
        throws IOException
    {
        try ( Reader reader = new FileReader( path ) )
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Settings settings = gson.fromJson( reader, Settings.class );
            settings.path = path;

            return settings;
        }
    }

    public void save()
        throws IOException
    {
        try ( Writer writer = new FileWriter( path ) )
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson( this, writer );
        }
    }

    public String getValue( String key )
    {
        return values.get( key );
    }

    public String getValue( String key, String defaultValue )
    {
        String value = values.get( key );
        return value != null ? value : defaultValue;
    }

    public void setValue( String key, String value )
    {
        values.put( key, value );
    }
}
