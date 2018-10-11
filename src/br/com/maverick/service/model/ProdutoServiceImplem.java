package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.model.ProdutoDaoInterface;
import br.com.maverick.model.model.ProdutoModel;

public class ProdutoServiceImplem implements ProdutoServiceInterface {

	@Inject
	private ProdutoDaoInterface produtoDaoInterface;
	
	@Override
	@Transactional
	public ProdutoModel salvarProduto(ProdutoModel produtoModel) {
		produtoDaoInterface.salvarProduto(produtoModel);
		return produtoModel;
	}

	@Override
	@Transactional
	public void alterar(ProdutoModel produtoModel) {
		produtoDaoInterface.alterar(produtoModel);
	}

	@Override
	@Transactional
	public void excluir(ProdutoModel produtoModel) {
		produtoDaoInterface.excluir(produtoModel);
	}

	@Override
	public List<ProdutoModel> getProdutos() {
		return produtoDaoInterface.getProdutos();
	}

}
