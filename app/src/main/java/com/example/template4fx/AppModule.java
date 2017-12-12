package com.example.template4fx;

import com.example.template4fx.component.ExampleView;
import com.example.template4fx.component.RootView;
import com.example.template4fx.component.SettingsView;
import com.example.template4fx.component.UserView;
import com.google.inject.AbstractModule;

public class AppModule
    extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind( RootView.class );
        bind( ExampleView.class );
        bind( UserView.class );
        bind( SettingsView.class );
    }
}
