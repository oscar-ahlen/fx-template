package com.example.template4fx.component;

import com.example.template4fx.control.FXDialog;
import com.example.template4fx.util.HistoryList;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RootView
    extends Component
{
    private final Map<String, MainView> views = new HashMap<>();

    private MainView current;

    private final HistoryList<MainView> recent = new HistoryList<>();

    @FXML
    private Pane navBar;

    @FXML
    private StackPane rootPane, center;

    @FXML
    private Button backward, forward, refresh;

    private final StringProperty title = new SimpleStringProperty();

    public void initialize()
    {
        backward.disableProperty().bind( recent.backwardEnabledProperty().not() );
        forward.disableProperty().bind( recent.forwardEnabledProperty().not() );
    }

    public void setup()
        throws IOException
    {
        loadMainView( "ExampleView", "/fxml/ExampleView.fxml" );
        switchView( "ExampleView" );
    }

    public MainView getView( String name )
    {
        return views.get( name );
    }

    public void exit()
    {
        Platform.exit();
    }

    public void toggleNavBar()
    {
        if ( isActive( navBar ) )
            hide( navBar );
        else
            show( navBar );
    }

    public void backward()
    {
        switchView( recent.backward() );
    }

    public void forward()
    {
        switchView( recent.forward() );
    }

    public void refresh()
    {
        current.refresh();
    }

    public void switchView( String name )
    {
        MainView view = views.get( name );
        switchView( view );
        recent.add( view );
    }

    private void switchView( MainView view )
    {
        if ( current == view )
            return;

        if ( current != null )
        {
            switchNode( center, current.getRoot(), view.getRoot() );
        }
        else
        {
            switchNode( center, null, view.getRoot() );
        }

        current = view;

        rebindProperty( refresh.managedProperty(), current.refreshableProperty() );
        rebindProperty( refresh.visibleProperty(), current.refreshableProperty() );

        rebindProperty( refresh.disableProperty(), current.idleProperty().not() );
    }

    private void loadMainView( String name, String fxml )
        throws IOException
    {
        MainView mainView = (MainView) load( fxml );
        mainView.setRootView( this );
        views.put( name, mainView );
    }

    private void rebindProperty( BooleanProperty source, BooleanProperty target )
    {
        source.unbind();
        source.bind( target );
    }

    private void rebindProperty( BooleanProperty source, BooleanBinding target )
    {
        source.unbind();
        source.bind( target );
    }

    private void rebindProperty( StringProperty source, StringProperty target )
    {
        source.unbind();
        source.bind( target );
    }

    public void showDialog()
    {
        FXDialog dialog = new FXDialog();
        dialog.setOnClose( () -> rootPane.getChildren().remove( dialog ) );
        rootPane.getChildren().add( dialog );
    }

    public String getTitle()
    {
        return title.get();
    }

    public StringProperty titleProperty()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title.set( title );
    }
}
