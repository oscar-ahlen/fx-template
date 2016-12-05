package se.simplistics.template4fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FormController
{
    private final Image folderImage =
        new Image( getClass().getResourceAsStream( "/icons/16/folder_16dp.png" ) );

    private final Image pdfImage =
        new Image( getClass().getResourceAsStream( "/icons/16/file_pdf_16dp.png" ) );

    private final Image wordImage =
        new Image( getClass().getResourceAsStream( "/icons/16/file_doc_16dp.png" ) );

    @FXML
    private TreeView<String> treeView;

    @FXML
    private ListView<String> listView;

    private ObservableList<String> list;

    public void initialize()
    {
        TreeItem<String> root = new TreeItem<>( "Root", new ImageView( folderImage ) );
        treeView.setRoot( root );

        TreeItem<String> folder1 = addFolder( "0000", root );

        TreeItem<String> subFolder1 = addFolder( "m1", folder1 );
        addFile( "Sub document 1", subFolder1, pdfImage );
        addFile( "Sub document 2", subFolder1, pdfImage );

        TreeItem<String> subFolder2 = addFolder( "m1", folder1 );
        addFile( "Sub document 3", subFolder2, pdfImage );
        addFile( "Sub document 4", subFolder2, pdfImage );

        addFile( "Document 1", folder1, pdfImage );
        addFile( "Document 2", folder1, pdfImage );
        addFile( "Document 3", folder1, pdfImage );

        TreeItem<String> folder2 = addFolder( "0000-working documents", root );
        addFile( "Document 4", folder2, wordImage );
        addFile( "Document 5", folder2, wordImage );
        addFile( "Document 6", folder2, wordImage );

        addFile( "Random document 1", root, wordImage );
        addFile( "Random document 2", root, wordImage );

        list = FXCollections.observableArrayList();
        list.add( "Item 1" );
        list.add( "Item 2" );
        list.add( "Item 3" );
        listView.setItems( list );
    }

    private TreeItem<String> addFolder( String name, TreeItem<String> parent )
    {
        TreeItem<String> folder = new TreeItem<>( name, new ImageView( folderImage ) );
        parent.getChildren().add( folder );
        return folder;
    }

    private void addFile( String name, TreeItem<String> parent, Image image )
    {
        parent.getChildren().add( new TreeItem<>( name, new ImageView( image ) ) );
    }
}
