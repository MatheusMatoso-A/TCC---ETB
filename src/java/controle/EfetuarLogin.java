package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.entidades.Usuario;
import modelo.persistencia.UsuarioDAO;

public class EfetuarLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EfetuarLogin</title>");
            out.println("</head>");
            out.println("<body>");

            HttpSession session = request.getSession();

            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String mensagem;
            try {
                UsuarioDAO uDB = new UsuarioDAO();

                Usuario u = uDB.logar(login, senha);

                if (u.getId() > 0 && u.getAtivo() == true) {

                    session.setAttribute("usuario", u);
                    response.sendRedirect("inicio_login.jsp");

                } 

            } catch (Exception e) {
                
                mensagem = "<Strong>ATENÇÃO</Strong></br> "
                        + "Usuário ou senha inválidos!</br> "
                        + "Se o problema persistir, entre em contato com o suporte para verificar o status de sua conta.";
                request.getSession().setAttribute("mensagemAlerta", mensagem);
                response.sendRedirect("login.jsp?warning=true");
                
                out.print(e);
                e.printStackTrace();

            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
