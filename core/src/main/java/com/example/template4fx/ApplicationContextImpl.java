package com.example.template4fx;

import com.example.template4fx.service.HttpService;
import com.example.template4fx.service.PostService;
import com.example.template4fx.service.SettingsService;
import com.example.template4fx.service.UserService;
import com.google.inject.Inject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationContextImpl
    implements ApplicationContext
{
    private final ExecutorService executorService = Executors.newFixedThreadPool( 4 );

    private final HttpService httpService;

    private final UserService userService;

    private final PostService postService;

    private final SettingsService settingsService;

    @Inject
    public ApplicationContextImpl( HttpService httpService,
                                   UserService userService,
                                   PostService postService,
                                   SettingsService settingsService )
    {
        this.httpService = httpService;
        this.userService = userService;
        this.postService = postService;
        this.settingsService = settingsService;
    }

    @Override
    public ExecutorService getExecutorService()
    {
        return executorService;
    }

    @Override
    public UserService getUserService()
    {
        return userService;
    }

    @Override
    public PostService getPostService()
    {
        return postService;
    }

    @Override
    public SettingsService getSettingsService()
    {
        return settingsService;
    }

    @Override
    public void close()
        throws Exception
    {
        executorService.shutdown();
        httpService.close();
    }
}
