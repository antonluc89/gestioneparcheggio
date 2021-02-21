<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.prova.gestioneparcheggio.model.Automobile"%>
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
			<h5>Update automobile</h5>
		</div>

		<div class='card-body'>

			<form method="post"
				action="ExecuteUpdateAutomobileServlet?idAutomobile=${update_automobile_attr.id}">

				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Marca</label> <input type="text" name="marca" id="marca"
							class="form-control" placeholder="Inserire la marca"
							value="${update_automobile_attr.marca}">
					</div>

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Modello</label> <input type="text" name="modello"
								id="modello" class="form-control"
								placeholder="Inserire il modello"
								value="${update_automobile_attr.modello}">
						</div>

						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Targa</label> <input type="text" name="targa" id="targa"
									class="form-control" placeholder="Inserire la targa"
									value="${update_automobile_attr.targa}">
							</div>

							<div class="form-row">
								<div class="form-group col-md-6">
									<label>Orario stampa ticket</label> <input class="form-control"
										id="orarioStampaTicket" type="time" placeholder="HH:mm:ss"
										min="08:00:00" max="20:00:00" name="orarioStampaTicket"
										value="${update_automobile_attr.orarioStampaTicket}">
								</div>
								<div class="form-group col-md-6">
									<label>Durata (minuti)</label> <input type="number"
										class="form-control" name="minutiDurataTicket"
										id="minutiDurataTicket"
										placeholder="Inserire la durata del ticket"
										value="${update_automobile_attr.minutiDurataTicket}">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="parcheggio.id">Parcheggio</label> <select
										class="form-control" id="parcheggio.id" name="parcheggio.id">
										<option value="">-- Selezionare un parcheggio --</option>
										<c:forEach items="${parcheggi_list_attribute }"
											var="parcheggioItem">
											<option value="${parcheggioItem.id}">${parcheggioItem.nome }
												${parcheggioItem.Indirizzo }</option>
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