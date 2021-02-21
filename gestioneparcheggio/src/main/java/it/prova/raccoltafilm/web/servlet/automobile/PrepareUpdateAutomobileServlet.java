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

@WebServlet("/PrepareUpdateAutomobileServlet")
public class PrepareUpdateAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAutomobileParam = request.getParameter("idAutomobile");

		Long idParsedAutomobile = UtilityForm.parseIdEntryToLongFromString(idAutomobileParam);
		try {
			request.setAttribute("parcheggi_list_attribute",
					MyServiceFactory.getParcheggioServiceInstance().listAllElements());

			Automobile automobileUpdateInstance = MyServiceFactory.getAutomobileServiceInstance()
					.caricaSingoloElementoByIdEager(idParsedAutomobile);

			request.setAttribute("update_automobile_attr", automobileUpdateInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore nella procedura.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/automobile/update.jsp").forward(request, response);
	}
}
