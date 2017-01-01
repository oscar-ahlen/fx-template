package se.simplistics.template4fx.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.nio.file.Path;

public abstract class AbstractFileEntity
    implements FileEntity
{
    public final StringProperty name = new SimpleStringProperty();

    public final Property<Path> path = new SimpleObjectProperty<>();

    public final Property<FileEntity> parent = new SimpleObjectProperty<>();

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
    public FileEntity getParent()
    {
        return parent.getValue();
    }

    public SimpleStringProperty nameProperty()
    {
        return nameProperty();
    }

    public Property<Path> pathProperty()
    {
        return path;
    }

    public Property<FileEntity> parentProperty()
    {
        return parent;
    }

    @Override
    public String toString()
    {
        return name.get();
    }
}
