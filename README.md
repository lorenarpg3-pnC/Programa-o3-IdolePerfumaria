# IDole Perfumaria

Sistema desktop para cadastro e pesquisa de perfumes e body splashes, consulta de estoque, registro de vendas e consulta do histórico.

## Requisitos

- Java 15 ou superior

## Como executar

Compile os arquivos Java da pasta `src` e execute a classe `main.Main`.

A classe `demo.DemonstracaoPadroes` executa uma demonstração no console dos padrões usados no projeto.

## Padrões de projeto

- Factory Method: `ProdutoFactory`, `PerfumeFactory` e `BodySplashFactory`
- Facade: `PerfumariaFacade`
- Singleton: `GerenciadorPerfumaria`
- Iterator: `ProdutoIterator` e `CatalogoProdutos`
