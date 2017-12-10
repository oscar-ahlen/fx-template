package com.example.template4fx.service.impl;

import com.example.template4fx.error.HttpException;
import com.example.template4fx.service.HttpService;
import com.google.gson.Gson;
import com.google.inject.Singleton;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.WinHttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

@Singleton
public class HttpServiceImpl
    implements HttpService
{
    private final PoolingHttpClientConnectionManager manager;

    private final CloseableHttpClient client;

    private final Gson gson = new Gson();

    public HttpServiceImpl()
    {
        manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal( 50 );
        manager.setDefaultMaxPerRoute( 20 );

        client = WinHttpClients.custom().setConnectionManager( manager ).build();
    }

    @Override
    public CloseableHttpResponse execute( HttpUriRequest request )
        throws IOException
    {
        return client.execute( request );
    }

    @Override
    public <T> T execute( HttpUriRequest request, Type type )
        throws HttpException, IOException
    {
        try ( CloseableHttpResponse response = client.execute( request ) )
        {
            StatusLine statusLine = response.getStatusLine();

            if ( statusLine.getStatusCode() >= 300 )
                throw new HttpException( statusLine );

            try ( InputStream inputStream = response.getEntity().getContent();
                  InputStreamReader reader = new InputStreamReader( inputStream ) )
            {
                return gson.fromJson( reader, type );
            }
        }
    }

    @Override
    public void close()
        throws Exception
    {
        client.close();
        manager.close();
    }
}
