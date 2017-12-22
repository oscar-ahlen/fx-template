package com.example.template4fx.view;

import com.example.template4fx.control.SVGIcon;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class DetailListCellFactory<S extends Displayable>
    implements Callback<ListView<S>, ListCell<S>>
{
    @Override
    public ListCell<S> call( ListView<S> param )
    {
        return new ListCell<S>()
        {
            private final SVGIcon icon = new SVGIcon();

            private final StringProperty title = new SimpleStringProperty();

            private final StringProperty description = new SimpleStringProperty();

            private GridPane content;

            @Override
            protected void updateItem( S item, boolean empty )
            {
                super.updateItem( item, empty );

                if ( empty || item == null )
                {
                    setGraphic( null );
                    setText( null );
                }
                else
                {
                    if ( content == null )
                        content = createContent();

                    icon.getSVG().setContent( item.getContent() );
                    title.set( item.getTitle() );
                    description.set( item.getDescription() );
                    setGraphic( content );
                }
            }

            private GridPane createContent()
            {
                GridPane gridPane = new GridPane();
                gridPane.setHgap( 4 );

                GridPane.setRowIndex( icon, 0 );
                GridPane.setColumnIndex( icon, 0 );
                GridPane.setRowSpan( icon, 2 );

                Label titleLabel = new Label();
                titleLabel.getStyleClass().add( "detail-title" );
                titleLabel.textProperty().bind( title );

                GridPane.setRowIndex( titleLabel, 0 );
                GridPane.setColumnIndex( titleLabel, 1 );

                Label descriptionLabel = new Label();
                descriptionLabel.textProperty().bind( description );

                GridPane.setRowIndex( descriptionLabel, 1 );
                GridPane.setColumnIndex( descriptionLabel, 1 );

                gridPane.getChildren().addAll( icon, titleLabel, descriptionLabel );
                return gridPane;
            }
        };
    }
}
