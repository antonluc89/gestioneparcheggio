package it.prova.raccoltafilm.web.servlet.parcheggio;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneparcheggio.model.Parcheggio;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.utility.UtilityForm;

@WebServlet("/ExecuteSearchParcheggioServlet")
public class ExecuteSearchParcheggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeParam = request.getParameter("nome");
		String indirizzoParam = request.getParameter("indirizzo");
		String orarioAperturaParam = request.getParameter("orarioApertura");
		String orarioChiusuraParam = request.getParameter("orarioChiusura");
		String giornoChiusuraParam = request.getParameter("giornoChiusura");
		String capienzaParam = request.getParameter("capienza");

		LocalTime orarioAperturaParsed = UtilityForm.parseTimeEntryFromString(orarioAperturaParam);

		LocalTime orarioChiusuraParsed = UtilityForm.parseTimeEntryFromString(orarioChiusuraParam);

		Date giornoChiusuraParsed = UtilityForm.parseDateEntryFromString(giornoChiusuraParam);

		Integer capienzaParsed = UtilityForm.parseIntegerFromString(capienzaParam);

		Parcheggio parcheggioExample = new Parcheggio(nomeParam, indirizzoParam, orarioAperturaParsed,
				 orarioChiusuraParsed, giornoChiusuraParsed, capienzaParsed);

		try {
			request.setAttribute("parcheggi_list_attribute",
					MyServiceFactory.getParcheggioServiceInstance().findByExample(parcheggioExample));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/parcheggio/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("parcheggio/list.jsp").forward(request, response);
	}

}
