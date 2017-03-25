package se.simplistics.template4fx.control;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A 4-way quadrant TabPane-SplitPane with a top left master pane and 3 dynamic child panes.
 */
public class QuadTabPane
    extends SplitPane
{
    public enum Location
    {
        NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST
    }

    private enum Direction
    {
        LEFT, RIGHT, UP, DOWN
    }

    private final SplitPane left, right;

    private final TabPane northWestPane, northEastPane, southWestPane, southEastPane;

    private final Set<Node> tabPaneSet = new HashSet<>();

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
        //southWestPane.setPadding( new Insets( 8, 0, 0, 0 ) );
        southEastPane = new TabPane();
        initTabPane( southEastPane );
        //southEastPane.setPadding( new Insets( 8, 0, 0, 0 ) );

        left.getItems().add( northWestPane );
        right.getItems().add( northEastPane );
    }

    public void addKeyBindings( Scene scene, KeyCombination.Modifier modifier )
    {
        addKeyBinding( scene, KeyCode.LEFT, modifier, this::moveTabLeft );
        addKeyBinding( scene, KeyCode.RIGHT, modifier, this::moveTabRight );
        addKeyBinding( scene, KeyCode.UP, modifier, this::moveTabUp );
        addKeyBinding( scene, KeyCode.DOWN, modifier, this::moveTabDown );

        addKeyBinding( scene, KeyCode.HOME, modifier,
                       () -> getFocusedPane( getScene().getFocusOwner() ).requestFocus() );

        addKeyBinding( scene, KeyCode.F4, modifier,
                       () ->
                       {
                           TabPane focusedPane = getFocusedPane( getScene().getFocusOwner() );
                           Tab tab = focusedPane.getSelectionModel().getSelectedItem();

                           if ( focusedPane.getTabs().remove( tab ) )
                           {
                               tab.getOnClosed().handle( null );
                               focusedPane.requestFocus();
                           }
                       } );
    }

    /**
     * Adds a tab to this QuadTabPane at specified location. This is equivalent to:
     * {@link #addTab(Tab, Location, boolean, Runnable)} with arguments (tab, location, false, null}
     *
     * @param tab      the tab to be added
     * @param location where the tab should be located, i.e. NW, NE, SW, SE
     */
    public void addTab( Tab tab, Location location )
    {
        addTab( tab, location, false, null );
    }

    /**
     * Adds a tab to this QuadTabPane at specified location.
     *
     * @param tab      the tab to be added
     * @param location where the tab should be located, i.e. NW, NE, SW, SE
     * @param focus    true if the newly added tab should be selected and focused after the operation, false otherwise
     * @param runnable optional action to be performed after the tab is closed, can be null
     */
    public void addTab( Tab tab, Location location, boolean focus, Runnable runnable )
    {
        switch ( location )
        {
            case NORTH_WEST:
                addTab( tab, northWestPane, focus, runnable );
                break;
            case NORTH_EAST:
                addTab( tab, northEastPane, focus, runnable );
                break;
            case SOUTH_WEST:
                addTab( tab, southWestPane, focus, runnable );
                break;
            case SOUTH_EAST:
                addTab( tab, southEastPane, focus, runnable );
                break;
        }
    }

    /**
     * Adds a 'master' tab to this QuadTabPane and will always be located at NW or NE depending on which side is focused.
     *
     * @param tab      the tab to be added
     * @param focus    true if the newly added tab should be selected and focused after the operation, false otherwise
     * @param runnable optional action to be performed after the tab is closed, can be null
     */
    public void addMasterTab( Tab tab, boolean focus, Runnable runnable )
    {
        TabPane current = getFocusedPane( getScene().getFocusOwner() );

        if ( current == southWestPane )
            addTab( tab, northWestPane, focus, runnable );
        else if ( current == southEastPane )
            addTab( tab, northEastPane, focus, runnable );
        else
            addTab( tab, current, focus, runnable );
    }

    /**
     * Adds a 'child' tab to this QuadTabPane and will always be located at SW or SE depending on which side is focused.
     *
     * @param tab      the tab to be added
     * @param focus    true if the newly added tab should be selected and focused after the operation, false otherwise
     * @param runnable optional action to be performed after the tab is closed, can be null
     */
    public void addChildTab( Tab tab, boolean focus, Runnable runnable )
    {
        TabPane current = getFocusedPane( getScene().getFocusOwner() );

        if ( current == northWestPane )
            addTab( tab, southWestPane, focus, runnable );
        else if ( current == northEastPane )
            addTab( tab, southEastPane, focus, runnable );
        else
            addTab( tab, current, focus, runnable );
    }

    public void moveTabLeft()
    {
        moveSelectedTab( getFocusedPane( getScene().getFocusOwner() ), Direction.LEFT );
    }

    public void moveTabRight()
    {
        moveSelectedTab( getFocusedPane( getScene().getFocusOwner() ), Direction.RIGHT );
    }

    public void moveTabUp()
    {
        TabPane parent = getFocusedPane( getScene().getFocusOwner() );

        if ( parent == southWestPane || parent == southEastPane )
            moveSelectedTab( parent, Direction.UP );
    }

    public void moveTabDown()
    {
        TabPane parent = getFocusedPane( getScene().getFocusOwner() );

        if ( parent == northWestPane || parent == northEastPane )
            moveSelectedTab( parent, Direction.DOWN );
    }

    public void requestTabFocus( Tab tab )
    {
        TabPane targetPane = null;

        if ( northWestPane.getTabs().contains( tab ) )
            targetPane = northWestPane;
        else if ( northEastPane.getTabs().contains( tab ) )
            targetPane = northEastPane;
        else if ( southWestPane.getTabs().contains( tab ) )
            targetPane = southWestPane;
        else if ( southEastPane.getTabs().contains( tab ) )
            targetPane = southEastPane;

        if ( targetPane != null )
        {
            targetPane.getSelectionModel().select( tab );
            targetPane.requestFocus();
        }
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

    private void initTabPane( TabPane pane )
    {
        pane.setTabClosingPolicy( TabPane.TabClosingPolicy.ALL_TABS );

        // Values based on template4fx theme
        pane.setMinWidth( 24 );
        pane.setMinHeight( 30 );

        tabPaneSet.add( pane );
    }

    private void addKeyBinding( Scene scene, KeyCode keyCode, KeyCombination.Modifier modifier, Runnable runnable )
    {
        scene.addEventFilter( KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            final KeyCombination keyComb = new KeyCodeCombination( keyCode, modifier );

            @Override
            public void handle( KeyEvent event )
            {
                if ( keyComb.match( event ) )
                {
                    runnable.run();
                    event.consume();
                }
            }
        } );
    }

    private void addTab( Tab tab, TabPane childPane, boolean focus, Runnable runnable )
    {
        tab.setOnClosed(
            event ->
            {
                update();

                if ( runnable != null )
                    runnable.run();
            } );

        childPane.getTabs().add( tab );
        update();

        if ( focus )
        {
            childPane.getSelectionModel().select( tab );
            Platform.runLater( childPane::requestFocus );
        }
    }

    private TabPane getFocusedPane( Node node )
    {
        while ( node != null )
        {
            if ( tabPaneSet.contains( node ) )
                return (TabPane) node;

            node = node.getParent();
        }

        return northWestPane;
    }

    private void moveSelectedTab( TabPane parent, Direction dir )
    {
        moveTab( parent.getSelectionModel().getSelectedItem(), dir );
    }

    private void moveTab( Tab tab, Direction dir )
    {
        if ( tab == null )
            return;

        TabPane parent = tab.getTabPane();

        if ( parent == null )
            return;

        boolean tabIsFocused = getScene().getFocusOwner() == parent;

        int index = parent.getSelectionModel().getSelectedIndex();
        boolean internalMove = ( index > 0 && dir == Direction.LEFT )
            || ( index < parent.getTabs().size() - 1 && dir == Direction.RIGHT );

        switch ( dir )
        {
            case LEFT:
                if ( internalMove )
                    moveTabInternally( parent, index, -1 );
                else if ( parent == northEastPane )
                    transferTab( tab, parent, northWestPane );
                else if ( parent == southEastPane )
                    transferTab( tab, parent, southWestPane );
                break;

            case RIGHT:
                if ( internalMove )
                    moveTabInternally( parent, index, 1 );
                else if ( parent == northWestPane )
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

        if ( !internalMove )
            update();

        if ( tabIsFocused )
            Platform.runLater( () -> tab.getTabPane().requestFocus() );
    }

    private void moveTabInternally( TabPane parent, int index, int offset )
    {
        Tab tab = parent.getTabs().remove( index );
        parent.getTabs().add( index + offset, tab );
        parent.getSelectionModel().select( tab );
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
