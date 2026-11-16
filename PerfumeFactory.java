package factory;
import model.Perfume;
import model.Produto;

public class PerfumeFactory extends ProdutoFactory {

    @Override
    public Produto criarProduto(String nome, String marca, String volume,
                                double preco, int estoque) {

        return new Perfume(nome, marca, volume, preco, estoque);
    }
}
