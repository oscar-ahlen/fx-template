package com.example.template4fx.service.impl;

import com.example.template4fx.error.HttpException;
import com.example.template4fx.service.HttpService;
import com.google.inject.Singleton;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.WinHttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;

@Singleton
public class HttpServiceImpl
    implements HttpService
{
    private final PoolingHttpClientConnectionManager manager;

    private final CloseableHttpClient client;

    public HttpServiceImpl()
    {
        manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal( 50 );
        manager.setDefaultMaxPerRoute( 20 );

        client = WinHttpClients.custom().setConnectionManager( manager ).build();
    }

    @Override
    public CloseableHttpResponse execute( HttpUriRequest request )
        throws HttpException, IOException
    {
        CloseableHttpResponse response = client.execute( request );
        StatusLine statusLine = response.getStatusLine();

        if ( statusLine.getStatusCode() >= 300 )
            throw new HttpException( statusLine );

        return response;
    }

    @Override
    public void close()
        throws Exception
    {
        client.close();
        manager.close();
    }
}
