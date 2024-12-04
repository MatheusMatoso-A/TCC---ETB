package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Perfil;
import modelo.entidades.Usuario;
import modelo.persistencia.PerfilDAO;
import modelo.persistencia.UsuarioDAO;

public class InserirUsuarioC extends HttpServlet {

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
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf");
            String dataNascimentoStr = request.getParameter("dataNascimento");
            String dataVencimento = request.getParameter("dataVencimento");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            int preCadastroId = Integer.parseInt(request.getParameter("preCadastroId"));
            int perfil = Integer.parseInt(request.getParameter("perfil"));
            int planoId = Integer.parseInt(request.getParameter("planoId"));
            String mensagem;

            try {

                UsuarioDAO uDAO = new UsuarioDAO();

                boolean unico = uDAO.usuarioUnico(cpf, email, login);

                if (unico == true) {

                    out.print("<script language='javascript'>");
                    out.print("alert('O login, e-mail ou CPF informado já está em uso. Por favor, escolha outra opção.');");
                    out.print("location.href='index.jsp';");
                    out.print("</script>");

                } else {

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date dataNascimento = formatter.parse(dataNascimentoStr);

                    Boolean ativo = false;

                    PerfilDAO pDAO = new PerfilDAO();
                    Perfil p = pDAO.buscarPorId(perfil);

                    Usuario u = new Usuario();

                    u.setNome(nome);
                    u.setEmail(email);
                    u.setTelefone(telefone);
                    u.setCpf(cpf);
                    u.setDataNascimento(dataNascimento);
                    u.setLogin(login);
                    u.setSenha(senha);
                    u.setPerfil(p);
                    u.setAtivo(ativo);

                    System.out.println("Valores recebidos:");
                    System.out.println("Nome: " + nome);
                    System.out.println("CPF: " + cpf);
                    System.out.println("Telefone: " + telefone);
                    System.out.println("Data Nascimento: " + dataNascimento);
                    System.out.println("Email: " + email);
                    System.out.println("Login: " + login);
                    System.out.println("Senha: " + senha);
                    System.out.println("Perfil: " + p);
                    System.out.println("Ativos: " + ativo);

                    uDAO.salvar(u);

                    int usuarioId = u.getId();
                    mensagem = "Cadastro realizado com sucesso!</br> "
                            + "Lembre-se: o acesso à sua conta será liberado após o pagamento da fatura.";
                    request.getSession().setAttribute("mensagemToast", mensagem);

                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'agendamento.jsp?usuarioId=" + usuarioId + "&preCadastroId=" + preCadastroId
                            + "&dataVencimento=" + dataVencimento + "&planoId=" + planoId + "';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                }

            } catch (Exception e) {
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
        return "Servlet Inserir Usuario C";
    }// </editor-fold>

}
