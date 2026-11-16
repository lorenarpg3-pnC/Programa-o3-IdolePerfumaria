package service;
import model.Venda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoVendas {

    private final List<Venda> vendas = new ArrayList<>();

    public void registrar(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> listar() {
        return Collections.unmodifiableList(vendas);
    }
}
