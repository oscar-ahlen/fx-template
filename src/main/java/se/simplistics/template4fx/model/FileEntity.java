package se.simplistics.template4fx.model;

import java.nio.file.Path;
import java.util.Collection;

public interface FileEntity
{
    String getName();

    Path getPath();

    FileEntity getParent();

    Collection<FileEntity> getChildren();
}
