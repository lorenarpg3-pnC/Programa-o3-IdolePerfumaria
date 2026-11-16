package demo;
import catalogo.CatalogoProdutos;
import catalogo.ProdutoIterator;
import factory.BodySplashFactory;
import factory.PerfumeFactory;
import factory.ProdutoFactory;
import service.GerenciadorPerfumaria;
import service.PerfumariaFacade;
import model.Produto;

public class DemonstracaoPadroes {

    public static void main(String[] args) {
        demonstrarFactoryMethod();
        demonstrarFacade();
        demonstrarSingleton();
        demonstrarIterator();
    }

    private static void demonstrarFactoryMethod() {
        System.out.println("\n=== FACTORY METHOD ===");

        ProdutoFactory fabricaPerfume = new PerfumeFactory();
        ProdutoFactory fabricaBodySplash = new BodySplashFactory();

        Produto perfume = fabricaPerfume.criarProduto(
                "Heaven V", "WePink", "100 ml", 89.90, 5);
        Produto bodySplash = fabricaBodySplash.criarProduto(
                "Body Splash Paradise", "WePink", "200 ml", 32.90, 8);

        System.out.println("Fábrica de perfume criou: " + perfume.getClass().getSimpleName());
        System.out.println("Fábrica de Body Splash criou: " + bodySplash.getClass().getSimpleName());
    }

    private static void demonstrarFacade() {
        System.out.println("\n=== FACADE ===");

        PerfumariaFacade fachada =
                GerenciadorPerfumaria.getInstancia().getFacade();

        int quantidade = fachada.listarProdutos().size();
        System.out.println("Produtos disponíveis pela fachada: " + quantidade);
    }

    private static void demonstrarSingleton() {
        System.out.println("\n=== SINGLETON ===");

        GerenciadorPerfumaria primeira = GerenciadorPerfumaria.getInstancia();
        GerenciadorPerfumaria segunda = GerenciadorPerfumaria.getInstancia();

        System.out.println("As duas referências apontam para o mesmo objeto? "
                + (primeira == segunda));
    }

    private static void demonstrarIterator() {
        System.out.println("\n=== ITERATOR ===");

        CatalogoProdutos catalogo = new CatalogoProdutos();

        ProdutoFactory fabrica = new PerfumeFactory();

        catalogo.adicionar(fabrica.criarProduto(
                "Exemplo A", "WePink", "100 ml", 80.00, 3));
        catalogo.adicionar(fabrica.criarProduto(
                "Exemplo B", "WePink", "100 ml", 90.00, 2));

        ProdutoIterator iterator = catalogo.iterator();

        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            System.out.println("Iterando produto: " + produto.getNome());
        }
    }
}
