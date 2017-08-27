package com.example.template4fx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Settings
{
    private static final Path DEFAULT_PATH =
        Paths.get( System.getProperty( "user.home" ) ).resolve( "AppData/Local/Template4FX/Template4FX.json" );

    private transient String path;

    private final Map<String, String> values = new HashMap<>();

    public static Settings createDefault()
    {
        return createDefault( DEFAULT_PATH.toString() );
    }

    public static Settings createDefault( String path )
    {
        Settings settings = new Settings();
        settings.path = path;
        return settings;
    }

    public static Settings load()
        throws IOException
    {
        return load( DEFAULT_PATH.toString() );
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
        Files.createDirectories( DEFAULT_PATH.getParent() );

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

    public Path getPath( String key )
    {
        return Paths.get( values.get( key ), "" );
    }
}
