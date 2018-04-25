package com.hit.memory;

import com.hit.algorithm.IAlgoCache;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;

import java.util.ArrayList;

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

		ArrayList<DataModel<T>> listOfEntitys = new ArrayList<>();
		DataModel<T> entity;

		for (int i = 0; i < ids.length; i++)
		{
			DataModel<T> tempData = (DataModel<T>) algoCache.getElement(ids[i]);

			if(tempData != null)
			{
				listOfEntitys.add(tempData);
			}

		}

		for (int i = 0; i <ids.length ; i++)
		{
			DataModel<T> tempData;
			tempData = (DataModel<T>) dao.find(ids[i]);

			if(tempData != null)
			{
				listOfEntitys.add(tempData);
			}

		}


		DataModel<T>[] arrOfDataModels = new DataModel[listOfEntitys.size()];

		for (int i = 0; i < listOfEntitys.size(); i++)
		{
			arrOfDataModels[i] = listOfEntitys.get(i);
		}


		return arrOfDataModels;

	}


}
