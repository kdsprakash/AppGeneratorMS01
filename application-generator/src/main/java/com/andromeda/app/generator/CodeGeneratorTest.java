package com.andromeda.app.generator;

import java.util.Properties;

import com.andromeda.app.generator.model.Component;
import com.andromeda.app.generator.util.PropertiesFileUtils;
import com.andromeda.commons.util.FileNDirUtils;
import com.andromeda.commons.util.JsonUtils;

public class CodeGeneratorTest
{
	public static void main(String args[]) throws Exception
	{
		Component component = getComponent();
		System.out.println("Component: " + component.getName());
		CodeGenerator codeGenerator = new CodeGenerator();
		codeGenerator.generateCode(component);
	}

	public static Component getComponent() throws Exception
	{
		Properties properties = PropertiesFileUtils.getConfigProperties();
		String tableName = properties.getProperty("table-name");
		String fileName = String.format("input/%s.json", tableName);
		System.out.println("Loading config file: " + fileName);
		String content = FileNDirUtils.getFileString(fileName);
		Component component = (Component) JsonUtils.deserialize(content, Component.class);

		return component;
	}
}
