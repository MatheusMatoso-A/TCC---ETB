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
import modelo.entidades.AreaCobertura;
import modelo.entidades.PreCadastro;
import modelo.persistencia.AreaCoberturaDAO;
import modelo.persistencia.PreCadastroDAO;

@WebServlet(name = "GerenciarPreCadastro", urlPatterns = {"/gerenciar_pre_cadastro.do"})
public class GerenciarPreCadastro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GerenciarPreCadastro</title>");
            out.println("</head>");
            out.println("<body>");

            String nome = request.getParameter("nome");
            String cep = request.getParameter("cep");
            String telefone = request.getParameter("telefone");
            String cidade = request.getParameter("cidade");
            String email = request.getParameter("email");

            int planoId = Integer.parseInt(request.getParameter("planoId"));

            //mensagem
            String mensagem;
            int na = 17;

            if (nome == null || nome.equals("")) {
                out.print("O campo Nome deve ser preenchido!");
            } else if (cep == null || cep.equals("")) {
                out.print("O campo CEP deve ser preenchido!");
            } else if (telefone == null || telefone.equals("")) {
                out.print("O campo Telefone deve ser preenchido!");
            } else if (cidade == null || cidade.equals("")) {
                out.print("O campo Cidade deve ser preenchido!");
            } else if (planoId < 1) {
                out.print("Selecione um plano");
            } else {
                try {
                    AreaCoberturaDAO acDAO = new AreaCoberturaDAO();

                    AreaCobertura ac = acDAO.buscarPorCep(cep);

                    PreCadastro pc = new PreCadastro();

                    pc.setNome(nome);
                    pc.setCep(cep);
                    pc.setTelefone(telefone);
                    pc.setCidade(cidade);
                    pc.setEmail(email);
                    if (ac != null) {
                        pc.setAreaCobertura(ac);    // Define a área de cobertura
                    } else {
                        pc.setAreaCobertura(na);   // Define explicitamente como null caso a área de cobertura não exista
                    }

                    // Define a área de cobertura
                    System.out.println("Valores recebidos:");
                    System.out.println("Nome: " + nome);
                    System.out.println("CEP: " + cep);
                    System.out.println("Telefone: " + telefone);
                    System.out.println("Cidade: " + cidade);
                    System.out.println("Email: " + email);
                    System.out.println("AreadeCoberturaID: " + ac);

                    PreCadastroDAO pcDAO = new PreCadastroDAO();

                    pcDAO.salvar(pc);
                    
                    System.out.println(pc.getId());
                    if (pc.getAreaCobertura().getId() == na) {

                        out.print("<script language='javascript'>");
                        out.print("location.href='informativo.jsp';");
                        out.print("</script>");

                    } else {

                        int preCadastroId = pc.getId();
                        mensagem = "Pré-Cadastro realizado com sucesso!";
                        request.getSession().setAttribute("mensagemToast", mensagem);

                        out.print("<script language='javascript'>");
                        out.print("window.location.href = 'cadastro.jsp?planoId=" + planoId + "&preCadastroId=" + preCadastroId + "';");  // Redireciona para listar_usuario.jsp
                        out.print("</script>");
                    }

                } catch (Exception erro) {
                    erro.printStackTrace();
                    out.println("Erro ao salvar o pré-cadastro: " + erro.getMessage());

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
        return "Servelet Gerenciar Pre Cadastro";
    }// </editor-fold>

}
