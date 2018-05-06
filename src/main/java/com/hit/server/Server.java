package com.hit.server;

import com.hit.services.CacheUnitController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server implements Observer
{
    private ServerSocket socket;
    private boolean serverIsRunning;
    CacheUnitController unitController;
    Executor threadPool;


    public Server()
    {
        unitController = new CacheUnitController ();
        threadPool = Executors.newCachedThreadPool ();
    }


    void start()
    {
        serverIsRunning = true;

        try
        {
            socket = new ServerSocket (12345);
        } catch (IOException e)
        {
            e.printStackTrace ();
        }
        Socket accept = null;

        while(serverIsRunning)
        {
            try
            {
                System.out.println ("Waiting For Client");
                accept = socket.accept ();
            } catch (IOException e)
            {
                e.printStackTrace ();
            }

            Thread thread = new Thread
                    (new HandleRequest(accept,unitController));

            threadPool.execute (thread);


        }


    }

    @Override
    public void update(Observable o, Object arg)
    {
        String inputCommand = (String) arg;


        if(inputCommand.equals ("Start"))
        {
            System.out.println ("Starting Server...");
            start ();

        }else
        {
            serverIsRunning = false;
            System.out.println ("Shutdown Server...");
        }

    }
}
