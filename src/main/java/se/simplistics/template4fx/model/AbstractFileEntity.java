package se.simplistics.template4fx.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.nio.file.Path;

public abstract class AbstractFileEntity
    implements FileEntity
{
    public final SimpleStringProperty name = new SimpleStringProperty();

    public final Property<Path> path = new SimpleObjectProperty<>();

    public final SimpleLongProperty size = new SimpleLongProperty();

    public final SimpleStringProperty type = new SimpleStringProperty();

    @Override
    public String getName()
    {
        return name.get();
    }

    @Override
    public Path getPath()
    {
        return path.getValue();
    }

    @Override
    public long getSize()
    {
        return size.get();
    }

    @Override
    public String getType()
    {
        return type.get();
    }

    @Override
    public boolean isDirectory()
    {
        return false;
    }

    @Override
    public boolean isFile()
    {
        return false;
    }

    public SimpleStringProperty nameProperty()
    {
        return name;
    }

    public Property<Path> pathProperty()
    {
        return path;
    }

    public SimpleLongProperty sizeProperty()
    {
        return size;
    }

    public SimpleStringProperty typeProperty()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return name.get();
    }
}
