package com.hit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Scanner;

public class CLI extends Observable implements Runnable
{

    private OutputStream outputStream;
    private InputStream inputStream;


    public CLI(java.io.InputStream in, java.io.OutputStream out)
    {
        this.inputStream = in;
        this.outputStream = out;
    }

    @Override
    public void run()
    {
        Scanner scanner = new Scanner (inputStream);
        PrintStream printStream = new PrintStream (outputStream);
        String command = null;

        while(true)
        {
            printStream.println ("Please Enter Your Command: ");
            command = scanner.nextLine ();

            //command = "Start";

            if(command.equals ("Start"))
            {
                setChanged ();
                notifyObservers ("Start");

            }
            if(command.equals ("Shutdown"))
            {
                setChanged ();
                notifyObservers ("Shutdown");

            }else
            {
                printStream.println ("Unknown Command");
            }


        }
    }
}
