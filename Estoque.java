package service;
import model.Produto;

public class Estoque {

    public void baixar(Produto produto, int quantidade) {
        produto.retirarEstoque(quantidade);
    }

    public int consultar(Produto produto) {
        return produto.getQuantidadeEstoque();
    }
}
