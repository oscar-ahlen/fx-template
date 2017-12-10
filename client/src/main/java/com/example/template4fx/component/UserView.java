package com.example.template4fx.component;

import com.example.template4fx.control.dialog.ErrorDialog;
import com.example.template4fx.facade.UserFacade;
import com.example.template4fx.model.User;
import com.example.template4fx.service.UserService;
import com.example.template4fx.view.SVGTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

import java.util.Collection;

public class UserView
    extends MainView
{
    @FXML
    private TableView<UserFacade> userView;

    @FXML
    private TableColumn<UserFacade, String> userNameColumn;

    private final ObservableList<UserFacade> users = FXCollections.observableArrayList();

    public void initialize()
    {
        setTitle( message( "title.user" ) );
        setSvg( "person" );

        userView.setItems( users );
        userNameColumn.setCellFactory( callback -> new SVGTableCell<>() );
    }

    @Override
    public void handleKeyEvent( KeyEvent event )
    {

    }

    public void refresh()
    {
        run( new UserTask() );
    }

    private class UserTask
        extends Task<Collection<User>>
    {
        @Override
        protected Collection<User> call()
            throws Exception
        {
            return service( UserService.class ).getUsers();
        }

        @Override
        protected void succeeded()
        {
            users.clear();
            getValue().forEach( user -> users.add( new UserFacade( user ) ) );
        }

        @Override
        protected void failed()
        {
            popup( new ErrorDialog( "Error Dialog", getException() ) );
        }
    }
}
