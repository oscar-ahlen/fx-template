package com.example.template4fx.model;

public class File
{
    private String name;

    private String format;

    private long size = 0L;

    public File()
    {
    }

    public File( String name, String format, long size )
    {
        this.name = name;
        this.format = format;
        this.size = size;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getFormat()
    {
        return format;
    }

    public void setFormat( String format )
    {
        this.format = format;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize( long size )
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
        return "File{" +
            "name='" + name + '\'' +
            ", format='" + format + '\'' +
            ", size=" + size +
            '}';
    }
}
