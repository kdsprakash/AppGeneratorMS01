<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="Andromeda Framework">
	<meta name="author" content="${author}">
	
	<title>Andromeda</title>
	
	<!-- Bootstrap core CSS -->
	<link href="../../common/css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="../../common/css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
		
	<script src="../../common/scripts/lib/jquery-2.1.1.js"></script>
	<script src="../../common/scripts/lib/bootstrap.min.js"></script>
	<script src="../../common/scripts/lib/angular/angular.js"></script>	
	<script src="../../common/scripts/andromeda/AndromedaConstants.js"></script>	
</head>
<body>
	
	<script src="scripts/${name}App.js"></script>
	<script src="scripts/${name}Controller.js"></script>

	<div ng-app="${name}App" ng-controller="${name}Controller" id="${name}App">
		<h1>${name}s</h1>

		<!-- Modal -->
		<div class="modal fade" id="${name}Modal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">${name} Details</h4>
					</div>
					<div class="modal-body">
						<form role="form" name="dataForm">
							<#list columns as column>
							<div class="form-group">
								<label for="${column.name}">${column.name?cap_first}:</label>
								<input type="text" class="form-control" name="${column.name}" ng-model="formData.${column.name}" pattern="[A-Za-z0-9]*" required>
								<span ng-show="dataForm.${column.name}.$touched && dataForm.${column.name}.$invalid" class="text-danger">${column.name?cap_first} is required</span>
							</div>
							</#list>
						</form>
					</div>
					<div class="modal-footer">
						<table>
							<tr>
								<td><div id="addButtonDiv"><button type="button" class="btn btn-default" ng-click="add(formData);" ng-disabled="<#list columns as column>dataForm.${column.name}.$invalid<#sep> || </#sep></#list>">Save</button>&nbsp;&nbsp;</div></td>
								<td><div id="editButtonDiv"><button type="button" class="btn btn-default" ng-click="update(formData);" ng-disabled="<#list columns as column>dataForm.${column.name}.$invalid<#sep> || </#sep></#list>">Save</button>&nbsp;&nbsp;</div></td>
								<td><div><button type="button" class="btn btn-default" data-dismiss="modal">Close</button></div></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>

		<p>Selected {{(data|filter:search).length}} of {{ data.length }} <button type="button" class="btn btn-success" data-toggle="modal" data-target="#${name}Modal" ng-click="showAddForm();">Add</button></p>
		<div class="table-responsive">          
			<table class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<#list columns as column>
      					<th>${column.name?cap_first}</th>
    					</#list>
						<th>Actions</th>
					</tr>
					<tr>
						<#list columns as column>
      					<td><input type="text" ng-model="search.${column.name}" placeholder="${column.name?cap_first}"></td>
    					</#list>
						<td>&nbsp;</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="d in data | filter:search">
						<#list columns as column>
      					<td>{{ d.${column.name} }}</td>
    					</#list>
						<td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#${name}Modal" ng-click="showEditForm(d.${idColumn});">Edit</button>&nbsp;
							<button type="button" class="btn btn-danger" ng-click="delete(d.${idColumn}); getAll();">Delete</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<script language="JavaScript">
		jQuery(document).ready(function() {
			angular.element(document.getElementById('${name}App')).scope().getAll();
		});
	</script>
</body>
</html>
