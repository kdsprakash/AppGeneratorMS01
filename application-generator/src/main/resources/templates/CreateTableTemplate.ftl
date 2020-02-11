CREATE TABLE ${table.name}
(
    <#list columns as column>
      ${column.name} ${column.type}<#sep>, </#sep>
    </#list>
);
