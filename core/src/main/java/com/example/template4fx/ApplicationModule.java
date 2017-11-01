package com.example.template4fx;

import com.example.template4fx.service.HttpService;
import com.example.template4fx.service.PostService;
import com.example.template4fx.service.SettingsService;
import com.example.template4fx.service.UserService;
import com.example.template4fx.service.impl.FileSettingsService;
import com.example.template4fx.service.impl.HttpServiceImpl;
import com.example.template4fx.service.impl.PostServiceImpl;
import com.example.template4fx.service.impl.UserServiceImpl;
import com.google.inject.AbstractModule;

public class ApplicationModule
    extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind( ApplicationContext.class ).to( ApplicationContextImpl.class );

        bind( HttpService.class ).to( HttpServiceImpl.class );
        bind( UserService.class ).to( UserServiceImpl.class );
        bind( PostService.class ).to( PostServiceImpl.class );
        bind( SettingsService.class ).to( FileSettingsService.class );
    }
}
