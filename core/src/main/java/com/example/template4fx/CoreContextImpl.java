package com.example.template4fx;

import com.example.template4fx.service.HttpService;
import com.google.inject.Inject;

import java.util.concurrent.ExecutorService;

public class CoreContextImpl
    implements CoreContext
{
    private final ExecutorService executorService;

    private final HttpService httpService;

    @Inject
    public CoreContextImpl( ExecutorService executorService, HttpService httpService )
    {
        this.executorService = executorService;
        this.httpService = httpService;
    }

    @Override
    public void close()
        throws Exception
    {
        executorService.shutdown();
        httpService.close();
    }
}
