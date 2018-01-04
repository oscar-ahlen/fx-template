package com.example.template4fx.component;

import com.example.template4fx.Keys;
import com.example.template4fx.control.dialog.ConfirmDialog;
import com.example.template4fx.control.dialog.DialogEvent;
import com.example.template4fx.control.dialog.ErrorDialog;
import com.example.template4fx.control.dialog.InfoDialog;
import com.example.template4fx.control.dialog.ProgressDialog;
import com.example.template4fx.control.dialog.ValuePicker;
import com.example.template4fx.facade.FileFacade;
import com.example.template4fx.model.File;
import com.example.template4fx.task.ProgressTask;
import com.google.inject.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;

@Singleton
public class ExampleView
    extends MainView
{
    @FXML
    private Parent root;

    @FXML
    private TableView<FileFacade> fileView;

    private final ObservableList<FileFacade> files = FXCollections.observableArrayList();

    @FXML
    private Button valuePickerButton;

    public void initialize()
    {
        setTitle( message( "title.home" ) );
        setSvg( "home" );

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
    }

    @Override
    public Parent getParent()
    {
        return root;
    }

    @Override
    public void handleKeyEvent( KeyEvent event )
    {
        if ( Keys.F5.match( event ) )
        {
            event.consume();
            System.out.println( "Refreshing..." );
        }
    }

    public void showInfoDialog()
    {
        popup( new InfoDialog( "Information Dialog", "Testing the new Info Dialog" ) );
    }

    public void showErrorDialog()
    {
        popup( new ErrorDialog( "Error Dialog", new RuntimeException( "Something went wrong" ) ) );
    }

    public void showConfirmDialog()
    {
        ConfirmDialog dialog = new ConfirmDialog( "Confirm Dialog", "Are you sure this is okay?" );

        dialog.addEventHandler( DialogEvent.SUCCESS, event -> System.out.println( "Ok pressed" ) );
        dialog.addEventHandler( DialogEvent.CANCEL, event -> System.out.println( "Cancel pressed" ) );

        popup( dialog );
    }

    public void showProgressDialog()
    {
        Task task = new ProgressTask( 10 );
        popup( new ProgressDialog( "Task in progress", task ) );
        run( task );
    }

    public void testValuePicker()
    {
        ValuePicker<String> valuePicker =
            new ValuePicker<>( valuePickerButton, FXCollections.observableArrayList( Arrays.asList( "Test 1",
                                                                                                    "Test 2",
                                                                                                    "Test 3",
                                                                                                    "Test 4",
                                                                                                    "Test 5" ) ) );

        popup( valuePicker );
    }
}
