package com.hit.server;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

public class Request<T> implements Serializable
{
    Map<String,String> hashtable;
    T body;

    public Request(java.util.Map<java.lang.String,java.lang.String> headers,
                   T body)
    {
        this.hashtable = headers;
        this.body = body;

    }

    public Map<String, String> getHashtable()
    {
        return hashtable;
    }

    public void setHashtable(Map<String, String> hashtable)
    {
        this.hashtable = hashtable;
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
