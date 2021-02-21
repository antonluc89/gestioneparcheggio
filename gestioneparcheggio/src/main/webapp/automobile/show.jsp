<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Visualizza elemento</title>

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
				<dd class="col-sm-9">${show_automobile_attr.id}</dd>
			</dl>

			<dl class="row">
				<dt class="col-sm-3 text-right">Marca:</dt>
				<dd class="col-sm-9">${show_automobile_attr.marca}</dd>
			</dl>

			<dl class="row">
				<dt class="col-sm-3 text-right">Modello:</dt>
				<dd class="col-sm-9">${show_automobile_attr.modello}</dd>
			</dl>

			<dl class="row">
				<dt class="col-sm-3 text-right">Targa:</dt>
				<dd class="col-sm-9">${show_automobile_attr.targa}</dd>
			</dl>

			<dl class="row">
				<dt class="col-sm-3 text-right">Orario stampa ticket:</dt>
				<dd class="col-sm-9">
					<fmt:formatDate type="time"
						value="${show_automobile_attr.orarioStampaTicket}" />
				</dd>
			</dl>

			<dl class="row">
				<dt class="col-sm-3 text-right">Durata ticket:</dt>
				<dd class="col-sm-9">${show_automobile_attr.minutiDurataTicket}</dd>
			</dl>

			<!-- info Parcheggio -->
			<p>
				<a class="btn btn-primary btn-sm" data-toggle="collapse"
					href="#collapseExample" role="button" aria-expanded="false"
					aria-controls="collapseExample"> Info Parcheggio</a>
			</p>
			<div class="collapse" id="collapseExample">
				<div class="card card-body">
					<dl class="row">
						<dt class="col-sm-3 text-right">Nome:</dt>
						<dd class="col-sm-9">${show_parcheggio_attr.parcheggio.nome}</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Indirizzo:</dt>
						<dd class="col-sm-9">${show_film_parcheggio.parcheggio.indirizzo}</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Orario di chiusura:</dt>
						<dd class="col-sm-9">${show_parcheggio_attr.parcheggio.orarioChiusura}</dd>
					</dl>

				</div>
			</div>
			<!-- end info Parcheggio -->

		</div>

		<div class='card-footer'>
			<a href="ExecuteListAutomobileServlet" class='btn btn-outline-secondary'
				style='width: 80px'> <i class='fa fa-chevron-left'></i> Back
			</a>
		</div>
	</div>



	<!-- end main container --> </main>
	<jsp:include page="../footer.jsp" />

</body>
</html>