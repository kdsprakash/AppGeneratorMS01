package com.andromeda.app.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.andromeda.app.generator.model.Column;
import com.andromeda.app.generator.model.Component;
import com.andromeda.app.generator.util.PropertiesFileUtils;
import com.andromeda.commons.util.FileNDirUtils;
import com.andromeda.commons.util.JsonUtils;

/**
 * 
 * @author pk185013
 * @date Jan 15, 2020
 *
 */
public class ConfigGenerator
{
	private static final String JSON_FILE = "./%s/%s.json";
	private String baseDir = "input";

	public static void main(String args[]) throws Exception
	{
		ConfigGenerator c = new ConfigGenerator();
		c.generateCode();
	}

	public void generateCode() throws Exception
	{
		Properties properties = PropertiesFileUtils.getConfigProperties();

		String packageName = properties.getProperty("package-name");
		String module = properties.getProperty("module");
		String author = properties.getProperty("author");

		String fileName = String.format(JSON_FILE, baseDir, module);
		Component component = getComponentData(properties);
		component.setPackageName(packageName);
		component.setAuthor(author);
		component.setModule(module);
		String json = JsonUtils.toString(component);
		System.out.println("JSON: " + json);
		FileNDirUtils.writeToFile(json, fileName);
	}

	public Component getComponentData(Properties properties) throws Exception
	{
		String tableName = properties.getProperty("table-name");
		String jdbcDriver = properties.getProperty("jdbc-driver");
		String jdbcUrl = properties.getProperty("jdbc-url");
		String jdbcUsername = properties.getProperty("jdbc-username");
		String jdbcPassword = properties.getProperty("jdbc-password");

		Component component = new Component();
		int idColumnIndex = 0; // TODO: Need to add ID column check

		String sql = "SELECT * FROM " + tableName + " LIMIT 1;";
		Connection conn = null;
		try
		{
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();

			List<Column> columns = new ArrayList<Column>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
			{
				String name = rsmd.getColumnName(i);
				String type = rsmd.getColumnClassName(i);

				Column column = new Column(name, type);
				columns.add(column);
			}

			columns.get(idColumnIndex).setIdColumn(true);
			component.setColumns(columns);

			rs.close();
			st.close();
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		component.setName(tableName);
		component.setTableName(tableName);

		return component;
	}
}
