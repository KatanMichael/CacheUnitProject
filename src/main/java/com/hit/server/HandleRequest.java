package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class HandleRequest<T> implements Runnable
{

    Socket socket;
    CacheUnitController unitController;

    Request<DataModel<T>[]> socketRequest;

    Gson gson;

    Type ref;


    public HandleRequest(Socket s, CacheUnitController controller)
    {
        this.socket = s;
        this.unitController = controller;

    }


    @Override
    public void run()
    {
        Scanner inputStream = null;
        PrintWriter outputStream = null;

        gson = new GsonBuilder ().create ();

        try
        {
            inputStream = new Scanner (new InputStreamReader (socket.getInputStream ()));
            outputStream = new PrintWriter (new OutputStreamWriter (socket.getOutputStream ()));
            inputStream = new Scanner (new InputStreamReader (socket.getInputStream ()));
        } catch (IOException e)
        {
            e.printStackTrace ();
        }


        String inputString;

        DataModel[] model = null;
        String command;
        DataModel<T>[] body;

        ref = new TypeToken<Request<DataModel<T>[]>> (){}.getType();

        inputString = null;

        System.out.println (inputStream.next ());

        socketRequest = new Gson().fromJson(inputString, ref);

        Map headers = socketRequest.getHeaders ();

        command = (String) headers.get ("action");

        body = socketRequest.getBody ();


        if(command.equals ("GET"))
        {

            DataModel[] dataModels = unitController.get (body);

            String gsonString = gson.toJson (dataModels);

            outputStream.write (gsonString);
            outputStream.flush ();

        }else if(command.equals ("DELETE"))
        {

            boolean delete = unitController.delete (body);

            outputStream.write (String.valueOf (delete));
            outputStream.flush ();

        }else if(command.equals ("UPDATE"))
        {

            boolean update = unitController.update (body);

            outputStream.write (String.valueOf (update));
            outputStream.flush ();

        }else
        {
            outputStream.write ("Unknown Action");
            outputStream.flush ();

        }

        outputStream.close ();
        inputStream.close ();
    }

}
