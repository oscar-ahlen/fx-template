package se.simplistics.template4fx.model;

import java.nio.file.Path;

public interface FileEntity
{
    String getName();

    Path getPath();

    long getSize();

    String getType();

    boolean isDirectory();

    boolean isFile();
}
