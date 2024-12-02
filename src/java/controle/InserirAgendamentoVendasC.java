package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Agenda;
import modelo.entidades.Clientes;
import modelo.entidades.Funcionarios;
import modelo.entidades.PreCadastro;
import modelo.entidades.Produtos;
import modelo.entidades.Usuario;
import modelo.entidades.Vendas;
import modelo.persistencia.AgendaDAO;
import modelo.persistencia.ClientesDAO;
import modelo.persistencia.FuncionariosDAO;
import modelo.persistencia.PreCadastroDAO;
import modelo.persistencia.ProdutosDAO;
import modelo.persistencia.UsuarioDAO;
import modelo.persistencia.VendasDAO;

public class InserirAgendamentoVendasC extends HttpServlet {

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

            String tipoResidencia = request.getParameter("tipoResidencia");
            String cep = request.getParameter("cep");
            String endereco = request.getParameter("rua");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String complemento = request.getParameter("complemento");
            String pontoReferencia = request.getParameter("pontoReferencia");
            String horario = request.getParameter("horario");
            String dataVencimento = request.getParameter("dataVencimento");
            int funcionario = Integer.parseInt(request.getParameter("funcionario"));
            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            int preCadastroId = Integer.parseInt(request.getParameter("preCadastroId"));
            int planoId = Integer.parseInt(request.getParameter("planoId"));

            try {

                /**
                 * ******************************** Salvando Clientes
                 * ********************************
                 */
                UsuarioDAO uDAO = new UsuarioDAO();
                Usuario u = uDAO.buscarPorId(usuarioId);

                PreCadastroDAO pcDAO = new PreCadastroDAO();
                PreCadastro pc = pcDAO.buscarPorId(preCadastroId);

                Clientes c = new Clientes();

                c.setTipoEndereco(tipoResidencia);
                c.setCep(cep);
                c.setEndereco(endereco);
                c.setNumero(numero);
                c.setComplemento(complemento);
                c.setPontoReferencia(pontoReferencia);
                c.setPreCadastro(pc);
                c.setUsuario(u);

                System.out.println("Valores recebidos:");
                System.out.println("Tipo Endereco: " + tipoResidencia);
                System.out.println("CEP: " + cep);
                System.out.println("Endereco: " + endereco);
                System.out.println("Número: " + numero);
                System.out.println("Complemento: " + complemento);
                System.out.println("Ponto de Referência: " + pontoReferencia);
                System.out.println("Pré-Cadastro: " + pc);
                System.out.println("Usuário: " + u);

                ClientesDAO cDAO = new ClientesDAO();

                cDAO.salvar(c);
                /**
                 * ******************************** Salvando Clientes
                 * ********************************
                 */

                /**
                 * ******************************** Salvando Vendas
                 * ********************************
                 */
                int clienteId = c.getId();
                Boolean pago = false;
                Date dataVenda = new Date();

                ProdutosDAO pDAO = new ProdutosDAO();
                Produtos p = pDAO.buscarPorId(planoId);

                Clientes clienteVendas = cDAO.buscarPorId(clienteId);

                FuncionariosDAO fDAO = new FuncionariosDAO();
                Funcionarios f = fDAO.buscarPorUsuarioId(funcionario);

                Vendas v = new Vendas();

                v.setFoiPago(pago);
                v.setDataVenda(dataVenda);
                v.setDataVencimento(dataVencimento);
                v.setProdutos(p);
                v.setFuncionario(f);
                v.setCliente(clienteVendas);

                System.out.println("Valores recebidos:");
                System.out.println("Foi Pago: " + pago);
                System.out.println("Data da Venda: " + dataVenda);
                System.out.println("Dia do Venciemento: " + dataVencimento);
                System.out.println("Produtos: " + p);
                System.out.println("Funcionários: " + f);
                System.out.println("Cliente: " + clienteVendas);

                VendasDAO vDAO = new VendasDAO();
                vDAO.salvar(v);

                /**
                 * ******************************** Salvando Vendas
                 * *********************************
                 */
                /**
                 * ******************************** Salvando agenda
                 * *********************************
                 */
                LocalDateTime dataHora = LocalDateTime.parse(horario, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                String status = "Reservado";
                int vendaId = v.getId();

                Vendas vendasAgenda = vDAO.buscarPorId(vendaId);

                Agenda ag = new Agenda();

                ag.setStatus(status);
                ag.setVendas(vendasAgenda);
                ag.setDataComparecimento(dataHora);

                System.out.println("Valores recebidos:");
                System.out.println("Status: " + status);
                System.out.println("Vendas ID: " + vendasAgenda);
                System.out.println("Data de Comparecimento: " + dataHora);

                AgendaDAO agDAO = new AgendaDAO();
                agDAO.modificarStatus(ag);
                out.print("<script language='javascript'>");
                out.print("alert('Agendamento realizado com sucesso!!\nEm até 72h a Fatura estará em seu e-mail.');");
                out.print("</script>");
                response.sendRedirect("index.jsp");
                /**
                 * ******************************** Salvando agenda
                 * *********************************
                 */

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
        return "Short description";
    }// </editor-fold>

}
