<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.prova.gestioneparcheggio.model.Parcheggio"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Update</title>

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

	<div class='card'>
		<div class='card-header'>
			<h5>Update parcheggio</h5>
		</div>

		<div class='card-body'>

			<form method="post"
				action="ExecuteUpdateParcheggioServlet?idParcheggio=${update_parcheggio_attr.id}">

				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Nome</label> <input type="text" name="nome" id="nome"
							class="form-control" placeholder="Inserire il nome"
							value="${update_parcheggio_attr.nome}">
					</div>

					<div class="form-group col-md-6">
						<label>Indirizzo</label> <input type="text" name="indirizzo"
							id="cognome" class="form-control"
							placeholder="Inserire l'indirizzo"
							value="${update_parcheggio_attr.indirizzo}">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Orario di apertura</label> <input class="form-control"
							id="orarioChiusura" type="date" placeholder="hh:mm:ss"
							name="orarioApertura"
							value="${update_parcheggio_attr.orarioApertura}">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Orario di chiusura</label> <input class="form-control"
							id="orarioChiusura" type="time" placeholder="hh:mm:ss"
							name="orarioChiusura"
							value="${update_parcheggio_attr.orarioChiusura}">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Giorno di chiusura</label> <input class="form-control"
							id="dataDiNascita" type="date" placeholder="dd/MM/yy"
							title="formato : gg/mm/aaaa" name="giornoChiusura"
							value="${update_parcheggio_attr.giornoChiusura}">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label>Capienza</label> <input type="number" name="capienza"
						id="capienza" class="form-control"
						placeholder="Inserire la capienza"
						value="${update_parcheggio_attr.capienza}">
				</div>

				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Conferma</button>

			</form>
			<!-- end card-body -->
		</div>

	</div>

	<!-- end container --> </main>
	<jsp:include page="../footer.jsp" />

</body>
</html>