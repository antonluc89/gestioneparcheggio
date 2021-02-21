package it.prova.raccoltafilm.web.servlet.automobile;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneparcheggio.model.Automobile;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.utility.UtilityForm;

@WebServlet("/ExecuteUpdateAutomobileServlet")
public class ExecuteUpdateAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAutomobileParam = request.getParameter("idAutomobile");
		String marcaParam = request.getParameter("marca");
		String modelloParam = request.getParameter("modello");
		String targaParam = request.getParameter("targa");
		String orarioStampaTicketParam = request.getParameter("orarioStampaTicket");
		String minutiDurataTicketParam = request.getParameter("minutiDurataTicket");
		String parcheggioIdParam = request.getParameter("parcheggio.id");

		Long idAutomobileParsed = UtilityForm.parseIdEntryToLongFromString(idAutomobileParam);

		Long idParcheggioParsed = UtilityForm.parseIdEntryToLongFromString(parcheggioIdParam);

		Date orarioStampaTicketParsed = UtilityForm.parseTimeEntryFromString(orarioStampaTicketParam);

		Integer minutiDurataTicketParsed = UtilityForm.parseIntegerFromString(minutiDurataTicketParam);

		if (!UtilityForm.validateAutomobileInputForm(marcaParam, modelloParam, targaParam, orarioStampaTicketParam,
				minutiDurataTicketParam, parcheggioIdParam)) {
			request.setAttribute("errorMessage", "Errori nella validazione");
			request.getRequestDispatcher("/automobile/update.jsp").forward(request, response);
			return;
		}

		Automobile automobileUpdateInstance;
		try {
			automobileUpdateInstance = MyServiceFactory.getAutomobileServiceInstance()
					.caricaSingoloElementoById(idAutomobileParsed);
			automobileUpdateInstance.setMarca(marcaParam);
			automobileUpdateInstance.setModello(modelloParam);
			automobileUpdateInstance.setTarga(targaParam);
			automobileUpdateInstance.setOrarioStampaTicket(orarioStampaTicketParsed);
			automobileUpdateInstance.setMinutiDurataTicket(minutiDurataTicketParsed);
			automobileUpdateInstance.setParcheggio(
					MyServiceFactory.getParcheggioServiceInstance().caricaSingoloElementoById(idParcheggioParsed));

			MyServiceFactory.getAutomobileServiceInstance().aggiorna(automobileUpdateInstance);

			request.setAttribute("automobili_list_attribute",
					MyServiceFactory.getAutomobileServiceInstance().listAllElements());

			request.setAttribute("successMessage", "Operazione di aggiornamento effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/update.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListAutomobileServlet?operationResult=SUCCESS");
	}
}
