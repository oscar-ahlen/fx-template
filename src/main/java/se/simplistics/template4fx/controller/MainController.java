package se.simplistics.template4fx.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

public class MainController
{
    @FXML
    private StackPane stackPane;

    @FXML
    private Parent view1, view2, view3, view4, view5;

    @FXML
    private ToggleGroup startMenuGroup;

    public void initialize()
    {
        startMenuGroup.getToggles().get( 0 ).setSelected( true );

        startMenuGroup.selectedToggleProperty().addListener(
            ( observable, oldValue, newValue ) ->
            {
                if ( ( newValue == null ) )
                {
                    startMenuGroup.selectToggle( oldValue );
                }
            } );
    }

    public void selectView1()
    {
        selectView( view1 );
    }

    public void selectView2()
    {
        selectView( view2 );
    }

    public void selectView3()
    {
        selectView( view3 );
    }

    public void selectView4()
    {
    }

    public void selectView5()
    {
        selectView( view5 );
    }

    private void selectView( Parent parent )
    {
        stackPane.getChildren().clear();
        stackPane.getChildren().add( parent );
    }
}
