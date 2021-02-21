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
			<h5>Inserisci nuovo elemento</h5>
		</div>
		<div class='card-body'>

			<h6 class="card-title">
				I campi con <span class="text-danger">*</span> sono obbligatori
			</h6>

			<form method="post" action="ExecuteInsertParcheggioServlet">

				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Nome <span class="text-danger">*</span></label> <input
							type="text" name="nome" id="nome" class="form-control"
							placeholder="Inserire il nome" required>
					</div>

					<div class="form-group col-md-6">
						<label>Indirizzo <span class="text-danger">*</span></label> <input
							type="text" name="indirizzo" id="indirizzo" class="form-control"
							placeholder="Inserire l'indirizzo" required>
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-3">
						<label>Orario di apertura <span class="text-danger">*</span></label>
						<input class="form-control" id="orarioApertura" type="date" placeholder="HH:mm" name="orarioApertura"
							required>
					</div>
					<div class="form-group col-md-3">
						<label>Orario di chiusura <span class="text-danger">*</span></label>
						<input class="form-control" id="orarioChiusura" type="date" placeholder="HH:mm" name="orarioChiusura"
							required>
					</div>
					<div class="form-group col-md-3">
						<label>Giorno di chiusura <span class="text-danger">*</span></label>
						<input class="form-control" id="giornoChiusura" type="date"
							placeholder="dd/MM/yy" title="formato : gg/mm/aaaa"
							name="giornoChiusura" required>
					</div>
					<div class="form-group col-md-6">
						<label>Capienza</label> <input type="number" class="form-control"
							name="capienza" id="capienza" placeholder="Inserire la capienza">
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