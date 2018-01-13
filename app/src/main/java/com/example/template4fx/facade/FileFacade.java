package com.example.template4fx.facade;

import com.example.template4fx.SVG;
import com.example.template4fx.fx.Displayable;
import com.example.template4fx.model.File;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileFacade
    implements FXFacade<File>, Displayable
{
    private static final Map<String, String> ICONS = new HashMap<>();

    static
    {
        ICONS.put( "docx", "file_word" );
        ICONS.put( "pdf", "file_pdf" );
        ICONS.put( "xlsx", "file_excel" );
        ICONS.put( "pptx", "file_powerpoint" );
        ICONS.put( "folder", "folder" );
    }

    private final File file;

    public FileFacade( File file )
    {
        this.file = file;
        refresh();
    }

    @Override
    public File get()
    {
        return file;
    }

    @Override
    public void refresh()
    {
        if ( file != null )
        {
            name.set( file.getName() );
            format.set( file.getFormat() );
            size.set( file.getSize() );
        }
    }

    private final StringProperty name = new SimpleStringProperty();

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name.set( name );
    }

    private final StringProperty format = new SimpleStringProperty();

    public String getFormat()
    {
        return format.get();
    }

    public StringProperty formatProperty()
    {
        return format;
    }

    public void setFormat( String format )
    {
        this.format.set( format );
    }

    private final LongProperty size = new SimpleLongProperty();

    public long getSize()
    {
        return size.get();
    }

    public LongProperty sizeProperty()
    {
        return size;
    }

    public void setSize( long size )
    {
        this.size.set( size );
    }

    @Override
    public String getTitle()
    {
        return name.get();
    }

    @Override
    public String getDescription()
    {
        return null;
    }

    @Override
    public String getContent()
    {
        return SVG.INSTANCE.get( ICONS.get( format.get() ) );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        FileFacade that = (FileFacade) o;
        return Objects.equals( file, that.file );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( file );
    }
}
