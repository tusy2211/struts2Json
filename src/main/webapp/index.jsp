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
	
	#modalLoadingGenericoBody
	{
		min-height: 50px;
		padding:20px;
	}
	#modalLoadingGenericoContent
	{
		max-width:550px;
	
	}
	#modalLoadingGenericoText
	{
		text-align: left;
	}
	#modalLoadingGenericoDialog
	{
		top:250px;
	}
	
	#modalConfirmPopupBody
	{
		min-height: 100px;
		padding:20px;
	}
	#modalConfirmPopupContent
	{
		max-width:550px;
	
	}
	#modalConfirmPopupText
	{
		margin: auto;
		text-align: center;
	}
	#modalConfirmPopupDialog
	{
		top:250px;
	}
	
	a.exportExcelButton{
		margin-left:5px;
		background: url('images/icon-excel-small.png') no-repeat;
		text-decoration: none;
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
		var dataCommissioniContestazioni = [];
		$(document).ready(function(){
			createDataTableAccount();
			$.post('getListAccount.html').done(function(data){
				console.log(data);
			}).fail(function(){
				alert("huhu");
			});
			
			$('#example tbody').on('change','input[type="checkbox"]',function() {
				var rows = tableSelect.rows({ 'search' : 'applied'}).nodes();
				dataCommissioniContestazioni = [];
				for (var i = 0; i < $(rows).find("input:checked").length; i++) { 
					convertDataCommissioniTotal(tableSelect, i);
				}
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
		            	$('.toolbar').append("<div style=\"padding-left:10px\"><input style=\"width:100px\" type=\"button\" value=\"AddUser\" id=\"btnAggiungi\" class=\"btn btn-success\" onclick=\"addEmployee();\"><input style=\"width:100px; margin-left:10px\" type=\"button\" value=\"Delete\" class=\"btn btn-info\" id=\"btnDelete\" onclick=\"comfirmDelete();\"><input style=\"width:100px; margin-left:10px;\" type=\"button\" value=\"UpdateUser\" id=\"btnModifica\" class=\"btn btn-danger\" onclick=\"updateEmployee();\"></div>");
		            	$('#example_filter').append("<a title=\"Esporta tutti i record in formato excel\" class=\"exportExcelButton\" href=\"#\" onclick=\"exportExcelElencoPuntiVendita('export_excel_elenco_punti_vendita.html')\"> &nbsp &nbsp &nbsp</a>");
				    },
			});
			$('#example tbody').on( 'click', 'input[type="checkbox"]', function () {
		        rowData = oTable.row($(this).parents('tr')).data();
		        console.log("rowData===" + rowData);
		    });
		}
		
		function addUsers(){
			$.post("saveUser.html", {
				_id: null,
				_username: $('#username').val(),
				_pass: $('#password').val(),
				_des: $('#description').val(),
				_image: $('#image').val(),
				_birthday: $('#birthday').val(),
	        }).done(function(data) {	
	        	$('#detailModifica').modal('hide');
	        	createDataTableAccount();
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
			setTimeout(function(){ showLoading(); }, -1000000);
			$.post("saveUser.html", {
				_id: rowData.id,
				_username: $('#username').val(),
				_pass: $('#password').val(),
				_des: $('#description').val(),
				_image: $('#image').val(),
				_birthday: $('#birthday').val(),
	        }).done(function(data) {
	        	setTimeout(function(){ closeLoading(); }, -1000000);
	        	$('#detailModifica').modal('hide');
	        	createDataTableAccount();
		    }).fail(function() {
	        	$('#detailModifica').modal('hide');
		    });
	 	}
		
		function updateEmployee() {
			var tableSelect = $('#example').DataTable();
			var rows = tableSelect.rows({ 'search' : 'applied'}).nodes();
			if ($(rows).find("input:checked").length > 0) {
				if ($(rows).find("input:checked").length >= 2) {
					alert('Just choose only one record');
					return 0;
				}
				$('#username').val(rowData.username);
				$('#password').val(rowData.password);
				$('#description').val(rowData.description);
				$('#image').val(rowData.image);
				$('#birthday').val(rowData.birthday);
				$('#modificaButton').on('click', updateUsers);
				$('#detailModifica').modal('show');
			} else {
				alert('No records selected');
			}
		}
		function comfirmDelete() {
			showConfirmPopup("Do u want to delete record?", deleteEmployee);
		}
		
		var tableSelect = $('#example').DataTable();
		function deleteEmployee() {
			var tableSelect = $('#example').DataTable();
			var rows = tableSelect.rows({ 'search' : 'applied'}).nodes();
			if ($(rows).find("input:checked").length > 0) {
				if ($(rows).find("input:checked").length >= 2) {
					alert('Just choose only one record');
					return 0;
				}
				$.post("delUser.html", {
					idAccount: rowData.id
		        });
			} else {
				alert('No records selected');
			}
		}
		
		
		function convertDataCommissioniTotal(tableSelect, i) {
			
			var coll1_value=tableSelect.rows({'search' : 'applied'}).data()[i].id;
		    var obj={};
		    obj['id']=coll1_value;
		    dataCommissioniContestazioni.push(obj);
		    console.log("test" +dataCommissioniContestazioni);
		}
		
		function onClickChiudi_modifica() {
			$('#detailModifica').modal('hide');
		}
		
		function showLoading()
		{
			$("#modalLoadingGenerico").modal('show');
		}

		function closeLoading()
		{
			$("#modalLoadingGenerico").modal('hide');
		}
		
		var functionToConfirm = null;

		function showConfirmPopup(textConfirm, functionToExecute)
		{

			$("#modalConfirmPopupTitle").html("Confirm Delete");
			$("#modalConfirmPopupText").html(textConfirm);
			
			$("#modalConfirmPopup").modal('show');
			
			functionToConfirm = functionToExecute;
		}

		function executeConfirm()
		{
			$("#modalConfirmPopup").modal('hide');
			functionToConfirm();
		}
		
		function exportExcelElencoPuntiVendita(action){
			var tableSelect = $('#example').DataTable();
			var table = tableSelect.rows( { filter : 'applied'} );	
			alert(table.data().length);
			exportExcelTable(table , action, "error");
		}
		
		function exportExcelTable(table , action, errorText){
			if(table.data().length!=0){
				debugger;
				var tokenRce = new Date().getTime();
				var dataOfTable = JSON.stringify(table.data().toArray());
				var thisForm = document.createElement('form');
				thisForm.style.display = 'none';
				document.body.appendChild(thisForm);
				var listDataSelected = document.createElement('input');
				listDataSelected.type = 'hidden';
				listDataSelected.name = 'listDataSelected';
				listDataSelected.value = dataOfTable;
				thisForm.appendChild(listDataSelected);
				
				thisForm.method = 'POST';	
				thisForm.action = action+"?tokenRce=" + tokenRce;
				debugger;
				var formJquery = $(thisForm);
				$("#postiframe").remove();
				var iframe = $('<iframe name="postiframe" id="postiframe" style="display: none" />');
				$("body").append(iframe);
				formJquery.attr("target", "postiframe");
				formJquery.submit();
				alert('aaaaaa');
			    iframe.load(function(data) {
			    	var resp= $('#postiframe').contents().find('#result').html();
			    	if(resp=="KO"){
			    		alert('KO');
			    		showAlert(errorText);
			    	}
			    });
			    alert('hoho');
			    // wait response from download file
				showLoading();
				alert('showLoading');
			    var pollDownload = setInterval(function() {
			    	debugger;
			        if (document.cookie.indexOf("downloadRce=" + tokenRce) > -1) {
			            document.cookie = "downloadRce=" + tokenRce + "; expires=" + new Date(0).toGMTString() + "; path=/";
						closeLoading();
						alert('closeLoading');
			            clearInterval(pollDownload);
			        }
			    }, 500); 
			   
				document.body.removeChild(thisForm);
			}
		}
	</script>
</head>
<body>
	Welcome: ${session.username } | <a href="logout.html">Logout</a>
	<br />
	<br />
	<div class="table-responsive">
		<table id="example" class="table table-hover">
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
	</div>
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

	<!--  ==========================Start Popup loading ======================= -->
	<div class="panel-body">
		<!-- Modal -->
		<div aria-hidden="true" aria-labelledby="modalLoadingGenerico"
			role="dialog" tabindex="-1" id="modalLoadingGenerico"
			class="modal fade" style="display: none;" data-backdrop="static"
			data-keyboard="false">
			<div class="modal-dialog" id="modalLoadingGenericoDialog">
				<div class="modal-content" id="modalLoadingGenericoContent">
					<div class="modal-header">

						<h4 id="modalLoadingGenericoTitle" class="modal-title">Elaborazione
							in corso...</h4>
					</div>
					<div class="modal-body" id="modalLoadingGenericoBody">
						<div class="row" style="text-align: center">
							<div class="col-lg-12">
								<img src="images/loading_blue.gif"
									title="Elaborazione in corso...." /> <span
									style="margin-left: 15px">Operazione in corso </span>
							</div>
						</div>
					</div>
					<div class="modal-footer"></div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>
	<!--  ==========================End Popup loading ======================= -->

	<!--  ==========================Start popup confirm delete ======================= -->
	<div class="panel-body">
		<!-- Modal -->
		<div aria-hidden="true" aria-labelledby="modalConfirmPopup"
			role="dialog" tabindex="-1" id="modalConfirmPopup" class="modal fade"
			style="display: none;">
			<div class="modal-dialog" id="modalConfirmPopupDialog">
				<div class="modal-content" id="modalConfirmPopupContent">
					<div class="modal-header">
						<button aria-hidden="true" data-dismiss="modal" class="close"
							type="button">×</button>
						<h4 id="modalConfirmPopupTitle" class="modal-title">Confirm Delete</h4>
					</div>
					<div class="modal-body" id="modalConfirmPopupBody">
						<span id="modalConfirmPopupText">Lorem ipsum </span>
					</div>
					<div class="modal-footer">
						<button type="button" id="confirmButton"
							onclick="executeConfirm();">
							<b>Continue</b>
						</button>
						<button data-dismiss="modal" type="button">Cancel</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>
	<!--  ==========================End popup confirm delete ======================= -->
	
</body>
</html>