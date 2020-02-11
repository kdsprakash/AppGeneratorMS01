package com.andromeda.app.generator.model;

import java.util.List;

import com.andromeda.commons.model.BaseModel;

public class Table extends BaseModel
{
	private String name;
//	private List<Column> columns;

	public Table(String name)
	{
		super();
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

//	public List<Column> getColumns()
//	{
//		return columns;
//	}
//
//	public void setColumns(List<Column> columns)
//	{
//		this.columns = columns;
//	}
}
