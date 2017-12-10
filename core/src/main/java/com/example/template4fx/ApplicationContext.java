package com.example.template4fx;

import java.util.Set;

public interface ApplicationContext
    extends AutoCloseable
{
    Set<Class> getServices();

    <T> T getService( Class<T> clazz );
}
