package it.prova.raccoltafilm.web.servlet.parcheggio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestioneparcheggio.model.Parcheggio;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.utility.UtilityForm;

@WebServlet("/ExecuteVisualizzaParcheggioServlet")
public class ExecuteVisualizzaParcheggioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParcheggioParam = request.getParameter("idParcheggio");

		long idParsedParcheggio = UtilityForm.parseIdEntryToLongFromString(idParcheggioParam);

		if (!NumberUtils.isCreatable(idParcheggioParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			Parcheggio parcheggioInstance = MyServiceFactory.getParcheggioServiceInstance()
					.caricaSingoloElementoById(idParsedParcheggio);

			if (parcheggioInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListParcheggioServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("show_parcheggio_attr", parcheggioInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/parcheggio/show.jsp").forward(request, response);
	}

}
