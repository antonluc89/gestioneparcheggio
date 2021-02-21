<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Visualizza parcheggio</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

	<div class='card'>
		<div class='card-header'>Visualizza dettaglio</div>

		<div class='card-body'>
			<dl class="row">
				<dt class="col-sm-3 text-right">Id:</dt>
				<dd class="col-sm-9">${show_parcheggio_attr.id}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Nome:</dt>
				<dd class="col-sm-9">${show_parcheggio_attr.nome}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Indirizzo:</dt>
				<dd class="col-sm-9">${show_parcheggio_attr.indirizzo}</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Orario di apertura:</dt>
				<dd class="col-sm-9">
					<fmt:formatDate type="time"
						value="${show_parcheggio_attr.orarioApertura}" />
				</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Orario di chiusura:</dt>
				<dd class="col-sm-9">
					<fmt:formatDate type="time"
						value="${show_parcheggio_attr.orarioChiusura}" />
				</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Giorno di chiusura:</dt>
				<dd class="col-sm-9">
					<fmt:formatDate type="date"
						value="${show_parcheggio_attr.giornoChiusura}" />
				</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Capienza:</dt>
				<dd class="col-sm-9">${show_parcheggio_attr.capienza}</dd>
			</dl>
		</div>

		<!-- end info Regista -->



		<div class='card-footer'>
			<a href="ExecuteListParcheggioServlet"
				class='btn btn-outline-secondary' style='width: 80px'> <i
				class='fa fa-chevron-left'></i> Back
			</a>
		</div>
	</div>



	<!-- end main container --> </main>
	<jsp:include page="../footer.jsp" />

</body>
</html>