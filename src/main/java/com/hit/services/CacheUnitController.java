package com.hit.services;

import com.hit.dm.DataModel;

public class CacheUnitController<T>
{
    CacheUnitService unitService;

    public CacheUnitController()
    {
        unitService = new CacheUnitService ();
    }

    public boolean delete(DataModel<T>[] dataModels)
    {
        return unitService.delete (dataModels);
    }

    public boolean update(DataModel<T>[] dataModels)
    {
        return unitService.update (dataModels);
    }

    public DataModel<T>[] get(DataModel<T>[] dataModels)
    {
        return unitService.get (dataModels);
    }
}
