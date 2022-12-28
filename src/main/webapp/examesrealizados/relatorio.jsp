<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="label.titulo.pagina.consulta"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">	
		<div class="container">
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
					
				</div>				
			</div>

			<div class="row">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th><s:text name="label.id"/></th>
							<th>Codigo Func</th>
							<th>Nome Func</th>
							<th>Codigo Exame</th>
							<th>Nome Exame</th>
							<th>Data</th>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="examesRealizados" >
							<tr>
								<td>${cod}</td>
								<td>${codExame}</td>
								<td>${exame}</td>
								<td>${codFunc}</td>
								<td>${func}</td>
								<td>${data}</td>													
							</tr>
						</s:iterator>
					</tbody>
					
					<tfoot class="table-secondary">
						
					</tfoot>				
				</table>
			</div>

			<div class="row">
			
			</div>
		</div>		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>