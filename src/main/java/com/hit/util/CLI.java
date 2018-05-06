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
            command = scanner.nextLine ();

            if(command.equals ("Start"))
            {
                setChanged ();
                notifyObservers ("Start");

            }else if(command.equals ("Shutdown"))
            {
                setChanged ();
                notifyObservers ("Shutdown");

            }else
            {
                printStream.println ("Unknows Command");
            }

        }
    }
}
