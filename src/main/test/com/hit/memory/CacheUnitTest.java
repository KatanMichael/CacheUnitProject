package com.hit.memory;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dm.DataModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheUnitTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDataModels()
    {
        //DaoFileImpl<Integer> daoFile = new DaoFileImpl<>();
        //DataModel<Integer> dataModel;
        LRUAlgoCacheImpl<Long,DataModel<Integer>> lru = new LRUAlgoCacheImpl(5);
        //CacheUnit<Integer> cacheUnit = new CacheUnit(lru,daoFile);

        lru.putElement(new Long(1),new DataModel<>(new Long(1),new Integer(1)));
        lru.putElement(new Long(2),new DataModel<>(new Long(2),new Integer(2)));
        lru.putElement(new Long(3),new DataModel<>(new Long(3),new Integer(3)));
        lru.putElement(new Long(4),new DataModel<>(new Long(4),new Integer(4)));
        lru.putElement(new Long(5),new DataModel<>(new Long(5),new Integer(5)));



        assertEquals(true,true);
    }
}