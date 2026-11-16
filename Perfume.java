package model;

public class Perfume extends Produto {

    public Perfume(String nome, String marca, String volume, double preco, int estoque) {

        super(nome, marca, "Perfume", volume, preco, estoque);
    }
}
