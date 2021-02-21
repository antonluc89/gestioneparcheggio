package it.prova.raccoltafilm.web.servlet.automobile;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneparcheggio.model.Automobile;
import it.prova.gestioneparcheggio.model.Parcheggio;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.utility.UtilityForm;

@WebServlet("/ExecuteInsertAutomobileServlet")
public class ExecuteInsertAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String marcaParam = request.getParameter("marca");
		String modelloParam = request.getParameter("modello");
		String targaParam = request.getParameter("targa");
		String orarioStampaTicketParam = request.getParameter("orarioStampaTicket");
		String minutiDurataTicketParam = request.getParameter("minutiDurataTicket");
		String parcheggioIdParam = request.getParameter("parcheggio.id");

		Long idParcheggioParsed = UtilityForm.parseIdEntryToLongFromString(parcheggioIdParam);

		Date orarioStampaTicketParsed = UtilityForm.parseTimeEntryFromString(orarioStampaTicketParam);

		Integer minutiDurataTicketParsed = UtilityForm.parseIntegerFromString(minutiDurataTicketParam);

		if (!UtilityForm.validateAutomobileInputForm(marcaParam, modelloParam, targaParam, orarioStampaTicketParam,
				minutiDurataTicketParam, parcheggioIdParam) || orarioStampaTicketParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;
		}

		Automobile automobileInsertInstance = new Automobile(marcaParam, modelloParam, targaParam,(Time) orarioStampaTicketParsed,
				minutiDurataTicketParsed,new Parcheggio());

		try {
			MyServiceFactory.getAutomobileServiceInstance().inserisciNuovo(automobileInsertInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListAutomobileServlet?operationResult=SUCCESS");
	}
}