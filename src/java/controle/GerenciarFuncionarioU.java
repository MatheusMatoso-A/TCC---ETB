package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Funcionarios;
import modelo.persistencia.FuncionariosDAO;
import modelo.persistencia.UsuarioDAO;

public class GerenciarFuncionarioU extends HttpServlet {

    private FuncionariosDAO fDAO;
    private UsuarioDAO uDAO;
    private String mensagem;

    @Override
    public void init() throws ServletException {

        try {
            fDAO = new FuncionariosDAO();
            uDAO = new UsuarioDAO();
        } catch (Exception ex) {
            Logger.getLogger(GerenciarProdutos.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GerenciarFuncionarioU</title>");
            out.println("</head>");
            out.println("<body>");
            String action = request.getParameter("action");

            try {

                switch (action) {
                    case "inserir":
                        inserirFuncionario(request, response);
                        break;
                    case "modificar":
                        modificarFuncionario(request, response);
                        break;
                    case "excluir":
                        excluirFuncionario(request, response);
                        break;
                    default:
                        response.sendRedirect("funcionarios_login.jsp");
                        break;

                }
            } catch (IOException e) {

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

    private void inserirFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    private void modificarFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    private void excluirFuncionario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");

            if (id == null || id.equals("")) {
                mensagem = "Um Funcionario deve ser selecionado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'listar_funcionarios.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");
            } else {

                try {

                    Funcionarios f = new Funcionarios();
                    f.setId(Integer.parseInt(id));
                    fDAO.deletar(f);

                    mensagem = "Funcionário excluido com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_funcionarios.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao excluir Funcionário<strong> </br> "
                            + "Ocorreu um problema ao tentar excluir o funcionário. Por favor, tente novamente mais tarde.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_funcionarios.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }
            }

        }
    }

}
