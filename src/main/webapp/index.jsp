<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Struts Json  Datatable Demo</title>
	<link rel="stylesheet" href="bootstrap-3.3.6/css/bootstrap.css" />
	<script src="bootstrap-3.3.6/js/jquery-2.2.3.min.js"></script>
	<script src="bootstrap-3.3.6/js/jquery-ui.js"></script>
	<script src="bootstrap-3.3.6/js/jquery.popconfirm.js"></script>
	<script src="bootstrap-3.3.6/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet"
		href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
	<script type="text/javascript"
		src='<c:url value="/resources/lib/bootstrap-3.3.6/js/dataTables.checkboxes.min.js"/>'></script>
	<script type="text/javascript">
		$(document).ready(function(){
			createDataTableAccount();
		});
		
		function createDataTableAccount(){
			var oTable;
			oTable = $('#example').DataTable({
				"lengthMenu": [ 4, 15, 25, 50, 100],
			    "responsive": true,
			    "destroy": true,
			    "paginationType" : "full_numbers",
			    "processing" : false,
			    "serverSide" : false,
			    "bFilter": true,
			    "paging" :true,
			    "bLengthChange": true,
			    "bPaginate": true,
			    "pageLength": 15,
			    "bSort" : true,
			    "autoWidth": true,
			    'columnDefs': [
	                {
	                   'targets': 0,
	                   'checkboxes': {
	                      'selectRow': false
	                   }
	                },
	             ],
	             'select': {
	                'style': 'multi'
	             },
	             'order': [[1, 'asc']],
	             "scrollY":        200,
	             "scrollCollapse": true,
	             "scroller":       true,
	              "ajax" : {
	 		         type: "POST",
	 		     	 url: "getListAccount.action"
	 		     },
// 	             "dom" : '<"toolbar">frt' + "<'row'<'col-sm-8'p><'col-sm-4'l>>",
	             "columns" : [
	            	  {"mData":"id"},
		              {"mData":"username"},
		              {"mData":"password"},
		              {"mData":"description"},
		              {"mData":"image"},
		              {"mData":"birthday", "render": function (data) {
		                  var date = new Date(data);
		                  var month = date.getMonth() + 1;
		                  return (month.length > 1 ? month : month) + "/" + date.getDate() + "/" + date.getFullYear();
		              }}
		            ]
// 		            "fnInitComplete" : function(oSettings, json) {
// 		            	$('.toolbar').append("<div style=\"padding-left:10px\"><input style=\"width:100px\" type=\"button\" value=\"AddEmp\" id=\"btnAggiungi\" onclick=\"addEmployee();\"><input style=\"width:100px; margin-left:10px\" type=\"button\" value=\"Delete\" id=\"btnDelete\" onclick=\"deleteEmployee(${eId});\"><input id=\"btnModifica\" onclick=\"updateEmployee();\" style=\"width:100px; margin-left: 10px;\" disabled=\"disabled\" type=\"button\" value=\"Update\"></div>");
// 		            	showInputTest();
// 				    },
			});
			$('#example tbody').on( 'click', 'input[type="checkbox"]', function () {
		        rowData = oTable.row($(this).parents('tr')).data();
		    });
		}
	</script>
</head>
<body>
	Welcome: ${session.username } | <a href="logout.html">Logout</a>
	<br />
	<br />
	<table id="example">
		<thead>
			<tr>
				<th>Account ID</th>
				<th>Account Name</th>
				<th>Account Pass</th>
				<th>Description</th>
				<th>Image</th>
				<th>Birthday</th>
			</tr>
		</thead>
	</table>
</body>
</html>