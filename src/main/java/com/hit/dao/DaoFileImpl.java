package com.hit.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.dm.DataModel;

import java.io.*;
import java.util.ArrayList;


public class DaoFileImpl <T> implements IDao<java.lang.Long,DataModel<T>>
{

	private ArrayList<DataModel<T>> listOfEntitys;
	private static ObjectOutputStream outputStream;
	private static ObjectInputStream inputStream;
	private Gson gson;
	private static String fileName;

	public DaoFileImpl()
	{
		listOfEntitys = new ArrayList<>();
		fileName = "out.txt";
		gson = new GsonBuilder().create();
	}

	public DaoFileImpl(String fileName)
	{
		listOfEntitys = new ArrayList<>();
		this.fileName = fileName;
		gson = new GsonBuilder().create();

	}

	@Override
	public void delete(DataModel<T> entity)
	{
		listOfEntitys.clear();

		try {
			inputStream = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean cont = true;
		String inputJson;
		DataModel inputData;
		try {
			while(cont)
			{
				inputJson = (String) inputStream.readObject();
				if (inputJson != null)
				{
					inputData = gson.fromJson(inputJson, DataModel.class);
					listOfEntitys.add(inputData);
				} else {
					cont = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		boolean remove = false;

		for(DataModel model: listOfEntitys)
		{
			if(model.getId() == entity.getId())
			{
				remove = listOfEntitys.remove(model);
				break;
			}
		}

		if (remove)
		{
			try {
				outputStream = new ObjectOutputStream(new FileOutputStream(fileName, false));
			} catch (IOException e) {
				e.printStackTrace();
			}

			for(DataModel model: listOfEntitys)
			{
				String jsonString = gson.toJson(model);
				try {
					outputStream.writeObject(jsonString);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public DataModel<T> find(Long id)
	{
		DataModel resutlData = null;
		listOfEntitys.clear();

		try {
			inputStream = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean cont = true;
		String inputJson;
		DataModel inputData;
		try {
			while(cont)
			{
				inputJson = (String) inputStream.readObject();
				if (inputJson != null)
				{
					inputData = gson.fromJson(inputJson, DataModel.class);
					listOfEntitys.add(inputData);
				} else {
					cont = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for(DataModel model: listOfEntitys)
		{
			if(model.getId() == id)
			{
				resutlData = model;
			}
		}

		return resutlData;
	}

	@Override
	public void save(DataModel<T> entity)
	{
		listOfEntitys.clear();

		try {
			inputStream = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//File Not exists, enter first element
		if(inputStream == null)
		{
			try {
				outputStream = new ObjectOutputStream(new FileOutputStream(fileName,false));

				String s = gson.toJson(entity);
				outputStream.writeObject(s);

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}else
			//The file is contain some data... we download all the data,
			// add another line and
			//rewrite all of it to the file
		{
			listOfEntitys.clear();

			boolean cont = true;
			String inputJson;
			DataModel inputData;
			try {
				while(cont)
				{
					inputJson = (String) inputStream.readObject();
					if(inputJson != null)
					{
						inputData = gson.fromJson(inputJson,DataModel.class);
						listOfEntitys.add(inputData);
					}else
					{
						cont = false;
					}
				}


			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			listOfEntitys.add(entity);

			try
			{
				File file = new File(fileName);
				file.delete();
				outputStream = new ObjectOutputStream(new FileOutputStream(fileName,false));
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (DataModel dataModel: listOfEntitys)
			{
				String tempString = gson.toJson(dataModel);
				try {
					outputStream.writeObject(tempString);
				} catch (IOException e) {
					e.printStackTrace();
				}


			}

		}

		closeStreams();
	}

	public void openStreams()
	{
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(fileName,true));
			inputStream = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeStreams()
	{
		try {
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
