package com.example.template4fx;

import com.example.template4fx.service.HttpService;
import com.example.template4fx.service.PostService;
import com.example.template4fx.service.SettingsService;
import com.example.template4fx.service.UserService;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationContextImpl
    implements ApplicationContext
{
    private final Map<Class, Object> services = new HashMap<>();

    @Inject
    public ApplicationContextImpl( HttpService httpService,
                                   UserService userService,
                                   PostService postService,
                                   SettingsService settingsService )
    {
        put( ExecutorService.class, Executors.newFixedThreadPool( 4 ) );

        put( HttpService.class, httpService );
        put( UserService.class, userService );
        put( PostService.class, postService );
        put( SettingsService.class, settingsService );
    }

    @Override
    public Set<Class> getServices()
    {
        return services.keySet();
    }

    @SuppressWarnings( "unchecked" )
    public <T> T getService( Class<T> clazz )
    {
        if ( !services.containsKey( clazz ) )
            throw new IllegalArgumentException( String.format( "%s does not exist in application context", clazz ) );

        return (T) services.get( clazz );
    }

    @Override
    public void close()
        throws Exception
    {
        getService( ExecutorService.class ).shutdown();
        getService( HttpService.class ).close();
    }

    private <T> void put( Class<T> clazz, T service )
    {
        services.put( clazz, service );
    }
}
