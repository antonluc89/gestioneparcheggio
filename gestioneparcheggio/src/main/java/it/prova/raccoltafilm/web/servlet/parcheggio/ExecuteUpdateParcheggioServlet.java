package it.prova.raccoltafilm.web.servlet.parcheggio;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneparcheggio.model.Parcheggio;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.utility.UtilityForm;

@WebServlet("/ExecuteUpdateParcheggioServlet")
public class ExecuteUpdateParcheggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParcheggioParam = request.getParameter("idParcheggio");
		String nomeParam = request.getParameter("nome");
		String indirizzoParam = request.getParameter("indirizzo");
		String orarioAperturaParam = request.getParameter("orarioApertura");
		String orarioChiusuraParam = request.getParameter("orarioChiusura");
		String giornoChiusuraParam = request.getParameter("giornoChiusura");
		String capienzaParam = request.getParameter("capienza");

		Long idParsedParcheggio = UtilityForm.parseIdEntryToLongFromString(idParcheggioParam);

		Date orarioAperturaParsed = UtilityForm.parseTimeEntryFromString(orarioAperturaParam);

		Date orarioChiusuraParsed = UtilityForm.parseTimeEntryFromString(orarioChiusuraParam);

		Date giornoChiusuraParsed = UtilityForm.parseDateEntryFromString(giornoChiusuraParam);

		Integer capienzaParsed = UtilityForm.parseIntegerFromString(capienzaParam);

		if (!UtilityForm.validateParcheggioFormInput(nomeParam, indirizzoParam, orarioAperturaParam,
				orarioChiusuraParam, giornoChiusuraParam, capienzaParam)) {
			request.setAttribute("errorMessage", "Errori nella validazione");
			request.getRequestDispatcher("/parcheggio/update.jsp").forward(request, response);
			return;
		}

		Parcheggio parcheggioUpdateInstance;

		try {
			parcheggioUpdateInstance = MyServiceFactory.getParcheggioServiceInstance()
					.caricaSingoloElementoById(idParsedParcheggio);
			parcheggioUpdateInstance.setNome(nomeParam);
			parcheggioUpdateInstance.setIndirizzo(indirizzoParam);
			parcheggioUpdateInstance.setOrarioApertura(orarioAperturaParsed);
			parcheggioUpdateInstance.setOrarioChiusura(orarioChiusuraParsed);
			parcheggioUpdateInstance.setGiornoChiusura(giornoChiusuraParsed);
			parcheggioUpdateInstance.setCapienza(capienzaParsed);

			MyServiceFactory.getParcheggioServiceInstance().aggiorna(parcheggioUpdateInstance);

			request.setAttribute("parcheggi_list_attribute",
					MyServiceFactory.getAutomobileServiceInstance().listAllElements());
			
			request.setAttribute("successMessage", "Operazione di aggiornamento effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/parcheggio/update.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListParcheggioServlet?operationResult=SUCCESS");
	}
}
