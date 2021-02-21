package it.prova.raccoltafilm.web.servlet.automobile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneparcheggio.model.Automobile;
import it.prova.gestioneparcheggio.service.MyServiceFactory;

@WebServlet("/ExecuteSearchAutomobileServlet")
public class ExecuteSearchAutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Automobile automobileExample = new Automobile();
		
		try {
			request.setAttribute("automobili_list_attribute",
					MyServiceFactory.getAutomobileServiceInstance().findByExample(automobileExample));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/automobile/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/automobile/list.jsp").forward(request, response);
	}
}
