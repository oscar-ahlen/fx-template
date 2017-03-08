package se.simplistics.template4fx.fx.control;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class SearchBox<T>
    extends VBox
{
    private final Label searchBarLabel = new Label();

    private final StringBuilder builder = new StringBuilder();

    private final ListView<T> resultView = new ListView<>();

    private FilteredList<T> filteredData;

    private Runnable enterAction, escAction;

    public SearchBox()
    {
        super();
        getStyleClass().add( "search-box" );

        searchBarLabel.setStyle( "-fx-padding: 4 8 4 8;" );
        getChildren().add( searchBarLabel );

        Separator separator = new Separator();
        separator.getStyleClass().add( "search-box-divider" );
        getChildren().add( separator );

        resultView.getStyleClass().add( "search-list" );
        getChildren().add( resultView );
    }

    @Override
    public void requestFocus()
    {
        resultView.requestFocus();
        builder.setLength( 0 );
        update();
    }

    public void init( ObservableList<T> items )
    {
        filteredData = new FilteredList<>( items, s -> true );
        resultView.setItems( filteredData );

        resultView.setOnMouseClicked(
            click ->
            {
                if ( enterAction != null )
                    enterAction.run();
            } );

        resultView.setOnKeyTyped(
            event ->
            {
                int letter = event.getCharacter().charAt( 0 );

                if ( letter >= 32 ) // Space
                {
                    builder.append( event.getCharacter() );
                    update();
                }
                else if ( letter == 8 && builder.length() > 0 ) // Backspace
                {
                    builder.setLength( builder.length() - 1 );
                    update();
                }
                else if ( letter == 13 && enterAction != null )  // Enter
                {
                    enterAction.run();
                }
                else if ( letter == 27 && escAction != null ) // Esc
                {
                    escAction.run();
                }

                event.consume();
            } );

        resultView.setOnKeyPressed(
            event ->
            {
                switch ( event.getCode() )
                {
                    case LEFT:
                        event.consume();
                        break;
                    case RIGHT:
                        event.consume();
                        break;
                }
            } );
    }

    public void setOnEnter( Runnable enterAction )
    {
        this.enterAction = enterAction;
    }

    public void setOnEsc( Runnable escAction )
    {
        this.escAction = escAction;
    }

    public void setOnFocusedLost( Runnable focusedLostAction )
    {
        resultView.focusedProperty().addListener(
            ( arg0, arg1, arg2 ) ->
            {
                if ( arg1 && !arg2 )
                    focusedLostAction.run();
            } );
    }

    public ListView<T> getResultView()
    {
        return resultView;
    }

    private void update()
    {
        searchBarLabel.setText( builder.toString() );

        filteredData.setPredicate(
            builder.length() == 0 ?
                s -> true :
                s -> s.toString().toLowerCase().contains( builder.toString().toLowerCase() ) );

        resultView.getSelectionModel().selectFirst();
    }
}
