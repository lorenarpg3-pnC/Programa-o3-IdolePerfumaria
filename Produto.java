package model;

public abstract class Produto {

    private final String nome;
    private final String marca;
    private final String categoria;
    private final String volume;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String nome, String marca, String categoria, String volume,
                   double preco, int quantidadeEstoque) {

        if (nome == null || nome.isBlank() || marca == null || marca.isBlank()
                || categoria == null || categoria.isBlank() || volume == null || volume.isBlank()) {
            throw new IllegalArgumentException("Dados do produto incompletos.");
        }

        if (!Double.isFinite(preco) || preco < 0 || quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Preço e estoque não podem ser negativos.");
        }

        this.nome = nome.trim();
        this.marca = marca.trim();
        this.categoria = categoria.trim();
        this.volume = volume.trim();
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNome() { return nome; }
    public String getMarca() { return marca; }
    public String getCategoria() { return categoria; }
    public String getVolume() { return volume; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }

    public void setPreco(double preco) {
        if (!Double.isFinite(preco) || preco < 0) {
            throw new IllegalArgumentException("Preço inválido.");
        }
        this.preco = preco;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser positiva.");
        }
        quantidadeEstoque = Math.addExact(quantidadeEstoque, quantidade);
    }

    public void retirarEstoque(int quantidade) {
        if (quantidade <= 0 || quantidade > quantidadeEstoque) {
            throw new IllegalArgumentException("Quantidade inválida ou estoque insuficiente.");
        }
        quantidadeEstoque -= quantidade;
    }
}
