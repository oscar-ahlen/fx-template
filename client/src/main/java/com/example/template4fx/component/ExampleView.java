package com.example.template4fx.component;

import com.example.template4fx.control.dialog.ConfirmDialog;
import com.example.template4fx.control.dialog.ErrorDialog;
import com.example.template4fx.control.dialog.InfoDialog;
import com.example.template4fx.facade.FileFacade;
import com.example.template4fx.model.File;
import com.example.template4fx.view.SVGTableCell;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExampleView
    extends MainView
{
    @FXML
    private TableView<FileFacade> fileView;

    @FXML
    private TableColumn<FileFacade, FileFacade> fileNameColumn;

    private final ObservableList<FileFacade> files = FXCollections.observableArrayList();

    public void initialize()
    {
        fileView.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        fileView.setItems( files );
        files.add( new FileFacade( new File( "Folder 1", "folder", 0 ) ) );
        files.add( new FileFacade( new File( "Folder 2", "folder", 0 ) ) );
        files.add( new FileFacade( new File( "Word Document 1", "docx", 1000 ) ) );
        files.add( new FileFacade( new File( "Word Document 2", "docx", 2000 ) ) );
        files.add( new FileFacade( new File( "PDF Document 1", "pdf", 3000 ) ) );
        files.add( new FileFacade( new File( "PDF Document 2", "pdf", 4000 ) ) );
        files.add( new FileFacade( new File( "Excel Document 1", "xlsx", 5000 ) ) );
        files.add( new FileFacade( new File( "Excel Document 2", "xlsx", 6000 ) ) );
        files.add( new FileFacade( new File( "Powerpoint Document 1", "pptx", 7000 ) ) );
        files.add( new FileFacade( new File( "Powerpoint Document 2", "pptx", 8000 ) ) );

        fileNameColumn.setCellValueFactory( v -> new SimpleObjectProperty<>( v.getValue() ) );
        fileNameColumn.setCellFactory( callback -> new SVGTableCell<>() );
    }

    @Override
    public void refresh()
    {
        // Not to be implemented
    }

    public void showInfoDialog()
    {
        showDialog( new InfoDialog( "Information Dialog", "Testing the new Info Dialog" ) );
    }

    public void showErrorDialog()
    {
        showDialog( new ErrorDialog( "Error Dialog", new RuntimeException( "Something went wrong" ) ) );
    }

    public void showConfirmDialog()
    {
        ConfirmDialog dialog = new ConfirmDialog( "Confirm Dialog", "Are you sure this is okay?" );
        dialog.setOnOK( () -> System.out.println( "Ok pressed" ) );
        dialog.setOnCancel( () -> System.out.println( "Cancel pressed" ) );
        showDialog( dialog );
    }
}
