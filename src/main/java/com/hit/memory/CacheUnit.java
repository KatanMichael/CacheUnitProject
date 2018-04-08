package com.hit.memory;

import com.hit.algorithm.IAlgoCache;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;

public class CacheUnit <T> 
{

	private IDao dao;
	private IAlgoCache algoCache;

	public CacheUnit(com.hit.algorithm.IAlgoCache<java.lang.Long,DataModel<T>> algo,
            IDao<java.io.Serializable,DataModel<T>> dao)
	{
		this.algoCache = algo;
		this.dao = dao;

	}
	
	
	DataModel<T>[] getDataModels(java.lang.Long[] ids) throws java.lang.ClassNotFoundException, java.io.IOException
	{
		//TODO FIX THIS!!
		
		return null;
	}


}
