package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {

    private final String produto;
    private final int quantidade;
    private final double valorTotal;
    private final LocalDateTime data;

    public Venda(String produto, int quantidade, double valorTotal) {
        if (produto == null || produto.isBlank() || quantidade <= 0
                || !Double.isFinite(valorTotal) || valorTotal < 0) {
            throw new IllegalArgumentException("Dados da venda inválidos.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.data = LocalDateTime.now();
    }

    public String getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getValorTotal() { return valorTotal; }

    public String getDataFormatada() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
