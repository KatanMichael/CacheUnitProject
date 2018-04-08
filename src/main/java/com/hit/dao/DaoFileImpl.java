package com.hit.dao;

import com.hit.dm.DataModel;

import java.io.*;
import java.util.ArrayList;


public class DaoFileImpl <T> implements IDao<java.lang.Long,DataModel<T>>
{
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

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
		openStreams();
		getDataFromFile();

		listOfEntitys.remove(entity);

		writeDataToFile();

		closeStreams();
	}

	@Override
	public DataModel<T> find(Long id)
	{
		openStreams();
		getDataFromFile();

		for (int i = 0; i <listOfEntitys.size() ; i++)
		{
			if(listOfEntitys.get(i).getId() == id)
			{
				return listOfEntitys.get(i);
			}

		}

		return null;
	}

	@Override
	public void save(DataModel<T> entity)
	{
		listOfEntitys.clear();
		getDataFromFile();
		listOfEntitys.add(entity);
		writeDataToFile();
		closeStreams();
	}


	/*Private Util Methods*/
	private void openStreams()
	{
		try {
			inputStream = new ObjectInputStream(new FileInputStream(fileName));
			outputStream = new ObjectOutputStream(new FileOutputStream(fileName,false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeStreams()
	{
		try {
			inputStream.close();
			outputStream.close();
		}catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}

	private void getDataFromFile()
	{
		DataModel<T> entity;
		try {
			while ((entity = (DataModel<T>) inputStream.readObject()) != null)
			{
				listOfEntitys.add(entity);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void writeDataToFile()
	{
		openStreams();

		for (int i = 0; i < listOfEntitys.size(); i++)
		{
			try {
				outputStream.writeObject(listOfEntitys.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	

}
