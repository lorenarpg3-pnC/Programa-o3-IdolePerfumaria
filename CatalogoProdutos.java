package catalogo;
import model.Produto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CatalogoProdutos {

    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        if (buscarPorNome(produto.getNome()) != null) {
            throw new IllegalArgumentException("Já existe um produto com esse nome.");
        }
        produtos.add(produto);
    }

    public Produto buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return null;
        }
        String busca = nome.trim();
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(busca)) {
                return produto;
            }
        }

        return null;
    }

    public List<Produto> pesquisar(String termo) {

        List<Produto> encontrados = new ArrayList<>();

        String busca = termo == null ? "" : termo.trim().toLowerCase(Locale.ROOT);

        ProdutoIterator iterator = iterator();

        while (iterator.hasNext()) {

            Produto produto = iterator.next();

            if (produto.getNome().toLowerCase(Locale.ROOT).contains(busca)) {
                encontrados.add(produto);
            }
        }

        return encontrados;
    }

    public List<Produto> listarCategoria(String categoria) {
        List<Produto> encontrados = new ArrayList<>();
        if (categoria == null || categoria.isBlank()) {
            return encontrados;
        }

        ProdutoIterator iterator = iterator();

        while (iterator.hasNext()) {
            Produto produto = iterator.next();

            if (produto.getCategoria().equalsIgnoreCase(categoria.trim())) {
                encontrados.add(produto);
            }
        }

        return encontrados;
    }

    public ProdutoIterator iterator() {

        return new ProdutoIterator(Collections.unmodifiableList(produtos));
    }

    public int tamanho() {
        return produtos.size();
    }
}
