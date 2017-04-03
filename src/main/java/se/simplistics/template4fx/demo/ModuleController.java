package se.simplistics.template4fx.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import se.simplistics.template4fx.demo.model.Person;

public class ModuleController
{
    @FXML
    private TableView<Person> tableView;

    public void initialize()
    {
        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add( new Person( "John", "Doe", "john.doe@example.com" ) );
        people.add( new Person( "Jane", "Doe", "jane.doe@example.com" ) );
        people.add( new Person( "John", "Doe", "john.doe@example.com" ) );
        people.add( new Person( "Jane", "Doe", "jane.doe@example.com" ) );
        people.add( new Person( "John", "Doe", "john.doe@example.com" ) );
        people.add( new Person( "Jane", "Doe", "jane.doe@example.com" ) );
        people.add( new Person( "John", "Doe", "john.doe@example.com" ) );
        people.add( new Person( "Jane", "Doe", "jane.doe@example.com" ) );
        tableView.setItems( people );
    }

    public void showErrorDialog()
    {
        FXUtils.showError( "En example error", new Exception( "Error message" ) );
    }
}
