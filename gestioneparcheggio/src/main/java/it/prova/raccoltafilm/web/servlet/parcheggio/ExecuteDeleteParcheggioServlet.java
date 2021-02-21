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

@WebServlet("/ExecuteDeleteParcheggioServlet")
public class ExecuteDeleteParcheggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteParcheggioServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParcheggioParam = request.getParameter("idParcheggio");

		Long idParcheggioParsed = UtilityForm.parseIdEntryToLongFromString(idParcheggioParam);

		try {
			Parcheggio parcheggioDeleteInstance = MyServiceFactory.getParcheggioServiceInstance()
					.caricaSingoloElementoByIdConAutomobili(idParcheggioParsed);

			MyServiceFactory.getParcheggioServiceInstance().rimuovi(parcheggioDeleteInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/parcheggio/delete.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListParcheggioServlet?operationResult=SUCCESS");
	}

}
