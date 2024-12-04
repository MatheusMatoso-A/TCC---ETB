package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.AreaCobertura;
import modelo.persistencia.AreaCoberturaDAO;

public class GerenciarArea extends HttpServlet {

    private AreaCoberturaDAO acDAO;
    private String mensagem;

    @Override
    public void init() throws ServletException {

        try {
            acDAO = new AreaCoberturaDAO();
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
            out.println("<title>Servlet GerenciarArea</title>");
            out.println("</head>");
            out.println("<body>");

            String action = request.getParameter("action");

            try {

                switch (action) {
                    case "inserir":
                        inserirArea(request, response);
                        break;
                    case "modificar":
                        modificarArea(request, response);
                        break;
                    case "excluir":
                        excluirArea(request, response);
                        break;
                    default:
                        response.sendRedirect("areaCobertura_login.jsp");
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

    private void inserirArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");

            try {

                boolean cepU = acDAO.cepUnico(cep);

                if (cepU == true) {

                    mensagem = "O CEP informado já está cadastrado!";
                    request.getSession().setAttribute("mensagemWarningToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_area.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } else {

                    AreaCobertura ac = new AreaCobertura();

                    ac.setCep(cep);
                    ac.setCidade(cidade);
                    ac.setEstado(estado);

                    acDAO.salvar(ac);

                    mensagem = "Área de Cobertura cadastrada com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_area.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");
                }
            } catch (Exception e) {

                mensagem = "<strong>Erro ao cadastrar Área de Cobertura<strong> </br> "
                        + "Ocorreu um problema ao tentar cadastrar a área de cobertura. Por favor, verifique os dados e tente novamente.";
                request.getSession().setAttribute("mensagemDangerToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'areaCobertura_login.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

                out.print(e);
                e.printStackTrace();

            }

        }
    }

    private void modificarArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            int id = Integer.parseInt(request.getParameter("id"));

            if (id <= 0) {
                mensagem = "O ID da área de cobertura não foi encontrado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'form_alterar_area.jsp?id=" + id + "';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

            } else {

                try {
                        AreaCobertura ac = new AreaCobertura();

                        ac.setCep(cep);
                        ac.setCidade(cidade);
                        ac.setEstado(estado);
                        ac.setId(id);

                        acDAO.modificar(ac);

                        mensagem = "Área de Cobertura alterada com sucesso!</br> ";
                        request.getSession().setAttribute("mensagemToast", mensagem);
                        out.print("<script language='javascript'>");
                        out.print("window.location.href = 'listar_area.jsp';");  // Redireciona para listar_usuario.jsp
                        out.print("</script>");
                    

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao alterar  Área de cobertura<strong> </br> "
                            + "Ocorreu um problema ao tentar alterar a área de cobertura. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_area.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }
            }

        }
    }

    private void excluirArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String id = request.getParameter("id");

            if (id == null || id.equals("")) {
                mensagem = "Uma Área de cobertura deve ser selecionado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'listar_area.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");
            } else {

                try {

                    AreaCobertura ac = new AreaCobertura();
                    ac.setId(Integer.parseInt(id));
                    acDAO.deletar(ac);

                    mensagem = "Área de cobertura excluida com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_area.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao excluir Área de cobertura<strong> </br> "
                            + "Ocorreu um problema ao tentar excluir a área de cobertura. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_area.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }

            }
        }
    }
}
