package com.hit.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.dm.DataModel;

import java.io.*;
import java.util.ArrayList;


public class DaoFileImpl <T> implements IDao<java.lang.Long,DataModel<T>>
{

	private ArrayList<DataModel<T>> listOfEntitys;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private Gson gson;
	private String fileName;

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


	}

	@Override
	public DataModel<T> find(Long id)
	{

	}

	@Override
	public void save(DataModel<T> entity)
	{
		openStreams();

		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String jsonString = gson.toJson(entity);

		try {
			outputStream.writeObject(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void openStreams()
	{
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(fileName,true));
			inputStream = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
