package se.simplistics.template4fx.fx.control;

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
        southEastPane = new TabPane();
        initTabPane( southEastPane );

        left.getItems().add( northWestPane );
        right.getItems().add( northEastPane );
    }

    public void addKeyBindings( Scene scene, KeyCombination.Modifier modifier )
    {
        addKeyBinding( scene, KeyCode.LEFT, modifier, this::moveTabLeft );
        addKeyBinding( scene, KeyCode.RIGHT, modifier, this::moveTabRight );
        addKeyBinding( scene, KeyCode.UP, modifier, this::moveTabUp );
        addKeyBinding( scene, KeyCode.DOWN, modifier, this::moveTabDown );
    }

    public void addNorthWestTab( Tab tab )
    {
        addTab( tab, northWestPane );
    }

    public void addNorthEastTab( Tab tab )
    {
        addTab( tab, northEastPane );
    }

    public void addSouthWestPane( Tab tab )
    {
        addTab( tab, southWestPane );
    }

    public void addSouthEastPane( Tab tab )
    {
        addTab( tab, southEastPane );
    }

    public void moveTabLeft()
    {
        TabPane parent = getFocusedPane( getScene().focusOwnerProperty().get() );

        if ( parent == northEastPane )
            moveSelectedTab( northEastPane, Direction.LEFT );
        else if ( parent == southEastPane )
            moveSelectedTab( southEastPane, Direction.LEFT );
    }

    public void moveTabRight()
    {
        TabPane parent = getFocusedPane( getScene().focusOwnerProperty().get() );

        if ( parent == northWestPane )
            moveSelectedTab( northWestPane, Direction.RIGHT );
        else if ( parent == southWestPane )
            moveSelectedTab( southWestPane, Direction.RIGHT );
    }

    public void moveTabUp()
    {
        TabPane parent = getFocusedPane( getScene().focusOwnerProperty().get() );

        if ( parent == southWestPane )
            moveSelectedTab( southWestPane, Direction.UP );
        else if ( parent == southEastPane )
            moveSelectedTab( southEastPane, Direction.UP );
    }

    public void moveTabDown()
    {
        TabPane parent = getFocusedPane( getScene().focusOwnerProperty().get() );

        if ( parent == northWestPane )
            moveSelectedTab( northWestPane, Direction.DOWN );
        else if ( parent == northEastPane )
            moveSelectedTab( northEastPane, Direction.DOWN );
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
        pane.setMinWidth( 28 );
        pane.setMinHeight( 31 );

        tabPaneSet.add( pane );
    }

    private void addKeyBinding( Scene scene, KeyCode keyCode, KeyCombination.Modifier modifier, Runnable runnable )
    {
        scene.addEventFilter( KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            final KeyCombination keyComb = new KeyCodeCombination( keyCode, modifier );

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

    private void addTab( Tab tab, TabPane childPane )
    {
        tab.setOnClosed( event -> update() );
        childPane.getTabs().add( tab );
        updateSplitPanes();
    }

    private TabPane getFocusedPane( Node node )
    {
        while ( node != null )
        {
            if ( tabPaneSet.contains( node ) )
                return (TabPane) node;

            node = node.getParent();
        }

        return null;
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

        boolean tabIsFocused = getScene().focusOwnerProperty().get() == parent;

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

        if ( tabIsFocused )
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
