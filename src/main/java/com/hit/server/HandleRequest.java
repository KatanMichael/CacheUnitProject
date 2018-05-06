package com.hit.server;

import com.hit.services.CacheUnitController;

import java.net.Socket;

public class HandleRequest<T> implements Runnable
{

    Socket socket;
    CacheUnitController unitController;


    public HandleRequest(Socket s, CacheUnitController controller)
    {
        this.socket = s;
        this.unitController = controller;

    }


    @Override
    public void run()
    {

    }
}
