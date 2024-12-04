package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Produtos;
import modelo.entidades.Vendas;
import modelo.persistencia.ProdutosDAO;
import modelo.persistencia.VendasDAO;

public class GerenciarVendas extends HttpServlet {

    private VendasDAO vDAO;
    private String mensagem;

    @Override
    public void init() throws ServletException {

        try {
            vDAO = new VendasDAO();
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
            out.println("<title>Servlet GerenciarVendas</title>");
            out.println("</head>");
            out.println("<body>");

            String action = request.getParameter("action");

            try {

                switch (action) {

                    case "modificar":
                        modificarVendas(request, response);
                        break;
                    case "excluir":
                        excluirVendas(request, response);
                        break;
                    default:
                        response.sendRedirect("litar_vendas.jsp");
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

    private void modificarVendas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            int id = Integer.parseInt(request.getParameter("id"));
            int produto = Integer.parseInt(request.getParameter("produto"));
            boolean foiPago = Boolean.parseBoolean(request.getParameter("foiPago"));
            String dataVencimento = request.getParameter("dataVencimento");

            System.out.println("DADOS:");
            System.out.println("ID: " + id);
            System.out.println("PRODUTO: " + produto);
            System.out.println("DIA DO VENCIMENTO: " + dataVencimento);
            System.out.println("FOI PAGO: " + foiPago);

            if (id <= 0) {
                mensagem = "O ID da venda não foi encontrado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'form_alterar_vendas.jsp?id=" + id + "';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

            } else {

                try {

                    ProdutosDAO pDB = new ProdutosDAO();
                    Produtos p = pDB.buscarPorId(produto);

                    Vendas v = new Vendas();

                    v.setFoiPago(foiPago);
                    v.setDataVencimento(dataVencimento);
                    v.setProdutos(p);

                    vDAO.modificar(v);

                    mensagem = "Venda modificada com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_vendas.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao alterar Venda<strong> </br> "
                            + "Ocorreu um problema ao tentar alterar a Venda. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_vendas.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }
            }

        }

    }

    private void excluirVendas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String id = request.getParameter("id");

            if (id == null || id.equals("")) {
                mensagem = "Uma Venda deve ser selecionado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'listar_vendas.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");
            } else {

                try {

                    Vendas v = new Vendas();
                    v.setId(Integer.parseInt(id));
                    vDAO.deletar(v);

                    mensagem = "Venda excluida com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_vendas.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao excluir Venda<strong> </br> "
                            + "Ocorreu um problema ao tentar excluir a venda. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_vendas.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }

            }
        }
    }
}
