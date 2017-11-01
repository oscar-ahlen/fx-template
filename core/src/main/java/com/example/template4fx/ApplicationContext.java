package com.example.template4fx;

import com.example.template4fx.service.PostService;
import com.example.template4fx.service.SettingsService;
import com.example.template4fx.service.UserService;

import java.util.concurrent.ExecutorService;

public interface ApplicationContext
    extends AutoCloseable
{
    ExecutorService getExecutorService();

    UserService getUserService();

    PostService getPostService();

    SettingsService getSettingsService();
}
