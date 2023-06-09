package it.prova.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestionearticolijspservletjpamaven.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareEditArticoloServlet
 */
@WebServlet("/PrepareEditArticoloServlet")
public class PrepareEditArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareEditArticoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idArticoloToUpdate = request.getParameter("idArticolo");

		if (!NumberUtils.isCreatable(idArticoloToUpdate)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {
			request.setAttribute("ArticoloToUpdate", MyServiceFactory.getArticoloServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idArticoloToUpdate)));
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

//		

		request.getRequestDispatcher("/articolo/edit.jsp").forward(request, response);
	}

}
