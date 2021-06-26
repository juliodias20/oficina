package br.com.maverick.produto.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.produto.model.ProdutoModel;
import br.com.maverick.produto.repository.ProdutoDao;

public class ProdutoService {

	@Inject
	private ProdutoDao produtoDao;
	
	@Transactional
	public ProdutoModel salvarProduto(ProdutoModel produtoModel) {
		produtoDao.salvarProduto(produtoModel);
		return produtoModel;
	}

	@Transactional
	public void alterar(ProdutoModel produtoModel) {
		produtoDao.alterar(produtoModel);
	}

	@Transactional
	public void excluir(ProdutoModel produtoModel) {
		produtoDao.excluir(produtoModel);
	}

	public List<ProdutoModel> getProdutos() {
		return produtoDao.getProdutos();
	}
	
	public List<ProdutoModel> getProdutos(Integer codProduto) {
		return produtoDao.getProdutos(codProduto);
	}

}
