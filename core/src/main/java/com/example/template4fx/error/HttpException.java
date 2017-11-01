package com.example.template4fx.error;

import org.apache.http.StatusLine;

public class HttpException
    extends Exception
{
    public HttpException( StatusLine statusLine )
    {
        super( "[" + statusLine.getStatusCode() + "] " + statusLine.getReasonPhrase() );
    }
}
