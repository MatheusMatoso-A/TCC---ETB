package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Produtos;
import modelo.persistencia.ProdutosDAO;

@WebServlet(name = "GerenciarProdutos", urlPatterns = {"/gerenciar_produtos.do"})
public class GerenciarProdutos extends HttpServlet {

    private ProdutosDAO pDAO;
    private String mensagem;

    @Override
    public void init() throws ServletException {

        try {
            pDAO = new ProdutosDAO();
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
            out.println("<title>Servlet GerenciarProdutos</title>");
            out.println("</head>");
            out.println("<body>");

            String action = request.getParameter("action");

            try {

                switch (action) {
                    case "inserir":
                        inserirProduto(request, response);
                        break;
                    case "modificar":
                        modificarProduto(request, response);
                        break;
                    case "excluir":
                        excluirProduto(request, response);
                        break;
                    default:
                        response.sendRedirect("prondutos_login.jsp");
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

    private void inserirProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String nome = request.getParameter("nome");
            String velocidade = request.getParameter("velocidade");
            String valorStr = request.getParameter("valor");

            try {

                valorStr = valorStr.replace(',', '.');

                double valor = Double.parseDouble(valorStr);

                Produtos p = new Produtos();
                p.setNome(nome);
                p.setVelocidade(velocidade);
                p.setValor(valor);

                pDAO.salvar(p);

                mensagem = "Produto cadastrado com sucesso!</br> ";
                request.getSession().setAttribute("mensagemToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'listar_produtos.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

            } catch (Exception e) {

                mensagem = "<strong>Erro ao cadastrar produto<strong> </br> "
                        + "Ocorreu um problema ao tentar cadastrar o produto. Por favor, verifique os dados e tente novamente.";
                request.getSession().setAttribute("mensagemDangerToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'produtos_login.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

                out.print(e);
                e.printStackTrace();

            }
        }
    }

    private void modificarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String nome = request.getParameter("nome");
            String velocidade = request.getParameter("velocidade");
            String valorStr = request.getParameter("valor");
            String ativo = request.getParameter("ativo");
            int id = Integer.parseInt(request.getParameter("id"));

            if (id<=0) {
                mensagem = "O ID do produto não foi encontrado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'form_alterar_produto.jsp?id=" + id + "';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

            } else {

                try {

                    valorStr = valorStr.replace(',', '.');
                    double valor = Double.parseDouble(valorStr);

                    Produtos p = new Produtos();

                    p.setNome(nome);
                    p.setVelocidade(velocidade);
                    p.setValor(valor);
                    p.setAtivo(Boolean.parseBoolean(ativo));
                    p.setId(id);
                    
                    pDAO.modificar(p);
                    
                    mensagem = "Produto modificado com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_produtos.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");
                    

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao alterar  produto<strong> </br> "
                            + "Ocorreu um problema ao tentar alterar o produto. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_produtos.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }

            }

        }
    }

    private void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String id = request.getParameter("id");

            if (id == null || id.equals("")) {
                mensagem = "Um Produto deve ser selecionado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'listar_produtos.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");
            } else {

                try {

                    Produtos p = new Produtos();
                    p.setId(Integer.parseInt(id));
                    pDAO.deletar(p);

                    mensagem = "Produto excluido com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_produtos.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao excluir produto<strong> </br> "
                            + "Ocorreu um problema ao tentar excluir o produto. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_produtos.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }
            }

        }
    }

}
