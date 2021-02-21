package it.prova.raccoltafilm.web.servlet.automobile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneparcheggio.model.Automobile;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.utility.UtilityForm;

@WebServlet("/ExecuteDeleteAutomobileServlet")
public class ExecuteDeleteAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteAutomobileServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAutomobileParam = request.getParameter("idAutomobile");

		Long idAutomobileParsed = UtilityForm.parseIdEntryToLongFromString(idAutomobileParam);

		try {
			Automobile automobileDeleteInstance = MyServiceFactory.getAutomobileServiceInstance()
					.caricaSingoloElementoById(idAutomobileParsed);

			MyServiceFactory.getAutomobileServiceInstance().rimuovi(automobileDeleteInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/delete.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListAutomobileServlet?operationResult=SUCCESS");
	}
}