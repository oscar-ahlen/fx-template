package se.simplistics.template4fx.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
        new Image( getClass().getResourceAsStream( "/icons/16/folder.png" ) );

    private final Image pdfImage =
        new Image( getClass().getResourceAsStream( "/icons/16/file_extension_pdf.png" ) );

    private final Image wordImage =
        new Image( getClass().getResourceAsStream( "/icons/16/file_extension_doc.png" ) );

    @FXML
    private TreeView<String> treeView;

    @FXML
    private TreeTableView<Person> treeTableView;

    @FXML
    private ListView<String> listView;

    private ObservableList<String> list;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ComboBox<String> textBox;

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

        treeView.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        list = FXCollections.observableArrayList();
        list.add( "Item 1" );
        list.add( "Item 2" );
        list.add( "Item 3" );
        listView.setItems( list );

        TreeTableColumn<Person, String> nameColumn =
            new TreeTableColumn<>( "Name" );

        nameColumn.setCellValueFactory(
            ( TreeTableColumn.CellDataFeatures<Person, String> param ) ->
                new ReadOnlyStringWrapper( param.getValue().getValue().firstName.get() )
        );

        TreeTableColumn<Person, String> empColumn =
            new TreeTableColumn<>( "Email" );

        empColumn.setCellValueFactory(
            ( TreeTableColumn.CellDataFeatures<Person, String> param ) ->
                new ReadOnlyStringWrapper( param.getValue().getValue().email.get() )
        );

        treeTableView.getColumns().setAll( nameColumn, empColumn );

        TreeItem<Person> root2 = new TreeItem<>( new Person() );
        treeTableView.setRoot( root2 );
        TreeItem<Person> root3 = new TreeItem<>( new Person() );
        root2.getChildren().add( root3 );

        root3.getChildren().add( new TreeItem<>( new Person( "Jacob", "Smith", "jacob.smith@example.com" ) ) );
        root3.getChildren().add(
            new TreeItem<>( new Person( "Isabella", "Johnson", "isabella.johnson@example.com" ) ) );
        root3.getChildren().add( new TreeItem<>( new Person( "Ethan", "Williams", "ethan.williams@example.com" ) ) );
        root3.getChildren().add( new TreeItem<>( new Person( "Emma", "Jones", "emma.jones@example.com" ) ) );
        root3.getChildren().add( new TreeItem<>( new Person( "Michael", "Brown", "michael.brown@example.com" ) ) );

        TreeItem<Person> root4 = new TreeItem<>( new Person() );
        root2.getChildren().add( root4 );

        Person p3 = new Person();
        p3.firstName.set( "Oscar" );
        p3.email.set( "oscar.ahlen@protonmail.com" );
        root4.getChildren().add( new TreeItem<>( p3 ) );

        Person p4 = new Person();
        p4.firstName.set( "Ludvig" );
        p4.email.set( "ludvig.ahlen@protonmail.com" );
        root4.getChildren().add( new TreeItem<>( p4 ) );

        ObservableList<String> comboBoxList = FXCollections.observableArrayList();
        comboBoxList.add( "Test 1" );
        comboBoxList.add( "Test 2" );
        comboBoxList.add( "Test 3" );
        comboBox.setItems( comboBoxList );
        textBox.setItems( comboBoxList );
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
