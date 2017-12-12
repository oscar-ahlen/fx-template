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
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoreModule
    extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind( CoreContext.class ).to( CoreContextImpl.class );

        bind( HttpService.class ).to( HttpServiceImpl.class );
        bind( UserService.class ).to( UserServiceImpl.class );
        bind( PostService.class ).to( PostServiceImpl.class );
        bind( SettingsService.class ).to( FileSettingsService.class );
    }

    @Provides
    @Singleton
    ExecutorService provideExecutorService()
    {
        return Executors.newFixedThreadPool( 4 );
    }
}
