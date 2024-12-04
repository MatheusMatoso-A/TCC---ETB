package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.PreCadastro;
import modelo.persistencia.PreCadastroDAO;

public class ExcluirPreCadastro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExcluirPreCadastro</title>");
            out.println("</head>");
            out.println("<body>");

            String id = request.getParameter("id");
            String mensagem;
            
            if (id == null || id.equals("")) {
                mensagem = "Uma Pré-Cadastro deve ser selecionado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'listar_possiveis_clientes.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");
            } else {

                try {

                    PreCadastroDAO pcDAO = new PreCadastroDAO();
                    PreCadastro pc = new PreCadastro();
                    
                    pc.setId(Integer.parseInt(id));
                    
                    pcDAO.deletar(pc);
                    

                    mensagem = "Pré-Cadastro excluido com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_possiveis_clientes.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao excluir Pré-Cadastro<strong> </br> "
                            + "Ocorreu um problema ao tentar excluir Pré-Cadastro. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_possiveis_clientes.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }

                out.println("</body>");
                out.println("</html>");
            }
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
