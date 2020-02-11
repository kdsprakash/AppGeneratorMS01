package com.andromeda.app.generator.model;

import com.andromeda.commons.model.BaseModel;

/**
 * 
 * @author pk185013
 * @date Jan 15, 2020
 *
 */
public class Column extends BaseModel
{
	private String name;
	private String type;
	private boolean idColumn = false;
	
	public Column()
	{
		super();
	}

	public Column(String name, String type)
	{
		super();
		this.name = name;
		this.type = type;
	}

	public Column(String name, String type, Boolean idColumn)
	{
		super();
		this.name = name;
		this.type = type;
		this.idColumn = idColumn;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public boolean isIdColumn()
	{
		return idColumn;
	}

	public void setIdColumn(boolean idColumn)
	{
		this.idColumn = idColumn;
	}
}
