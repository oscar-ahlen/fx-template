package se.simplistics.template4fx.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.simplistics.template4fx.model.Person;

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
    private TreeTableView<Person> treeTableView;

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

        TreeTableColumn<Person, String> nameColumn =
            new TreeTableColumn<>( "Name" );

        nameColumn.setCellValueFactory(
            ( TreeTableColumn.CellDataFeatures<Person, String> param ) ->
                new ReadOnlyStringWrapper( param.getValue().getValue().getFirstName() )
        );

        TreeTableColumn<Person, String> empColumn =
            new TreeTableColumn<>( "Email" );

        empColumn.setCellValueFactory(
            ( TreeTableColumn.CellDataFeatures<Person, String> param ) ->
                new ReadOnlyStringWrapper( param.getValue().getValue().getEmail() )
        );

        treeTableView.getColumns().setAll( nameColumn, empColumn );

        TreeItem<Person> root2 = new TreeItem<>( new Person() );
        treeTableView.setRoot( root2 );
        TreeItem<Person> root3 = new TreeItem<>( new Person() );
        root2.getChildren().add( root3 );

        Person p1 = new Person();
        p1.setFirstName( "Oscar" );
        p1.setEmail( "oscar.ahlen@protonmail.com" );
        root3.getChildren().add( new TreeItem<>( p1 ) );

        Person p2 = new Person();
        p2.setFirstName( "Ludvig" );
        p2.setEmail( "ludvig.ahlen@protonmail.com" );
        root3.getChildren().add( new TreeItem<>( p2 ) );

        TreeItem<Person> root4 = new TreeItem<>( new Person() );
        root2.getChildren().add( root4 );

        Person p3 = new Person();
        p3.setFirstName( "Oscar" );
        p3.setEmail( "oscar.ahlen@protonmail.com" );
        root4.getChildren().add( new TreeItem<>( p3 ) );

        Person p4 = new Person();
        p4.setFirstName( "Ludvig" );
        p4.setEmail( "ludvig.ahlen@protonmail.com" );
        root4.getChildren().add( new TreeItem<>( p4 ) );
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
