package it.prova.raccoltafilm.web.servlet.parcheggio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestioneparcheggio.model.Parcheggio;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.utility.UtilityForm;

@WebServlet("/PrepareDeleteParcheggioServlet")
public class PrepareDeleteParcheggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteParcheggioServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParcheggioParam = request.getParameter("idParcheggio");
		
		Long idParcheggioParsed = UtilityForm.parseIdEntryToLongFromString(idParcheggioParam);

		try {
			Parcheggio parcheggioDeleteInstance = MyServiceFactory.getParcheggioServiceInstance()
					.caricaSingoloElementoByIdConAutomobili(idParcheggioParsed);
			
			request.setAttribute("delete_parcheggio_attr", parcheggioDeleteInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/parcheggio/delete.jsp").forward(request, response);
	}

}
