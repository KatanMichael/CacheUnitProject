package com.hit.memory;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class CacheUnitTest
{

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDataModels()
    {
        LRUAlgoCacheImpl<Long, DataModel<Integer>> lru = new LRUAlgoCacheImpl<>(25);
        DaoFileImpl<Integer> daoFile = new DaoFileImpl<>("out.txt");

        CacheUnit<Integer> cacheUnit = new CacheUnit(lru, daoFile);

        for (int i = 0; i < 27; i++)
        {
            //lru.putElement(Long.valueOf(i),new DataModel(Long.valueOf(i),i));
        }

        for (int i = 0; i < 150; i++)
        {
            int integer = i;
           //daoFile.save(new DataModel(Long.valueOf(i), integer));
        }


        Long[] ids = {Long.valueOf(19),Long.valueOf(20),Long.valueOf(110),Long.valueOf(101)};
        DataModel<Integer>[] dataModels = null;

        try
        {
            dataModels = cacheUnit.getDataModels(ids);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (DataModel model: dataModels)
        {
            System.out.println(model.getId() + " "+model.getContent());
        }

        Long[] ids1 = {Long.valueOf(19),Long.valueOf(20),Long.valueOf(110),Long.valueOf(101)};
        DataModel<Integer>[] dataModels1 = null;

        try
        {
            dataModels1 = cacheUnit.getDataModels(ids1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (DataModel model: dataModels1)
        {
            System.out.println(model.getId() + " "+model.getContent());
        }

    }

    @Test
    public void justTesting()
    {

    }
}