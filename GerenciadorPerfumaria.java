package service;
import catalogo.CatalogoProdutos;
import factory.BodySplashFactory;
import factory.PerfumeFactory;
import factory.ProdutoFactory;

public class GerenciadorPerfumaria {

    private static final GerenciadorPerfumaria INSTANCIA =
            new GerenciadorPerfumaria();

    private final CatalogoProdutos catalogo;
    private final PerfumariaFacade facade;

    private GerenciadorPerfumaria() {

        catalogo = new CatalogoProdutos();

        Estoque estoque = new Estoque();

        HistoricoVendas historico = new HistoricoVendas();

        facade = new PerfumariaFacade(catalogo, estoque, historico);

        carregarCatalogoInicial();
    }

    public static GerenciadorPerfumaria getInstancia() {
        return INSTANCIA;
    }

    public PerfumariaFacade getFacade() {
        return facade;
    }

    private void carregarCatalogoInicial() {

        ProdutoFactory perfumes = new PerfumeFactory();
        ProdutoFactory bodySplashes = new BodySplashFactory();

        facade.cadastrarProduto(perfumes.criarProduto("Heaven V", "WePink", "100 ml", 89.90, 12));
        facade.cadastrarProduto(perfumes.criarProduto("Infinity Purple", "WePink", "75 ml", 89.90, 10));
        facade.cadastrarProduto(perfumes.criarProduto("Fatal Pink", "WePink", "100 ml", 89.90, 8));
        facade.cadastrarProduto(perfumes.criarProduto("Paradise", "WePink", "100 ml", 89.90, 9));
        facade.cadastrarProduto(perfumes.criarProduto("VF Golden", "WePink", "75 ml", 84.90, 7));
        facade.cadastrarProduto(perfumes.criarProduto("Heaven Pink", "WePink", "100 ml", 84.90, 11));
        facade.cadastrarProduto(perfumes.criarProduto("Heaven Blue", "WePink", "100 ml", 84.90, 6));
        facade.cadastrarProduto(perfumes.criarProduto("VF 5th Anniversary Edition", "WePink", "75 ml", 84.90, 5));
        facade.cadastrarProduto(perfumes.criarProduto("Eternal Cherish", "WePink", "100 ml", 84.90, 4));

        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash Paradise", "WePink", "200 ml", 32.90, 20));
        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash Heaven Pink", "WePink", "200 ml", 32.90, 18));
        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash VF Golden", "WePink", "200 ml", 32.90, 16));
        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash Obsessed", "WePink", "200 ml", 32.90, 15));
        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash One Touch Warm", "WePink", "200 ml", 32.90, 14));
        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash Heaven", "WePink", "200 ml", 32.90, 13));
        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash Cèdre Raffi", "WePink", "200 ml", 32.90, 12));
        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash Wonbloom Fever", "WePink", "200 ml", 32.90, 10));
        facade.cadastrarProduto(bodySplashes.criarProduto("Body Splash Martin Magnetic", "WePink", "200 ml", 32.90, 9));
    }
}
