package se.simplistics.template4fx.fx.control;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * A 4-way quadrant TabPane-SplitPane with two master TabPanes at the top and
 */
public class QuadTabPane
    extends SplitPane
{
    private enum Direction
    {
        LEFT, RIGHT, UP, DOWN
    }

    private final SplitPane left, right;

    private final TabPane northWestPane, northEastPane, southWestPane, southEastPane;

    public QuadTabPane()
    {
        left = new SplitPane();
        left.setOrientation( Orientation.VERTICAL );

        right = new SplitPane();
        right.setOrientation( Orientation.VERTICAL );

        getItems().add( left );

        northWestPane = new TabPane();
        initTabPane( northWestPane );
        northEastPane = new TabPane();
        initTabPane( northEastPane );
        southWestPane = new TabPane();
        initTabPane( southWestPane );
        southEastPane = new TabPane();
        initTabPane( southEastPane );

        left.getItems().add( northWestPane );
        right.getItems().add( northEastPane );
    }

    public void initialize()
    {
        setOnKeyPressed( this::handleKeyPressed );
    }

    public void addNorthWestTab( Tab tab )
    {
        tab.setOnClosed( event -> updateSplitPanes() );
        northWestPane.getTabs().add( tab );
    }

    public void addNorthEastTab( Tab tab )
    {
        addChildPane( tab, northEastPane );
    }

    public void addSouthWestPane( Tab tab )
    {
        addChildPane( tab, southWestPane );
    }

    public void addSouthEastPane( Tab tab )
    {
        addChildPane( tab, southEastPane );
    }

    public void update()
    {
        checkAndMerge( northWestPane, southWestPane );
        checkAndMerge( northEastPane, southEastPane );

        if ( northWestPane.getTabs().isEmpty() )
        {
            merge( northWestPane, northEastPane );
            merge( southWestPane, southEastPane );
        }

        updateSplitPanes();
    }

    private void addChildPane( Tab tab, TabPane childPane )
    {
        tab.setOnClosed( event -> update() );
        childPane.getTabs().add( tab );
        updateSplitPanes();
    }

    private void initTabPane( TabPane pane )
    {
        pane.setTabClosingPolicy( TabPane.TabClosingPolicy.ALL_TABS );

        // Values based on template4fx theme
        pane.setMinWidth( 28 );
        pane.setMinHeight( 31 );
    }

    private void handleKeyPressed( KeyEvent event )
    {
        if ( event.isControlDown() )
        {
            switch ( event.getCode() )
            {
                case LEFT:
                    if ( getScene().focusOwnerProperty().get() == northEastPane )
                        transferSelectedTab( northEastPane, Direction.LEFT );
                    else if ( getScene().focusOwnerProperty().get() == southEastPane )
                        transferSelectedTab( southEastPane, Direction.LEFT );
                    break;

                case RIGHT:
                    if ( getScene().focusOwnerProperty().get() == northWestPane )
                        transferSelectedTab( northWestPane, Direction.RIGHT );
                    else if ( getScene().focusOwnerProperty().get() == southWestPane )
                        transferSelectedTab( southWestPane, Direction.RIGHT );
                    break;

                case UP:
                    if ( getScene().focusOwnerProperty().get() == southWestPane )
                        transferSelectedTab( southWestPane, Direction.UP );
                    else if ( getScene().focusOwnerProperty().get() == southEastPane )
                        transferSelectedTab( southEastPane, Direction.UP );
                    break;

                case DOWN:
                    if ( getScene().focusOwnerProperty().get() == northWestPane )
                        transferSelectedTab( northWestPane, Direction.DOWN );
                    else if ( getScene().focusOwnerProperty().get() == northEastPane )
                        transferSelectedTab( northEastPane, Direction.DOWN );
            }
        }
    }

    private void transferSelectedTab( TabPane parent, Direction dir )
    {
        transferTab( parent.getSelectionModel().getSelectedItem(), dir );
    }

    private void transferTab( Tab tab, Direction dir )
    {
        if ( tab == null )
            return;

        TabPane parent = tab.getTabPane();

        if ( parent == null )
            return;

        switch ( dir )
        {
            case LEFT:
                if ( parent == northEastPane )
                    transferTab( tab, parent, northWestPane );
                else if ( parent == southEastPane )
                    transferTab( tab, parent, southWestPane );
                break;

            case RIGHT:
                if ( parent == northWestPane )
                    transferTab( tab, parent, northEastPane );
                else if ( parent == southWestPane )
                    transferTab( tab, parent, southEastPane );
                break;

            case UP:
                if ( parent == southWestPane )
                    transferTab( tab, parent, northWestPane );
                else if ( parent == southEastPane )
                    transferTab( tab, parent, northEastPane );
                break;

            case DOWN:
                if ( parent == northWestPane )
                    transferTab( tab, parent, southWestPane );
                else if ( parent == northEastPane )
                    transferTab( tab, parent, southEastPane );
                break;
        }

        update();
        Platform.runLater( () -> tab.getTabPane().requestFocus() );
    }

    private void transferTab( Tab tab, TabPane source, TabPane dest )
    {
        source.getTabs().remove( tab );
        dest.getTabs().add( tab );
        dest.getSelectionModel().select( tab );
    }

    private void checkAndMerge( TabPane master, TabPane slave )
    {
        if ( master.getTabs().isEmpty() )
            merge( master, slave );
    }

    private void merge( TabPane master, TabPane slave )
    {
        for ( Tab tab : new ArrayList<>( slave.getTabs() ) )
        {
            slave.getTabs().remove( tab );
            master.getTabs().add( tab );
        }

        master.getSelectionModel().selectLast();
    }

    private void updateSplitPanes()
    {
        if ( !southWestPane.getTabs().isEmpty() && left.getItems().size() == 1 )
            left.getItems().add( southWestPane );
        else if ( southWestPane.getTabs().isEmpty() && left.getItems().size() > 1 )
            left.getItems().remove( southWestPane );

        if ( !southEastPane.getTabs().isEmpty() && right.getItems().size() == 1 )
            right.getItems().add( southEastPane );
        else if ( southEastPane.getTabs().isEmpty() && right.getItems().size() > 1 )
            right.getItems().remove( southEastPane );

        if ( !northEastPane.getTabs().isEmpty() && getItems().size() == 1 )
            getItems().add( right );
        if ( northEastPane.getTabs().isEmpty() && getItems().size() > 1 )
            getItems().remove( right );
    }
}
