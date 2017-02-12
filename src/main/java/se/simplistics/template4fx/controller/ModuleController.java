package se.simplistics.template4fx.controller;

import se.simplistics.template4fx.util.FXUtils;

public class ModuleController
{
    public void showAlertInfo()
    {
        FXUtils.showInfo( "Information header", "This is an information alert dialog" );
    }

    public void showAlertWarning()
    {
        FXUtils.showWarning( "Warning header", "This is a warning alert dialog" );
    }

    public void showAlertError()
    {
        FXUtils.showError( "Error header", "This is an error alert dialog" );
    }
}
