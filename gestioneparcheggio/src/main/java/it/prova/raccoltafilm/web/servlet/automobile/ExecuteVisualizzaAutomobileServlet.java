package it.prova.raccoltafilm.web.servlet.automobile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestioneparcheggio.model.Automobile;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.utility.UtilityForm;

@WebServlet("/ExecuteVisualizzaAutomobileServlet")
public class ExecuteVisualizzaAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAutomobileParam = request.getParameter("idAutomobile");

		Long idParsedAutomobile = UtilityForm.parseIdEntryToLongFromString(idAutomobileParam);

		if (!NumberUtils.isCreatable(idAutomobileParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			Automobile automobileViewInstance = MyServiceFactory.getAutomobileServiceInstance()
					.caricaSingoloElementoByIdEager(idParsedAutomobile);

			if (automobileViewInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListAutomobileServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("show_automobile_attr", automobileViewInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/automobile/show.jsp").forward(request, response);
	}
}
