package com.hit.memory;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
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
        LRUAlgoCacheImpl<Long, DataModel<Integer>> lru = new LRUAlgoCacheImpl<>(25);
        DaoFileImpl<Integer> daoFile = new DaoFileImpl<>("out.txt");

        for (int i = 0; i < 10; i++)
        {
            int integer = i;
            //daoFile.save(new DataModel(Long.valueOf(i), integer));
        }


        for (int i = 0; i < 5; i++) {
            lru.putElement((long) i, new DataModel((long) i, i));
        }

        CacheUnit<Integer> cacheUnit = new CacheUnit(lru, daoFile);



        Long[] ids = {Long.valueOf(1),Long.valueOf(2),Long.valueOf(15)};

        try {
            DataModel<Integer>[] dataModels = cacheUnit.getDataModels(ids);

            for(DataModel t: dataModels)
            {
                System.out.println(t.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Test
    public void justTesting()
    {
        try
        {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("out.txt",true));
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("out.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}