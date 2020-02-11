package com.andromeda.app.generator.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileUtils
{
	public static Properties getConfigProperties()
	{
		Properties properties = new Properties();

		try (InputStream input = new FileInputStream("config.properties"))
		{
			// load a properties file
			properties.load(input);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		return properties;
	}
}
