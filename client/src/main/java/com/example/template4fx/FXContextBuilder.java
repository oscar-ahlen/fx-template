package com.example.template4fx;

import java.util.concurrent.ExecutorService;

public class FXContextBuilder
{
    private ExecutorService executorService;

    private Settings settings;

    public FXContextBuilder setExecutorService( ExecutorService executorService )
    {
        this.executorService = executorService;
        return this;
    }

    public FXContextBuilder setSettings( Settings settings )
    {
        this.settings = settings;
        return this;
    }

    public FXContext createFXContext()
    {
        return new FXContext( executorService, settings );
    }
}
