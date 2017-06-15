<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Struts Json  Datatable Demo</title>
	<style>
	#example_paginate {
		float: inherit;
	}
	
	#example_length {
		float: right;
		margin-top: 10px;
	}
	</style>
	<link rel="stylesheet" href="bootstrap-3.3.6/css/bootstrap.css" />
	<script src="bootstrap-3.3.6/js/jquery-2.2.3.min.js"></script>
<%-- 	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script> --%>
	<script src="bootstrap-3.3.6/js/jquery-ui.js"></script>
	<script src="bootstrap-3.3.6/js/jquery.popconfirm.js"></script>
	<script src="bootstrap-3.3.6/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet"
		href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
<%-- 	<script type="text/javascript" --%>
<%-- 		src="bootstrap-3.3.6/js/dataTables.checkboxes.min.js"></script> --%>
	<script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.7/js/dataTables.checkboxes.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			createDataTableAccount();
			$.post('getListAccount.html').done(function(data){
				console.log(data);
			}).fail(function(){
				alert("huhu");
			});
		});
		var rowData = null;
		function createDataTableAccount(){
			var oTable;
			oTable = $('#example').DataTable({
				"lengthMenu": [15, 25, 50, 100],
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
// 	             "scrollY":        200,
// 	             "scrollCollapse": true,
// 	             "scroller":       true,
	             "ajax" : {
	 		         "type": "POST",
	 		     	 "url": "getListAccount.html"
	 		     },
	             "dom" : '<"toolbar">frt' + "<'row'<'col-sm-8'p><'col-sm-4'l>>",
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
		            ],
		            "fnInitComplete" : function(oSettings, json) {
		            	$('.toolbar').append("<div style=\"padding-left:10px\"><input style=\"width:100px\" type=\"button\" value=\"AddUser\" id=\"btnAggiungi\" onclick=\"addEmployee();\"><input style=\"width:100px; margin-left:10px\" type=\"button\" value=\"Delete\" id=\"btnDelete\" onclick=\"deleteEmployee();\"><input style=\"width:100px; margin-left:10px;\" type=\"button\" value=\"UpdateUser\" id=\"btnModifica\" onclick=\"updateEmployee();\"></div>");
				    },
			});
			$('#example tbody').on( 'click', 'input[type="checkbox"]', function () {
		        rowData = oTable.row($(this).parents('tr')).data();
		        console.log("rowData===" + rowData);
		    });
		}
		
		function addUsers(){
			$.post("saveUser.html", {
				_username: $('#username').val(),
				_pass: $('#password').val(),
				_des: $('#description').val(),
				_image: $('#image').val(),
				_birthday: $('#birthday').val(),
	        }).done(function(data) {	
	        	createDataTableAccount();
	        	$('#detailModifica').modal('hide');
		    }).fail(function() {
	        	$('#detailModifica').modal('hide');
		    });
	 	}
		
		function addEmployee() {
			$('#username').val("");
			$('#password').val("");
			$('#description').val("");
			$('#image').val("");
			$('#birthday').val("");
			$('#modificaButton').on('click', addUsers);
			$('#detailModifica').modal('show');
		}
		
		function updateUsers(){
			$.post("saveUser.html", {
				_id: rowData.id,
				_username: $('#username').val(),
				_pass: $('#password').val(),
				_des: $('#description').val(),
				_image: $('#image').val(),
				_birthday: $('#birthday').val(),
	        }).done(function(data) {	
	        	createDataTableAccount();
	        	$('#detailModifica').modal('hide');
		    }).fail(function() {
	        	$('#detailModifica').modal('hide');
		    });
	 	}
		
		function updateEmployee() {
			$('#username').val(rowData.username);
			$('#password').val(rowData.password);
			$('#description').val(rowData.description);
			$('#image').val(rowData.image);
			$('#birthday').val(rowData.birthday);
			$('#modificaButton').on('click', updateUsers);
			$('#detailModifica').modal('show');
		}
		
		function onClickChiudi_modifica() {
			$('#detailModifica').modal('hide');
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
				<th></th>
				<th>Account Name</th>
				<th>Account Pass</th>
				<th>Description</th>
				<th>Image</th>
				<th>Birthday</th>
			</tr>
		</thead>
	</table>
	
	<!--  ========================== Modifica======================= -->
		<div aria-hidden="true" aria-labelledby="detailModifica" role="dialog"
			tabindex="-1" id="detailModifica" class="modal fade"
			style="display: none;">
			<div class="modal-dialog">

				<div class="modal-content">
					<div class="modal-header">
						<button aria-hidden="true" data-dismiss="modal" class="close"
							type="button">×</button>
						<h4 id="myModalLabelModifica" class="modal-title">Modifica</h4>
					</div>
					<div class="modal-body">
						<!-- <div class="col-lg-12" style="text-align: left;"> -->

						<div class="panel-body" style="border: 0px;">
							<div class="dataTable_wrapper">
								<div id="dati_form_modifica">
									<div class="row">

										<fieldset id="modificaAggiungi">
											
											<div class="row" style="text-align: left;">
												<div class="col-lg-4 col-md-4 col-sm-4"
													style="margin-right: 100px; margin-left: 50px;">
													<b>Name: </b><br />
												    <input type="text" id="username" name="username" class="form-control"></input>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4"
													style="margin-right: 100px; margin-left: 50px;">
													<b>Password: </b><br />
												    <input type="password" id="password" name="password" class="form-control"></input>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4"
													style="margin-right: 100px; margin-left: 50px;">
													<b>Description: </b><br />
												    <input type="text" id="description" name="description" class="form-control"></input>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4"
													style="margin-right: 100px; margin-left: 50px;" class="form-control">
													<b>Image: </b><br />
												    <input type="text" id="image" name="image" class="form-control"></input>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4"
													style="margin-right: 100px; margin-left: 50px;">
													<b>Birthday: </b><br />
												    <input type="text" id="birthday" name="birthday" class="form-control"></input>
												</div>

											</div>
											<br/>
										</fieldset>
									</div>
								</div>
							</div>
						</div>
						<!-- </div> -->						
					</div>
					<div class="modal-footer" style="border: 0px;">
							<div style="padding-left: 0px;">
								<div class="tr-dettaglio" style="margin-top: 5px;">
									<button id="modificaButton" onclick=""
										style="margin-right:0px; float: right;">Modifica</button>
								</div>
								<button id="callAnnullaModificaButton" onclick="onClickChiudi_modifica()" type="button" style="float: left;">Cancel</button>
							</div>
						</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
		</div>
		<!--  ==========================End Modifica ======================= -->
</body>
</html>