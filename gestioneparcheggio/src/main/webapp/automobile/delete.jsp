<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="it.prova.gestioneparcheggio.model.Automobile"%>
<html>
<head>
<jsp:include page="../header.jsp" />
<title>Elimina</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

	<div
		class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
		role="alert">
		${errorMessage}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>


	<div class="card">
		<h5 class="card-header">Elimina automobile</h5>
		<div class="card-body">
			<form method="post"
				action="ExecuteDeleteAutomobileServlet?idAutomobile=${delete_automobile_attr.id}">
				<h5 class="card-title">Stai per rimuovere definitivamente
					l'automobile con ID: ${delete_automobile_attr.id}</h5>
				<p class="card-text">Vuoi procedere nell'eliminazione?</p>
				<button type="submit" name="submit" id="submit"
					class="btn btn-danger">Delete</button>
			</form>
		</div>
	</div>

	</main>

	<jsp:include page="../footer.jsp" />
</body>
</html>