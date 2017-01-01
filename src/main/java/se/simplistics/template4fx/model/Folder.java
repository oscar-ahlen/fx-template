package se.simplistics.template4fx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public class Folder
    extends AbstractFileEntity
{
    public final ObservableList<FileEntity> children = FXCollections.observableArrayList();

    @Override
    public Collection<FileEntity> getChildren()
    {
        return children;
    }
}
