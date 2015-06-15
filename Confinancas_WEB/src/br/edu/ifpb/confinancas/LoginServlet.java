package br.edu.ifpb.confinancas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -7911062024192033565L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperando informações do Header enviado pelo HTTP
		System.out.println("Resposta do GET...");
		Map map = getHeadersInfo(request);
		System.out.println(map.toString());
		
		// Recuperando valores enviado pelo método GET
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");		
		
		// Máquina que realizou a requisição
		String ipRequest = request.getRemoteAddr();
		response.setStatus(response.SC_ACCEPTED);
		
		// Gerando página de saída.
		/*PrintWriter pw = response.getWriter();
		pw.print("<html>");
		pw.print("<head></head>");
		pw.print("<body>");
		pw.print("<h1>Bem vindo,</h1>");
		pw.print("<h2>"+ usuario +"</h2>");
		pw.print("</body>");
		pw.print("</html>");
		pw.flush();*/
		
		response.sendRedirect("menu.html");
	}
	
	private Map<String, String> getHeadersInfo(HttpServletRequest request) {
		 
		Map<String, String> map = new HashMap<String, String>();
	 
		Enumeration headerNames = request.getHeaderNames();
		
		while (headerNames.hasMoreElements()) {
			
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			
			map.put(key, value);
		}
	 
		return map;
	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperando valores enviado pelo método GET
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");
		
		// Adicionando valores em sessão.
		HttpSession session = request.getSession();
		session.setAttribute("usuario", usuario);
				
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("segunda.jsp");        
		dispatcher.forward(request, response);
	}
}
