<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserimento</title>

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
			<h5>Inserisci nuova automobile</h5>
		</div>
		<div class='card-body'>

			<form method="post" action="ExecuteInsertAutomobileServlet">

				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Marca</label> <input type="text" name="marca" id="marca"
							class="form-control" placeholder="Inserire la marca">
					</div>

					<div class="form-group col-md-6">
						<label>Modello</label> <input type="text" name="modello"
							id="modello" class="form-control"
							placeholder="Inserire il modello">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label>Targa</label> <input type="text" name="targa" id="targa"
						class="form-control" placeholder="Inserire la targa">
				</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Orario di stampa ticket</label> <input class="form-control"
					id="orarioStampaTicket" type="date" placeholder="hh:mm:ss"
					title="formato : hh:mm:ss" name="orarioStampaTicket">
			</div>
			<div class="form-group col-md-6">
				<label>Durata ticket (minuti)</label> <input type="number"
					class="form-control" name="minutiDurataTicket"
					id="minutiDurataTicket" placeholder="Inserire la durata">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="parcheggio.id">Parcheggio</label> <select
					class="form-control" id="parcheggio.id" name="parcheggio.id">
					<option value="">-- Seleziona il parcheggio --</option>
					<c:forEach items="${parcheggi_list_attribute }"
						var="parcheggioItem">
						<option value="${parcheggioItem.id}">${parcheggioItem.nome }
							${parcheggioItem.indirizzo }</option>
					</c:forEach>
				</select>
			</div>
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