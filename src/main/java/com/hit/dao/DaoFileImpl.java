package com.hit.dao;


import com.hit.dm.DataModel;

import java.io.*;
import java.util.Hashtable;


public class DaoFileImpl<T> implements IDao<java.lang.Long, DataModel<T>>
{
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    String fileName;

    Hashtable<Long, DataModel<T>> hashtable;


    public DaoFileImpl()
    {
        fileName = "out.txt";
        hashtable = new Hashtable<> ();

    }

    public DaoFileImpl(String fileName)
    {
        this.fileName = fileName;
        hashtable = new Hashtable<> ();
    }

    @Override
    public void delete(DataModel<T> entity)
    {
        openInputStream ();
        hashtable.remove (entity.getId ());
        openOutputStream ();

    }

    @Override
    public DataModel<T> find(Long id)
    {
        DataModel resultModel = null;

        openInputStream ();

        resultModel = hashtable.get (id);

        openOutputStream ();
        closeStreams ();

        return resultModel;
    }


    @Override
    public void save(DataModel<T> entity)
    {
        openInputStream ();
        hashtable.put (entity.getId (),entity);

        openOutputStream ();
        closeStreams ();
    }

    private void openInputStream()
    {
        try
        {
            inputStream = new ObjectInputStream (new FileInputStream (fileName));

            hashtable = (Hashtable<Long, DataModel<T>>)inputStream.readObject ();

        } catch (IOException e)
        {
            e.printStackTrace ();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace ();
        }


    }

    private void openOutputStream()
    {
        try
        {
            outputStream = new ObjectOutputStream (new FileOutputStream (fileName,false));
            outputStream.writeObject (hashtable);
        } catch (IOException e)
        {
            e.printStackTrace ();
        }


    }

    private void closeStreams()
    {
        try
        {
            outputStream.close ();
            inputStream.close ();
        } catch (IOException e)
        {
            e.printStackTrace ();
        }
    }

}
