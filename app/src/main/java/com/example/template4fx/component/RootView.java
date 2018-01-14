package com.example.template4fx.component;

import com.example.template4fx.Keys;
import com.example.template4fx.control.SVGLabel;
import com.example.template4fx.fx.Popup;
import com.example.template4fx.util.HistoryList;
import com.google.inject.Singleton;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RootView
    extends Component
{
    private static final String TITLE = "Template4FX";

    private final Map<NavBarView, MainView> views = new HashMap<>();

    private final HistoryList<MainView> recent = new HistoryList<>();

    private MainView current;

    @FXML
    private Pane root, expandedNavBar, collapsedNavBar;

    @FXML
    private BorderPane viewPane;

    @FXML
    private SVGLabel header;

    @FXML
    private Button backward, forward;

    private Popup popup;

    public void initialize()
        throws IOException
    {
        setTitle( TITLE );

        backward.disableProperty().bind( recent.backwardEnabledProperty().not() );
        forward.disableProperty().bind( recent.forwardEnabledProperty().not() );

        root.addEventFilter( KeyEvent.KEY_PRESSED, new RootEventHandler() );

        toggleNavBar();

        views.put( NavBarView.ExampleView, load( "/fxml/ExampleView.fxml" ) );
        views.put( NavBarView.DialogView, load( "/fxml/DialogView.fxml" ) );
        views.put( NavBarView.UserView, load( "/fxml/UserView.fxml" ) );
        views.put( NavBarView.SettingsView, load( "/fxml/SettingsView.fxml" ) );

        switchView( NavBarView.ExampleView );
    }

    @Override
    public Parent getParent()
    {
        return root;
    }

    public void exit()
    {
        Platform.exit();
    }

    public void toggleNavBar()
    {
        if ( isActive( expandedNavBar ) )
        {
            hide( expandedNavBar );
            show( collapsedNavBar );
        }
        else
        {
            hide( collapsedNavBar );
            show( expandedNavBar );
        }
    }

    public void backward()
    {
        switchView( recent.backward() );
    }

    public void forward()
    {
        switchView( recent.forward() );
    }

    public void showExampleView()
    {
        switchView( NavBarView.ExampleView );
    }

    public void showDialogView()
    {
        switchView( NavBarView.DialogView );
    }

    public void showUserView()
    {
        switchView( NavBarView.UserView );
    }

    public void showSettingsView()
    {
        switchView( NavBarView.SettingsView );
    }

    public void popup( Popup popup )
    {
        popup.closedProperty().addListener( ( observable, oldValue, newValue ) -> {
            if ( newValue && !oldValue )
            {
                root.getChildren().remove( popup.getNode() );
                this.popup = null;
            }
        } );

        root.getChildren().add( popup.getNode() );
        this.popup = popup;
    }

    private void switchView( NavBarView name )
    {
        MainView view = views.get( name );
        switchView( view );
        recent.add( view );
    }

    private void switchView( MainView view )
    {
        if ( current == view )
            return;

        current = view;

        viewPane.setCenter( current.getParent() );

        rebindProperty( header.textProperty(), current.titleProperty() );
        rebindProperty( header.svgProperty(), current.svgProperty() );
    }

    private final StringProperty title = new SimpleStringProperty();

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

    private class RootEventHandler
        implements EventHandler<KeyEvent>
    {
        @Override
        public void handle( KeyEvent event )
        {
            if ( popup != null )
            {
                popup.handleKeyEvent( event );
            }
            else if ( Keys.ALT_LEFT.match( event ) )
            {
                event.consume();
                backward();
            }
            else if ( Keys.ALT_RIGHT.match( event ) )
            {
                event.consume();
                forward();
            }
            else if ( Keys.CTRL_E.match( event ) )
            {
                event.consume();
            }
            else
            {
                current.handleKeyEvent( event );
            }
        }
    }

    private enum NavBarView
    {
        ExampleView,
        DialogView,
        UserView,
        SettingsView,
    }
}
