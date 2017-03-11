package se.simplistics.template4fx.skin;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import se.simplistics.template4fx.control.SearchBox;

public class SearchBoxSkin
    extends SkinBase<SearchBox>
{
    public SearchBoxSkin( SearchBox searchBox )
    {
        super( searchBox );
        getChildren().add( createMasker() );
    }

    private StackPane createMasker()
    {
        VBox vBox = new VBox();
        vBox.getStyleClass().add( "search-box" );

        Label searchBarLabel = new Label();
        searchBarLabel.textProperty().bind( getSkinnable().searchTextProperty() );
        searchBarLabel.getStyleClass().add( "search-box-label" );
        vBox.getChildren().add( searchBarLabel );

        Separator separator = new Separator();
        separator.getStyleClass().add( "search-box-divider" );
        vBox.getChildren().add( separator );

        getSkinnable().getResultView().getStyleClass().add( "search-list" );
        vBox.getChildren().add( getSkinnable().getResultView() );

        Group group = new Group();
        group.getChildren().add( vBox );

        StackPane container = new StackPane();
        container.setAlignment( Pos.CENTER );
        container.getStyleClass().add( "search-box-background" );
        container.getChildren().add( group );

        return container;
    }
}
