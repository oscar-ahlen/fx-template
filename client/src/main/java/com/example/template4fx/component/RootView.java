package com.example.template4fx.component;

import com.example.template4fx.FXContext;
import com.example.template4fx.Keys;
import com.example.template4fx.util.HistoryList;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RootView
    extends Component
{
    private final Map<View, MainView> views = new HashMap<>();

    private final HistoryList<MainView> recent = new HistoryList<>();

    private MainView current;

    private FXContext context;

    @FXML
    private Pane navBar;

    @FXML
    private StackPane rootPane, center;

    @FXML
    private Button backward, forward, refresh;

    private final StringProperty title = new SimpleStringProperty();

    private boolean showsDialog = false;

    public void initialize()
    {
        backward.disableProperty().bind( recent.backwardEnabledProperty().not() );
        forward.disableProperty().bind( recent.forwardEnabledProperty().not() );

        rootPane.addEventFilter( KeyEvent.KEY_PRESSED, new RootViewEventHandler() );
    }

    public void setup()
        throws IOException
    {
        loadMainView( View.ExampleView, "/fxml/ExampleView.fxml" );
        loadMainView( View.SettingsView, "/fxml/SettingsView.fxml" );
        switchView( View.ExampleView );
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

    public void showExampleView()
    {
        switchView( View.ExampleView );
        toggleNavBar();
    }

    public void showSettingsView()
    {
        switchView( View.SettingsView );
        toggleNavBar();
    }

    public void showDialog( Control control )
    {
        control.visibleProperty().addListener(
            ( observable, oldValue, newValue ) -> {
                if ( !newValue && oldValue )
                {
                    rootPane.getChildren().remove( control );
                    showsDialog = false;
                }
            } );

        rootPane.getChildren().add( control );
        showsDialog = true;
    }

    public void switchView( View name )
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

    private void loadMainView( View name, String fxml )
        throws IOException
    {
        MainView mainView = (MainView) load( fxml );
        mainView.setRootView( this );
        mainView.setContext( context );
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

    public void setContext( FXContext context )
    {
        this.context = context;
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

    private class RootViewEventHandler
        implements EventHandler<KeyEvent>
    {
        public void handle( KeyEvent event )
        {
            if ( showsDialog )
                return;

            if ( Keys.ALT_LEFT.match( event ) )
            {
                backward();
                event.consume();
            }
            else if ( Keys.ALT_RIGHT.match( event ) )
            {
                forward();
                event.consume();
            }
            else if ( Keys.CTRL_E.match( event ) )
            {
                toggleNavBar();
                event.consume();
            }
            else
            {
                current.handleKeyEvent( event );
            }
        }
    }
}
