package UI;
import factory.BodySplashFactory;
import factory.PerfumeFactory;
import factory.ProdutoFactory;
import service.GerenciadorPerfumaria;
import service.PerfumariaFacade;
import model.Produto;
import model.Venda;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TelaPrincipal extends JFrame {

    private final PerfumariaFacade facade =
            GerenciadorPerfumaria.getInstancia().getFacade();

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Nome", "Marca", "Categoria", "Volume", "Preço", "Estoque"}, 0) {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabela = new JTable(modelo);

    private final JTextField campoBusca = new JTextField(18);

    private final JComboBox<String> filtroCategoria =
            new JComboBox<>(new String[]{"Todos", "Perfume", "Body Splash"});

    private final NumberFormat moeda =
            NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public TelaPrincipal() {
        super("IDÔLE PERFUMARIA");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(940, 590);

        setLocationRelativeTo(null);

        setMinimumSize(new Dimension(800, 500));

        construirInterface();

        atualizarTabela(facade.listarProdutos());
    }

    private void construirInterface() {

        JPanel principal = new JPanel(new BorderLayout(12, 12));

        principal.setBackground(new Color(255, 244, 248));

        principal.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));

        setContentPane(principal);

        JLabel titulo = new JLabel("IDÔLE PERFUMARIA");
        titulo.setFont(new Font("Serif", Font.BOLD, 29));
        titulo.setForeground(new Color(155, 54, 94));

        JLabel subtitulo = new JLabel("Gerenciamento de produtos, estoque e vendas");
        subtitulo.setForeground(new Color(120, 90, 103));

        JPanel cabecalho = new JPanel(new GridLayout(2, 1, 2, 2));
        cabecalho.setOpaque(false);
        cabecalho.add(titulo);
        cabecalho.add(subtitulo);

        principal.add(cabecalho, BorderLayout.NORTH);

        tabela.setRowHeight(27);
        tabela.getTableHeader().setBackground(new Color(246, 207, 222));
        tabela.getTableHeader().setForeground(new Color(100, 40, 66));
        tabela.setSelectionBackground(new Color(255, 220, 234));
        tabela.setGridColor(new Color(245, 224, 233));

        principal.add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel rodape = new JPanel(new BorderLayout(8, 8));
        rodape.setOpaque(false);

        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 4));
        filtros.setOpaque(false);

        filtros.add(new JLabel("Pesquisar:"));
        filtros.add(campoBusca);
        filtros.add(new JLabel("Categoria:"));
        filtros.add(filtroCategoria);

        JButton pesquisar = botao("Buscar");
        JButton limpar = botao("Limpar");
        filtros.add(pesquisar);
        filtros.add(limpar);

        pesquisar.addActionListener(e -> aplicarFiltros());

        limpar.addActionListener(e -> {
            campoBusca.setText("");
            filtroCategoria.setSelectedIndex(0);
            atualizarTabela(facade.listarProdutos());
        });

        rodape.add(filtros, BorderLayout.NORTH);

        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 4));
        acoes.setOpaque(false);

        JButton cadastrar = botao("Cadastrar produto");
        JButton vender = botao("Registrar venda");
        JButton historico = botao("Histórico de vendas");
        JButton estoque = botao("Consultar estoque");
        JButton atualizar = botao("Atualizar catálogo");

        cadastrar.addActionListener(e -> cadastrarProduto());
        vender.addActionListener(e -> registrarVenda());
        historico.addActionListener(e -> mostrarHistorico());
        estoque.addActionListener(e -> consultarEstoque());
        atualizar.addActionListener(e -> aplicarFiltros());

        acoes.add(cadastrar);
        acoes.add(vender);
        acoes.add(historico);
        acoes.add(estoque);
        acoes.add(atualizar);

        rodape.add(acoes, BorderLayout.SOUTH);

        principal.add(rodape, BorderLayout.SOUTH);
    }

    private JButton botao(String texto) {
        JButton b = new JButton(texto);
        b.setBackground(new Color(238, 177, 201));
        b.setForeground(new Color(92, 35, 61));
        b.setFocusPainted(false);
        return b;
    }

    private void aplicarFiltros() {

        String termo = campoBusca.getText().trim();

        String categoria = (String) filtroCategoria.getSelectedItem();

        List<Produto> produtos = termo.isEmpty()
                ? facade.listarProdutos()
                : facade.pesquisarProdutos(termo);

        if (!"Todos".equals(categoria)) {
            produtos.removeIf(p -> !p.getCategoria().equalsIgnoreCase(categoria));
        }

        atualizarTabela(produtos);
    }

    private void atualizarTabela(List<Produto> produtos) {

        modelo.setRowCount(0);

        for (Produto p : produtos) {
            modelo.addRow(new Object[]{
                    p.getNome(),
                    p.getMarca(),
                    p.getCategoria(),
                    p.getVolume(),
                    moeda.format(p.getPreco()),
                    p.getQuantidadeEstoque()
            });
        }
    }

    private void cadastrarProduto() {

        JTextField nome = new JTextField();
        JComboBox<String> categoria =
                new JComboBox<>(new String[]{"Perfume", "Body Splash"});
        JTextField volume = new JTextField("100 ml");
        JTextField preco = new JTextField();
        JTextField quantidade = new JTextField();

        Object[] campos = {
                "Nome:", nome,
                "Categoria:", categoria,
                "Volume:", volume,
                "Preço (ex.: 79,90):", preco,
                "Quantidade inicial:", quantidade
        };

        if (JOptionPane.showConfirmDialog(this, campos, "Cadastrar produto",
                JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            double valor = Double.parseDouble(
                    preco.getText().trim().replace(",", "."));

            int estoqueInicial = Integer.parseInt(quantidade.getText().trim());

            ProdutoFactory factory = categoria.getSelectedIndex() == 0
                    ? new PerfumeFactory()
                    : new BodySplashFactory();

            Produto novo = factory.criarProduto(
                    nome.getText().trim(),
                    "WePink",
                    volume.getText().trim(),
                    valor,
                    estoqueInicial
            );

            facade.cadastrarProduto(novo);

            aplicarFiltros();

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Confira os dados informados: " + ex.getMessage(),
                    "Erro no cadastro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void registrarVenda() {

        List<Produto> produtos = facade.listarProdutos();
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cadastre um produto antes de registrar uma venda.");
            return;
        }

        String[] nomes = produtos.stream()
                .map(Produto::getNome)
                .toArray(String[]::new);

        JComboBox<String> escolha = new JComboBox<>(nomes);
        JTextField quantidade = new JTextField("1");

        Object[] campos = {
                "Produto:", escolha,
                "Quantidade:", quantidade
        };

        if (JOptionPane.showConfirmDialog(this, campos, "Registrar venda",
                JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            int qtd = Integer.parseInt(quantidade.getText().trim());

            Venda venda = facade.registrarVenda(
                    (String) escolha.getSelectedItem(), qtd);

            aplicarFiltros();

            JOptionPane.showMessageDialog(
                    this,
                    "Venda registrada!\nTotal: " + moeda.format(venda.getValorTotal())
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Não foi possível registrar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarHistorico() {

        StringBuilder texto = new StringBuilder();

        for (Venda v : facade.consultarHistorico()) {
            texto.append(v.getDataFormatada())
                    .append(" | ")
                    .append(v.getProduto())
                    .append(" | Qtd.: ")
                    .append(v.getQuantidade())
                    .append(" | Total: ")
                    .append(moeda.format(v.getValorTotal()))
                    .append("\n");
        }

        if (texto.length() == 0) {
            texto.append("Nenhuma venda registrada até o momento.");
        }

        JTextArea area = new JTextArea(texto.toString(), 14, 65);

        area.setEditable(false);

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                "Histórico de vendas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void consultarEstoque() {

        int linha = tabela.getSelectedRow();

        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela.");
            return;
        }

        String nome = (String) modelo.getValueAt(linha, 0);

        Produto produto = facade.listarProdutos().stream()
                .filter(p -> p.getNome().equals(nome))
                .findFirst()
                .orElse(null);

        if (produto != null) {
            JOptionPane.showMessageDialog(
                    this,
                    produto.getNome() + "\nEstoque disponível: "
                            + facade.consultarEstoque(produto) + " unidade(s).",
                    "Consulta de estoque",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
