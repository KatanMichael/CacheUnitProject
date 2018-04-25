package com.hit.memory;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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
       DataModel<Integer> dataModel;
       LRUAlgoCacheImpl<Long, DataModel<Integer>> lru = new LRUAlgoCacheImpl<>(5);
       DaoFileImpl<Integer> daoFile = new DaoFileImpl<>("out.txt");

        for (int i = 0; i < 6; i++)
        {
               lru.putElement((long)i,new DataModel((long)i,i));
        }

        CacheUnit<Integer> cacheUnit = new CacheUnit(lru,daoFile);

        Long[] ids = {Long.valueOf(1),Long.valueOf(3),Long.valueOf(4)};

        ArrayList<DataModel<Integer>> models = new ArrayList<>();

        try {
            DataModel<Integer>[] dataModels = cacheUnit.getDataModels(ids);

            for (int i = 0; i < dataModels.length; i++)
            {
                models.add(dataModels[i]);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (DataModel data: models)
        {
            System.out.println(data.getId()+" "+ data.getContent());
        }

    }

    @Test
    public void justTesting()
    {


    }
}