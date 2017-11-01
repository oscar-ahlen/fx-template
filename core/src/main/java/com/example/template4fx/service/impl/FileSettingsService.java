package com.example.template4fx.service.impl;

import com.example.template4fx.model.Settings;
import com.example.template4fx.service.SettingsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Singleton;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Singleton
public class FileSettingsService
    implements SettingsService
{
    private final Path defaultPath = Paths.get( System.getProperty( "user.home" ) )
                                          .resolve( ".template4fx/Template4FX.json" );

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Settings settings;

    public FileSettingsService()
        throws IOException
    {
        try
        {
            settings = load();
        }
        catch ( IOException exc )
        {
            settings = new Settings();
            store();
        }
    }

    @Override
    public String getValue( String key )
    {
        return settings.getValues().get( key );
    }

    @Override
    public void setValue( String key, String value )
    {
        settings.getValues().put( key, value );
    }

    @Override
    public synchronized void store()
        throws IOException
    {
        Files.createDirectories( defaultPath.getParent() );

        try ( Writer writer = new FileWriter( defaultPath.toFile() ) )
        {
            gson.toJson( this, writer );
        }
    }

    private Settings load()
        throws IOException
    {
        try ( Reader reader = new FileReader( defaultPath.toFile() ) )
        {
            return gson.fromJson( reader, Settings.class );
        }
    }
}
