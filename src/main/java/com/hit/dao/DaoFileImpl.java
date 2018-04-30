package com.hit.dao;

import com.hit.dm.DataModel;

import java.io.*;
import java.util.ArrayList;


public class DaoFileImpl <T> implements IDao<java.lang.Long,DataModel<T>>
{

	private ArrayList<DataModel<T>> listOfEntitys;

	private String fileName;

	public DaoFileImpl()
	{
		listOfEntitys = new ArrayList<>();
		fileName = "out.txt";
	}

	public DaoFileImpl(String fileName)
	{
		listOfEntitys = new ArrayList<>();
		this.fileName = fileName;

	}

	@Override
	public void delete(DataModel<T> entity)
	{

		listOfEntitys.clear();
		DataModel<T> tempModel;

		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));

			boolean control = true;

			while(control)
			{
				tempModel = (DataModel<T>) inputStream.readObject();
				if(tempModel != null)
				{
					listOfEntitys.add(tempModel);
				}else
				{
					control = false;
				}

			}

			listOfEntitys.remove(entity);

			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName,false));

			for(DataModel t: listOfEntitys)
			{
				outputStream.writeObject(t);
			}



		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

	@Override
	public DataModel<T> find(Long id)
	{
		DataModel <T> tempEntity;
		DataModel <T> returnEntity = null;
		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));

			boolean control = true;
			while(control)
			{
				DataModel<T> readObject = (DataModel<T>) inputStream.readObject();
				tempEntity = readObject;
				if(tempEntity != null)
				{
					listOfEntitys.add(tempEntity);
				}else
				{
					control = false;
				}

			}

		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for(DataModel t: listOfEntitys)
		{
			if(t.getId() == id)
			{
				returnEntity = t;
			}
		}


		return returnEntity;
	}

	@Override
	public void save(DataModel<T> entity)
	{

		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName,true));

			outputStream.writeObject(entity);

			outputStream.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}



}
