package com.example.template4fx.component;

import com.example.template4fx.facade.FileFacade;
import com.example.template4fx.model.File;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.shape.SVGPath;

public class FileView
    extends MainView
{
    @FXML
    private TableView<FileFacade> fileView;

    @FXML
    private TableColumn<FileFacade, FileFacade> fileNameColumn;

    private final ObservableList<FileFacade> files = FXCollections.observableArrayList();

    public void initialize()
    {
        idle.set( false );

        fileView.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        fileView.setItems( files );
        files.add( new FileFacade( new File( "Document 1", "docx", 10 ) ) );
        files.add( new FileFacade( new File( "Document 2", "docx", 50 ) ) );
        files.add( new FileFacade( new File( "Document 3", "pdf", 100 ) ) );
        files.add( new FileFacade( new File( "Document 4", "pdf", 150 ) ) );
        files.add( new FileFacade( new File( "Document 5", "xlsx", 200 ) ) );
        files.add( new FileFacade( new File( "Document 6", "xlsx", 250 ) ) );
        files.add( new FileFacade( new File( "Document 7", "pptx", 300 ) ) );
        files.add( new FileFacade( new File( "Document 8", "pptx", 500 ) ) );
        files.add( new FileFacade( new File( "Folder 1", "folder", 0 ) ) );
        files.add( new FileFacade( new File( "Folder 2", "folder", 0 ) ) );

        fileNameColumn.setCellValueFactory( v -> new SimpleObjectProperty<>( v.getValue() ) );

        fileNameColumn.setCellFactory( callback -> new TableCell<FileFacade, FileFacade>()
        {
            private SVGPath icon;

            @Override
            protected void updateItem( FileFacade item, boolean empty )
            {
                super.updateItem( item, empty );

                if ( empty || item == null )
                {
                    setGraphic( null );
                    setText( null );
                }
                else
                {
                    if ( icon == null )
                    {
                        icon = new SVGPath();
                        icon.fillProperty().bind( textFillProperty() );
                    }

                    icon.setContent( item.getSVGIcon() );
                    setGraphic( icon );
                    setText( item.getName() );
                }
            }
        } );
    }
}
