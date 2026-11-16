package catalogo;
import model.Produto;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ProdutoIterator implements Iterator<Produto> {

    private final List<Produto> produtos;
    private int posicao = 0;

    public ProdutoIterator(List<Produto> produtos) {
        if (produtos == null) {
            throw new IllegalArgumentException("Product list cannot be null.");
        }
        this.produtos = produtos;
    }

    @Override
    public boolean hasNext() {
        return posicao < produtos.size();
    }

    @Override
    public Produto next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Não há mais produtos.");
        }
        return produtos.get(posicao++);
    }
}
