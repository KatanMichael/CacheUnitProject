package com.hit.server;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

public class Request<T> implements Serializable
{
    Map<String,String> header;
    T body;

    public Request(java.util.Map<java.lang.String,java.lang.String> headers,
                   T body)
    {
        this.header = headers;
        this.body = body;

    }

    public Map<String, String> getHeaders()
    {
        return header;
    }

    public void setHeaders(Map<String, String> hashtable)
    {
        this.header = hashtable;
    }

    public T getBody()
    {
        return body;
    }

    public void setBody(T body)
    {
        this.body = body;
    }

    public String toString()
    {
        String s = "...";
        //TODO add return string

        return s;

    }
}
