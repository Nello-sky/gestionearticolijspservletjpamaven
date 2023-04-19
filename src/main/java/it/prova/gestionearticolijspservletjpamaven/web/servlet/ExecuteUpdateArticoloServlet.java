package it.prova.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionearticolijspservletjpamaven.model.Articolo;
import it.prova.gestionearticolijspservletjpamaven.service.MyServiceFactory;
import it.prova.gestionearticolijspservletjpamaven.utility.UtilityArticoloForm;

/**
 * Servlet implementation class ExecuteUpdateArticoloServlet
 */
@WebServlet("/ExecuteUpdateArticoloServlet")
public class ExecuteUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// estraggo input
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String dataArrivoStringParam = request.getParameter("dataArrivo");
		Long idArticoloToUpdate = Long.parseLong(request.getParameter("idUpdate"));
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Articolo articoloInstance = UtilityArticoloForm.createArticoloFromAll(idArticoloToUpdate,codiceInputParam,
				descrizioneInputParam, prezzoInputStringParam, dataArrivoStringParam);

		// se la validazione non risulta ok
		try {
		if (!UtilityArticoloForm.validateArticoloBean(articoloInstance)) {
			
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("ArticoloToUpdate", MyServiceFactory.getArticoloServiceInstance().caricaSingoloElemento(idArticoloToUpdate));
			request.getRequestDispatcher("/articolo/edit.jsp").forward(request, response);
			return;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articoloInstance);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/results.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);

	}

}
