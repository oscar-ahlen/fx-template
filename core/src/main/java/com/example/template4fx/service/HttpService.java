package com.example.template4fx.service;

import com.example.template4fx.error.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;

public interface HttpService
    extends AutoCloseable
{
    CloseableHttpResponse execute( HttpUriRequest request )
        throws HttpException, IOException;
}
