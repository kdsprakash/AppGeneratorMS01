package ${packageName}.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.dao.${name}Dao;
import ${packageName}.model.${name};

/**
 * 
 * @author ${author}
 * @date ${date}
 *
 */
@Service
public class ${name}Service
{
	Logger logger = LoggerFactory.getLogger(${name}Service.class);

	@Autowired
	private ${name}Dao ${name?uncap_first}Dao;

	public void set${name}Dao(${name}Dao ${name?uncap_first}Dao)
	{
		this.${name?uncap_first}Dao = ${name?uncap_first}Dao;
	}

	public List<${name}> getAll()
	{
		return ${name?uncap_first}Dao.getAll();
	}

	public ${name} getById(${idColumnType} ${idColumn})
	{
		return ${name?uncap_first}Dao.getById(${idColumn});
	}

	public void save(${name} ${name?uncap_first})
	{
		${name?uncap_first}Dao.add(${name?uncap_first});
	}

	public void update(${name} ${name?uncap_first})
	{
		${name?uncap_first}Dao.update(${name?uncap_first});
	}

	public void deleteById(${idColumnType} ${idColumn})
	{
		${name?uncap_first}Dao.deleteById(${idColumn});
	}
}
