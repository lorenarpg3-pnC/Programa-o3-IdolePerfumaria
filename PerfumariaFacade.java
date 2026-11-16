package service;
import catalogo.CatalogoProdutos;
import catalogo.ProdutoIterator;
import model.Produto;
import model.Venda;
import java.util.ArrayList;
import java.util.List;

public class PerfumariaFacade {

    private final CatalogoProdutos catalogo;
    private final Estoque estoque;
    private final HistoricoVendas historico;

    public PerfumariaFacade(CatalogoProdutos catalogo, Estoque estoque,
                            HistoricoVendas historico) {
        this.catalogo = catalogo;
        this.estoque = estoque;
        this.historico = historico;
    }

    public void cadastrarProduto(Produto produto) {
        catalogo.adicionar(produto);
    }

    public List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();

        ProdutoIterator iterator = catalogo.iterator();

        while (iterator.hasNext()) {
            lista.add(iterator.next());
        }

        return lista;
    }

    public List<Produto> pesquisarProdutos(String termo) {
        return catalogo.pesquisar(termo);
    }

    public List<Produto> listarCategoria(String categoria) {
        return catalogo.listarCategoria(categoria);
    }

    public int consultarEstoque(Produto produto) {
        return estoque.consultar(produto);
    }

    public List<Venda> consultarHistorico() {
        return historico.listar();
    }

    public Venda registrarVenda(String nomeProduto, int quantidade) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser positiva.");
        }

        Produto produto = catalogo.buscarPorNome(nomeProduto);

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        double total = produto.getPreco() * quantidade;
        if (!Double.isFinite(total)) {
            throw new IllegalArgumentException("O valor total da venda é inválido.");
        }

        estoque.baixar(produto, quantidade);

        Venda venda = new Venda(produto.getNome(), quantidade, total);

        historico.registrar(venda);

        return venda;
    }
}
