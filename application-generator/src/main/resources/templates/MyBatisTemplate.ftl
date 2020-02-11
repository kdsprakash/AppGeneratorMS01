<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.model.${name}">

	<insert id="Add" parameterType="map">
		INSERT INTO ${tableName} 
		(
			<#list columns as column>
			${column.name}<#sep>, </#sep>
			</#list>
		)
		VALUES 
		(
			<#list columns as column>
			${r"#{p"}.${column.name}${r"}"}<#sep>, </#sep>
			</#list>
		)
	</insert>
	
	<update id="Update" parameterType="map">
		UPDATE ${tableName}
		SET 
			<#list columns as column>
			${column.name} = ${r"#{p"}.${column.name}${r"}"}<#sep>, </#sep>
			</#list>
		WHERE ${idColumn} = ${r"#{p"}.${idColumn}${r"}"}
	</update>

	<delete id="Delete" parameterType="${idColumnType}">
		DELETE FROM ${tableName}
		WHERE ${idColumn} = ${r"#{"}${idColumn}${r"}"}
	</delete>
	
	<select id="Get" resultType="${packageName}.model.${name}" parameterType="map">
		SELECT * FROM ${tableName}
	</select>
	
	<select id="GetById" resultType="${packageName}.model.${name}" parameterType="${idColumnType}">
		SELECT * FROM ${tableName} WHERE ${idColumn} = ${r"#{"}${idColumn}${r"}"}
	</select>

</mapper>
