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
					<s:form action="/relatoriosRelatorios.action">
						<div class="input-group">
								<s:text name="label.data.inicio" />
								<s:textfield  id="data" name="dtInicio"  type="date" format="yyyy-MM-dd"  /><!-- <id="dtInicio" name="datai"-->
								<!--<s:textfield cssClass="form-control" id="inicio" name="inicio" type="date"  />-->
								<s:text name="label.data.fim" />
								<s:textfield  id="data" name="dtFim"  type="date" format="yyyy-MM-dd" />
								<!--<s:textfield cssClass="form-control" id="fim" name="fim" type="date"  />-->
								<button class="btn btn-primary" type="submit"><s:text name="label.pesquisar"/></button>
						</div>
					</s:form>			
				</div>				
			</div>
		</div>		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>