package com.hit.services;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

import java.io.IOException;

public class CacheUnitService<T>
{

    CacheUnit cacheUnit;

    public CacheUnitService()
    {
        LRUAlgoCacheImpl<Long, DataModel<Integer>> lru = new LRUAlgoCacheImpl<>(25);
        DaoFileImpl<Integer> daoFile = new DaoFileImpl<>("out.txt");

        for (int i = 0; i < 150; i++)
        {
            int integer = i;
            daoFile.save(new DataModel(Long.valueOf(i), integer));
        }

        this.cacheUnit = new CacheUnit (lru,daoFile);
    }

    public boolean delete(DataModel<T>[] dataModels)
    {


        return true;
    }

    public boolean update(DataModel<T>[] dataModels)
    {

        //TODO add code

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
