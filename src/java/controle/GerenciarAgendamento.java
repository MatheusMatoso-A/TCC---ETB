package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Agenda;
import modelo.persistencia.AgendaDAO;

public class GerenciarAgendamento extends HttpServlet {

    private AgendaDAO agDAO;
    private String mensagem;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public void init() throws ServletException {

        try {
            agDAO = new AgendaDAO();
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
            out.println("<title>Servlet GerenciarAgendamento</title>");
            out.println("</head>");
            out.println("<body>");

            String action = request.getParameter("action");

            try {

                switch (action) {
                    case "inserir":
                        inserirAgenda(request, response);
                        break;
                    case "modificar":
                        modificarAgenda(request, response);
                        break;
                    case "excluir":
                        excluirAgenda(request, response);
                        break;
                    default:
                        response.sendRedirect("agendamento_login.jsp");
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

    private void inserirAgenda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String dataHoraStr = request.getParameter("dataHora");
            String status = request.getParameter("status");

            try {

                LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

                Agenda ag = new Agenda();

                ag.setDataComparecimento(dataHora);
                ag.setStatus(status);

                System.out.println("Dados Recebidos:");
                System.out.println("Data: " + dataHora);
                System.out.println("status: " + status);

                agDAO.salvar(ag);

                mensagem = "Agenda cadastrada com sucesso!</br> ";
                request.getSession().setAttribute("mensagemToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'listar_agenda.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

            } catch (Exception e) {

                mensagem = "<strong>Erro ao cadastrar agenda<strong> </br> "
                        + "Ocorreu um problema ao tentar cadastrar a agenda. Por favor, verifique os dados e tente novamente.";
                request.getSession().setAttribute("mensagemDangerToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'agendamento_login.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

                out.print(e);
                e.printStackTrace();

            }

        }
    }

    private void modificarAgenda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String dataHoraStr = request.getParameter("dataHora");
            String status = request.getParameter("status");
            int id = Integer.parseInt(request.getParameter("id"));

            if (id <= 0) {
                mensagem = "O ID do agendamento não foi encontrado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'form_alterar_agenda.jsp?id=" + id + "';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");

            } else {

                try {

                    LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

                    Agenda ag = new Agenda();

                    ag.setDataComparecimento(dataHora);
                    ag.setStatus(status);
                    ag.setId(id);

                    agDAO.modificar(ag);

                    mensagem = "Agenda modificada com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_agenda.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao alterar agendaemento<strong> </br> "
                            + "Ocorreu um problema ao tentar alterar o agendamento. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_agenda.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }

            }

        }
    }

    private void excluirAgenda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String id = request.getParameter("id");

            if (id == null || id.equals("")) {
                mensagem = "Um Agendamento deve ser selecionado!";
                request.getSession().setAttribute("mensagemWarningToast", mensagem);
                out.print("<script language='javascript'>");
                out.print("window.location.href = 'listar_agenda.jsp';");  // Redireciona para listar_usuario.jsp
                out.print("</script>");
            } else {

                try {

                    Agenda ag = new Agenda();
                    ag.setId(Integer.parseInt(id));
                    agDAO.deletar(ag);

                    mensagem = "Agendamento excluido com sucesso!</br> ";
                    request.getSession().setAttribute("mensagemToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_agenda.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                } catch (Exception e) {
                    mensagem = "<strong>Erro ao excluir Agendamento<strong> </br> "
                            + "Ocorreu um problema ao tentar excluir o agendamento. Por favor, verifique os dados e tente novamente.";
                    request.getSession().setAttribute("mensagemDangerToast", mensagem);
                    out.print("<script language='javascript'>");
                    out.print("window.location.href = 'listar_agenda.jsp';");  // Redireciona para listar_usuario.jsp
                    out.print("</script>");

                    out.print(e);
                    e.printStackTrace();
                }

            }
        }
    }
}
