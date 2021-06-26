package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.ProdutoModel;

public interface ProdutoDaoInterface {

	ProdutoModel salvarProduto(ProdutoModel produtoModel);
	
	void alterar(ProdutoModel produtoModel);
	
	void alterar(Integer codProduto, Integer qtdEstoque);
	
	void excluir(ProdutoModel produtoModel);
	
	List<ProdutoModel> getProdutos();

	List<ProdutoModel> getProdutos(Integer codProduto);
	
}
