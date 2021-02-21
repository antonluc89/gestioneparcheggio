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

@WebServlet("/PrepareDeleteAutomobileServlet")
public class PrepareDeleteAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteAutomobileServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAutomobileParam = request.getParameter("idAutomobile");
		
		Long idParsedAutomobile = UtilityForm.parseIdEntryToLongFromString(idAutomobileParam);

		try {
			Automobile automobileDeleteInstance = MyServiceFactory.getAutomobileServiceInstance()
					.caricaSingoloElementoByIdEager(idParsedAutomobile);
			
			request.setAttribute("delete_automobile_attr", automobileDeleteInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/automobile/delete.jsp").forward(request, response);
	}
}
