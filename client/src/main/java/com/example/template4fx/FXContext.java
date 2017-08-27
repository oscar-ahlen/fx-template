package com.example.template4fx;

import java.util.concurrent.ExecutorService;

public class FXContext
{
    private final ExecutorService executorService;

    private final Settings settings;

    public FXContext( ExecutorService executorService, Settings settings )
    {
        this.executorService = executorService;
        this.settings = settings;
    }

    public void shutdown()
    {
        executorService.shutdown();
    }

    public ExecutorService getExecutorService()
    {
        return executorService;
    }

    public Settings getSettings()
    {
        return settings;
    }
}
