package factory;
import model.BodySplash;
import model.Produto;

public class BodySplashFactory extends ProdutoFactory {

    @Override
    public Produto criarProduto(String nome, String marca, String volume,
                                double preco, int estoque) {

        return new BodySplash(nome, marca, volume, preco, estoque);
    }
}
