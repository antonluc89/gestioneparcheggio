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

@WebServlet("/PrepareUpdateParcheggioServlet")
public class PrepareUpdateParcheggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParcheggioParam = request.getParameter("idParcheggio");

		Long idParsedParcheggio = UtilityForm.parseIdEntryToLongFromString(idParcheggioParam);
		try {
			Parcheggio parcheggioUpdateInstance = MyServiceFactory.getParcheggioServiceInstance()
					.caricaSingoloElementoById(idParsedParcheggio);
			
			request.setAttribute("update_parcheggio_attr", parcheggioUpdateInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore nella procedura.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/parcheggio/update.jsp").forward(request, response);
	}
}
