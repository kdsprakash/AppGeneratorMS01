package ${packageName}.model;

import com.andromeda.commons.model.BaseModel;

/**
 * 
 * @author ${author}
 * @date ${date}
 *
 */
public class ${name} extends BaseModel
{
	<#list columns as column>
	private ${column.type} ${column.name};
    </#list>

	/**
	 * Constructor method
	 */
	public ${name}()
	{
	}

	/**
	 * Constructor method
	 */
	public ${name}(<#list columns as column>${column.type} ${column.name}<#sep>, </#sep></#list>)
	{
		<#list columns as column>
	    this.${column.name} = ${column.name};
	    </#list>
	}
	
	<#list columns as column>
	/**
	 * Getter method for ${column.name}.
	 * 
	 * @return
	 */
	public ${column.type} get${column.name?cap_first}()
	{
		return ${column.name};
	}

	/**
	 * Setter method for ${column.name}.
	 * 
	 * @param id
	 */
	public void set${column.name?cap_first}(${column.type} ${column.name})
	{
		this.${column.name} = ${column.name};
	}
	</#list>
}
