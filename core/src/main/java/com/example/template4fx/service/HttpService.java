package com.example.template4fx.service;

import com.example.template4fx.error.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.lang.reflect.Type;

public interface HttpService
    extends AutoCloseable
{
    CloseableHttpResponse execute( HttpUriRequest request )
        throws IOException;

    <T> T execute( HttpUriRequest request, Type type )
        throws HttpException, IOException;
}
