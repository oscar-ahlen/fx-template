package se.simplistics.template4fx.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.Skin;
import se.simplistics.template4fx.skin.SearchBoxSkin;

public class SearchBox<T>
    extends Control
{
    private final StringProperty searchText = new SimpleStringProperty();

    private final StringBuilder builder = new StringBuilder();

    private final ListView<T> resultView = new ListView<>();

    private FilteredList<T> filteredData;

    private Runnable enterAction;

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
                if ( resultView.getSelectionModel().getSelectedItem() != null )
                {
                    setVisible( false );

                    if ( enterAction != null )
                        enterAction.run();
                }
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
                    setVisible( false );
                    enterAction.run();
                }
                else if ( letter == 27 ) // Esc
                {
                    setVisible( false );
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
                    case TAB:
                        event.consume();
                        break;
                }
            } );
    }

    public void setOnEnter( Runnable enterAction )
    {
        this.enterAction = enterAction;
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

    public void toggle()
    {
        setVisible( !isVisible() );

        if ( isVisible() )
            requestFocus();
    }

    public ListView<T> getResultView()
    {
        return resultView;
    }

    private void update()
    {
        searchText.setValue( builder.toString() );

        filteredData.setPredicate(
            builder.length() == 0 ?
                s -> true :
                s -> s.toString().toLowerCase().contains( builder.toString().toLowerCase() ) );

        resultView.getSelectionModel().selectFirst();
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new SearchBoxSkin( this );
    }

    public String getSearchText()
    {
        return searchText.get();
    }

    public StringProperty searchTextProperty()
    {
        return searchText;
    }

    public void setSearchText( String searchText )
    {
        this.searchText.set( searchText );
    }
}
