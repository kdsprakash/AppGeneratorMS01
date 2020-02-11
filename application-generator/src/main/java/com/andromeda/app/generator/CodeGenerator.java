package com.andromeda.app.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andromeda.app.generator.model.Column;
import com.andromeda.app.generator.model.Component;
import com.andromeda.app.generator.util.CodeGeneratorUtils;
import com.andromeda.commons.util.DateUtils;
import com.andromeda.commons.util.FileNDirUtils;

/**
 * 
 * @author pk185013
 * @date Jan 15, 2020
 *
 */
public class CodeGenerator
{
	private static final String MODEL_DIR = "%s/main/java/%s/model";
	private static final String DAO_DIR = "%s/main/java/%s/dao";
	private static final String SERVICE_DIR = "%s/main/java/%s/service";
	private static final String CONTROLLER_DIR = "%s/main/java/%s/controller";
	private static final String MYBATIS_DIR = "%s/main/resources/%s/model";

	private static final String HTML_DIR = "%s/modules/%s";
	private static final String SCRIPTS_DIR = "%s/modules/%s/scripts";

	private static final String MYBATIS_MAPPER_ENTRY = "<mapper resource=\"%s/model/%s-MyBatis.xml\" />";

	private String baseDir = "output";

	public void generateCode(Component component) throws Exception
	{
		String module = component.getModule();
		String packageName = component.getPackageName() + "." + module;
		String packageDir = packageName.replaceAll("\\.", "/");

		String modelDir = String.format(MODEL_DIR, baseDir, packageDir);
		String daoDir = String.format(DAO_DIR, baseDir, packageDir);
		String serviceDir = String.format(SERVICE_DIR, baseDir, packageDir);
		String controllerDir = String.format(CONTROLLER_DIR, baseDir, packageDir);
		String mybatisDir = String.format(MYBATIS_DIR, baseDir, packageDir);
		String htmlDir = String.format(HTML_DIR, baseDir, module);
		String scriptsDir = String.format(SCRIPTS_DIR, baseDir, module);

		List<String> directories = new ArrayList<String>();
		directories.add(modelDir);
		directories.add(daoDir);
		directories.add(serviceDir);
		directories.add(controllerDir);
		directories.add(mybatisDir);

		directories.add(htmlDir);
		directories.add(scriptsDir);

		FileNDirUtils.createDirs(".", directories);

		Map<String, Object> params = getParams(component);

		generateModel(component, params, modelDir);
		generateDao(component, params, daoDir);
		generateService(component, params, serviceDir);
		generateController(component, params, controllerDir);
		generateMyBatis(component, params, mybatisDir);

		generateHtml(component, params, htmlDir);
		generateScriptApp(component, params, scriptsDir);
		generateScriptController(component, params, scriptsDir);

		System.out.println("\nAdd below entry in MyBatis configuration file");
		System.out.printf(MYBATIS_MAPPER_ENTRY, packageDir, component.getName());
	}

	private void generateModel(Component component, Map<String, Object> params,
			String targetLocation) throws Exception
	{
		String templateName = "ModelTemplate.ftl";
		String name = component.getName();
		String outputFileName = targetLocation + "/" + name + ".java";

		CodeGeneratorUtils.generateCode(params, templateName, outputFileName);
		System.out.println(outputFileName + ": Generated");
	}

	private void generateDao(Component component, Map<String, Object> params, String targetLocation)
			throws Exception
	{
		String templateName = "DaoTemplate.ftl";
		String name = component.getName();
		String outputFileName = targetLocation + "/" + name + "Dao.java";

		CodeGeneratorUtils.generateCode(params, templateName, outputFileName);
		System.out.println(outputFileName + ": Generated");
	}

	private void generateService(Component component, Map<String, Object> params,
			String targetLocation) throws Exception
	{
		String templateName = "ServiceTemplate.ftl";
		String name = component.getName();
		String outputFileName = targetLocation + "/" + name + "Service.java";

		CodeGeneratorUtils.generateCode(params, templateName, outputFileName);
		System.out.println(outputFileName + ": Generated");
	}

	private void generateController(Component component, Map<String, Object> params,
			String targetLocation) throws Exception
	{
		String templateName = "ControllerTemplate.ftl";
		String name = component.getName();
		String outputFileName = targetLocation + "/" + name + "Controller.java";

		CodeGeneratorUtils.generateCode(params, templateName, outputFileName);
		System.out.println(outputFileName + ": Generated");
	}

	private void generateMyBatis(Component component, Map<String, Object> params,
			String targetLocation) throws Exception
	{
		String templateName = "MyBatisTemplate.ftl";
		String name = component.getName();
		String outputFileName = targetLocation + "/" + name + "-MyBatis.xml";

		CodeGeneratorUtils.generateCode(params, templateName, outputFileName);
		System.out.println(outputFileName + ": Generated");
	}

	private void generateHtml(Component component, Map<String, Object> params,
			String targetLocation) throws Exception
	{
		String templateName = "modules/HtmlTemplate.ftl";
		String name = component.getName();
		String outputFileName = targetLocation + "/" + name + ".html";

		CodeGeneratorUtils.generateCode(params, templateName, outputFileName);
		System.out.println(outputFileName + ": Generated");
	}

	private void generateScriptController(Component component, Map<String, Object> params,
			String targetLocation) throws Exception
	{
		String templateName = "modules/ScriptControllerTemplate.ftl";
		String name = component.getName();
		String outputFileName = targetLocation + "/" + name + "Controller.js";

		CodeGeneratorUtils.generateCode(params, templateName, outputFileName);
		System.out.println(outputFileName + ": Generated");
	}

	private void generateScriptApp(Component component, Map<String, Object> params,
			String targetLocation) throws Exception
	{
		String templateName = "modules/ScriptAppTemplate.ftl";
		String name = component.getName();
		String outputFileName = targetLocation + "/" + name + "App.js";

		CodeGeneratorUtils.generateCode(params, templateName, outputFileName);
		System.out.println(outputFileName + ": Generated");
	}

	private Map<String, Object> getParams(Component component)
	{
		Map<String, Object> params = new HashMap<String, Object>();

		List<Column> columns = component.getColumns();
		for (Column column : columns)
		{
			if (column.isIdColumn())
			{
				params.put("idColumn", column.getName());
				params.put("idColumnType", column.getType());
			}
		}

		String module = component.getModule();
		String packageName = component.getPackageName() + "." + module;

		params.put("name", component.getName());
		params.put("tableName", component.getTableName());
		params.put("packageName", packageName);
		params.put("columns", component.getColumns());
		params.put("author", component.getAuthor());
		params.put("module", module);
		params.put("date", DateUtils.getCurrentDate());

		return params;
	}
}
