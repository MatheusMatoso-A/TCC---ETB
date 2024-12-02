/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mathe
 */
@WebServlet(name = "Sair", urlPatterns = {"/sair.do"})
public class Sair extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Logout</title>");
            out.println("</head>");
            out.println("<body>");

            try {

                // Obtém a sessão atual (se houver)
                HttpSession session = request.getSession(false);

                // Verifica se a sessão existe antes de tentar remover o atributo
                if (session != null) {

                    // Remove o atributo específico "usuario" da sessão
                    session.removeAttribute("usuario");

                    // Opcional: pode redirecionar para a página de login ou outra página
                    response.sendRedirect("login.jsp");

                } else {

                    // Caso não exista uma sessão, redireciona para a página de login diretamente
                    response.sendRedirect("login.jsp");
                }

            } catch (IOException e) {

                out.print("<script type='text/javascript'>");
                out.print("alert('Erro ao tentar fazer logout: " + e.getMessage() + "');");
                out.print("window.location.href='login.jsp';"); // Redireciona após o alerta
                out.print("</script>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

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
        return "Servelet para Sair";
    }// </editor-fold>

}
