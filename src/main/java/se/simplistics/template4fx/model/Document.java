package se.simplistics.template4fx.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Collection;
import java.util.Collections;

public class Document
    extends AbstractFileEntity
{
    public final StringProperty mimeType = new SimpleStringProperty();

    public final LongProperty size = new SimpleLongProperty();

    @Override
    public Collection<FileEntity> getChildren()
    {
        return Collections.emptyList();
    }

    public StringProperty mimeTypeProperty()
    {
        return mimeType;
    }

    public LongProperty sizeProperty()
    {
        return size;
    }
}
