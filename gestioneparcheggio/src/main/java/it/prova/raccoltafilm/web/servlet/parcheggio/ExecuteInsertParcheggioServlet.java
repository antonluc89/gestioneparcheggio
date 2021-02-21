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

@WebServlet("/ExecuteInsertParcheggioServlet")
public class ExecuteInsertParcheggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeParam = request.getParameter("nome");
		String indirizzoParam = request.getParameter("indirizzo");
		String orarioAperturaParam = request.getParameter("orarioApertura");
		String orarioChiusuraParam = request.getParameter("orarioChiusura");
		String giornoChiusuraParam = request.getParameter("giornoChiusura");
		String capienzaParam = request.getParameter("capienza");

		Date orarioAperturaParsed = UtilityForm.parseTimeEntryFromString(orarioAperturaParam);

		Date orarioChiusuraParsed = UtilityForm.parseTimeEntryFromString(orarioChiusuraParam);

		Date giornoChiusuraParsed = UtilityForm.parseDateEntryFromString(giornoChiusuraParam);

		Integer capienzaParsed = UtilityForm.parseIntegerFromString(capienzaParam);

		if (!UtilityForm.validateParcheggioFormInput(nomeParam, indirizzoParam, orarioAperturaParam,
				orarioChiusuraParam, giornoChiusuraParam, capienzaParam) || giornoChiusuraParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/parcheggio/insert.jsp").forward(request, response);
			return;
		}

		Parcheggio parcheggioInstance = new Parcheggio(nomeParam, indirizzoParam, orarioAperturaParsed,
				orarioChiusuraParsed, giornoChiusuraParsed, capienzaParsed);

		try {

			MyServiceFactory.getParcheggioServiceInstance().inserisciNuovo(parcheggioInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/parcheggio/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListParcheggioServlet?operationResult=SUCCESS");

	}

}
