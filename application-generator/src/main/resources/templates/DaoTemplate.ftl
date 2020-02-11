package ${packageName}.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.andromeda.commons.dao.BaseDAO;
import ${packageName}.model.${name};

/**
 * An implementation for storing and retrieving ${r"{@link "}${name}${r"}"} objects
 * to and from the database.
 *
 * @author ${author}
 * @date ${date}
 *
 */
@Repository
public class ${name}Dao extends BaseDAO
{
	/**
	 * Inserts ${r"{@link "}${name}${r"}"} object into the database.
	 * 
	 * @param ${name?uncap_first}
	 */
	public void add(${name} ${name?uncap_first})
	{
		Map<String, Object> params = new HashMap<>();
		params.put("p", ${name?uncap_first});
		sqlSessionTemplate.insert("${packageName}.model.${name}.Add", params);
	}

	/**
	 * Updates ${r"{@link "}${name}${r"}"} object into the database.
	 * 
	 * @param ${name?uncap_first}
	 */
	public void update(${name} ${name?uncap_first})
	{
		Map<String, Object> params = new HashMap<>();
		params.put("p", ${name?uncap_first});
		sqlSessionTemplate.update("${packageName}.model.${name}.Update", params);
	}

	/**
	 * Deletes ${r"{@link "}${name}${r"}"} object from the database.
	 * 
	 * @param ${idColumn}
	 */
	public void deleteById(${idColumnType} ${idColumn})
	{
		sqlSessionTemplate.delete("${packageName}.model.${name}.Delete", ${idColumn});
	}

	/**
	 * Returns all ${r"{@link "}${name}${r"}"} objects stored in the database.
	 * 
	 * @return
	 */
	public List<${name}> getAll()
	{
		return sqlSessionTemplate.selectList("${packageName}.model.${name}.Get");
	}

	/**
	 * Returns ${r"{@link "}${name}${r"}"} object stored in the database.
	 * 
	 * @param ${idColumn}
	 * @return
	 */
	public ${name} getById(${idColumnType} ${idColumn})
	{
		return sqlSessionTemplate.selectOne("${packageName}.model.${name}.GetById", ${idColumn});
	}
}
