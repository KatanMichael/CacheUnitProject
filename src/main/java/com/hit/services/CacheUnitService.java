package com.hit.services;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

import javax.xml.crypto.Data;
import java.io.IOException;

public class CacheUnitService<T>
{

    private CacheUnit cacheUnit;

    public CacheUnitService()
    {
        LRUAlgoCacheImpl<T, DataModel<T>> lru = new LRUAlgoCacheImpl<>(25);
        DaoFileImpl<T> daoFile = new DaoFileImpl<>("out.txt");

        this.cacheUnit = new CacheUnit (lru,daoFile);

        for (int i = 0; i < 150; i++)
        {
            Integer integer = i;
            char c = (char)i;
           //daoFile.save(new DataModel(Long.valueOf(i), c));
        }
    }

    public boolean delete(DataModel<T>[] dataModels)
    {

        DataModel[] returnModels = null;
        Long[] ids = new Long[dataModels.length];

        for (int i = 0; i <dataModels.length ; i++)
        {
            ids[i] = dataModels[i].getId ();
        }

        try
        {
            returnModels = cacheUnit.getDataModels (ids);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace ();
        } catch (IOException e)
        {
            e.printStackTrace ();
        }

        for(DataModel model: returnModels)
        {
            model.setContent (null);
        }



        return true;
    }

    public boolean update(DataModel<T>[] dataModels)
    {

        DataModel[] returnModels = null;
        Long[] ids = new Long[dataModels.length];

        for (int i = 0; i <dataModels.length ; i++)
        {
            ids[i] = dataModels[i].getId ();
        }

        try
        {
            returnModels = cacheUnit.getDataModels (ids);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace ();
        } catch (IOException e)
        {
            e.printStackTrace ();
        }

        for (int i = 0; i <dataModels.length ; i++)
        {
            for (int j = 0; j <returnModels.length ; j++)
            {
                if(dataModels[i].getId ().equals (returnModels[j].getId ()))
                {
                    returnModels[j].setContent (dataModels[i].getContent ());
                    j = returnModels.length+1;
                }
            }
        }
        for(DataModel model: dataModels)
        {
            cacheUnit.updateFile (model);
        }
        return true;
    }

    public DataModel<T>[] get(DataModel<T>[] dataModels)
    {
        Long[] ids = new Long[dataModels.length];
        DataModel[] models = null;

        for (int i = 0; i < dataModels.length; i++)
        {
            ids[i] = dataModels[i].getId ();
        }

        try
        {

            models = cacheUnit.getDataModels (ids);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace ();
        } catch (IOException e)
        {
            e.printStackTrace ();
        }


        return models;
    }
}
