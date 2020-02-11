package com.andromeda.app.generator.util;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * 
 * @author pk185013
 * @date Jan 15, 2020
 *
 */
public class CodeGeneratorUtils
{
	public static void generateCode(Map<String, Object> params, String templateName,
			String outputFileName) throws Exception
	{
		// Configure FreeMarker
		Configuration cfg = new Configuration();

		// Where do we load the templates from:
		cfg.setClassForTemplateLoading(CodeGeneratorUtils.class, "/templates");

		// Some other recommended settings:
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// Get the template
		Template template = cfg.getTemplate(templateName);

		// Generate the output

		// Write output to the console
		// Writer consoleWriter = new OutputStreamWriter(System.out);
		// template.process(params, consoleWriter);

		// For the sake of example, also write output into a file:
		Writer fileWriter = new FileWriter(new File(outputFileName));
		try
		{
			template.process(params, fileWriter);
		}
		finally
		{
			fileWriter.close();
		}
	}
}
