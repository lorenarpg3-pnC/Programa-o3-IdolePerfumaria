package factory;
import model.Produto;

public abstract class ProdutoFactory {

    public abstract Produto criarProduto(
            String nome,
            String marca,
            String volume,
            double preco,
            int estoque
    );
}
