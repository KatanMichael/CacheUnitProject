package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class HandleRequest<T> implements Runnable
{

    Socket socket;
    CacheUnitController unitController;

    Request socketRequest;

    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    Gson gson;

    public HandleRequest(Socket s, CacheUnitController controller)
    {
        this.socket = s;
        this.unitController = controller;

        try
        {
            inputStream = new ObjectInputStream (socket.getInputStream ());
            outputStream = new ObjectOutputStream (socket.getOutputStream ());
        } catch (IOException e)
        {
            e.printStackTrace ();
        }

        gson = new GsonBuilder ().create ();

    }


    @Override
    public void run()
    {

        String inputString;

        String command;
        DataModel[] model;

        try
        {
            inputString = (String) inputStream.readObject ();
            System.out.println (inputString);
            outputStream.writeObject (inputString);

            socketRequest = gson.fromJson (inputString,Request.class);

            System.out.println (socketRequest);
        } catch (IOException e)
        {
            e.printStackTrace ();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace ();
        }

        try
        {
            outputStream.writeObject ("Hello!");
        } catch (IOException e)
        {
            e.printStackTrace ();
        }

        ArrayList list = (ArrayList) socketRequest.getBody ();

        model = (DataModel[]) list.toArray ();

        command = (String) socketRequest.getHeaders().get ("action");

        if(command.equals ("GET"))
        {

            DataModel[] dataModels = unitController.get (model);

        }else if(command.equals ("DELETE"))
        {

            boolean delete = unitController.delete (model);

        }else if(command.equals ("UPDATE"))
        {
            boolean update = unitController.update (model);


        }else
        {
            try
            {
                outputStream.writeObject ("Unkown Action");
            } catch (IOException e)
            {
                e.printStackTrace ();
            }
        }



    }
}
