package ${packageName}.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ${packageName}.model.${name};
import ${packageName}.service.${name}Service;

@RestController
public class ${name}Controller
{
	@Autowired
	private ${name}Service ${name?uncap_first}Service;

	@ResponseBody
	@RequestMapping(value = "/service/${tableName}", method = { RequestMethod.GET })
	public List<${name}> getAll()
	{
		return ${name?uncap_first}Service.getAll();
	}

	@ResponseBody
	@RequestMapping(value = "/service/${tableName}/{id}", method = { RequestMethod.GET })
	public ${name} getById(@PathVariable Integer id)
	{
		return ${name?uncap_first}Service.getById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/service/${tableName}", method = { RequestMethod.POST })
	public void save(@RequestBody ${name} ${name?uncap_first})
	{
		try
		{
			${name?uncap_first}Service.save(${name?uncap_first});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/service/${tableName}", method = { RequestMethod.PUT })
	public void update(@RequestBody ${name} ${name?uncap_first})
	{
		try
		{
			${name?uncap_first}Service.update(${name?uncap_first});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/service/${tableName}/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable Integer id)
	{
		${name?uncap_first}Service.deleteById(id);
	}
}
