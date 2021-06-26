package br.com.maverick.estoque.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.estoque.model.EstoqueModel;
import br.com.maverick.estoque.repository.EstoqueDao;

public class EstoqueService {

	@Inject
	private EstoqueDao estoqueDao;
	
	@Transactional
	public EstoqueModel salvarEstoque(EstoqueModel estoqueModel) {
		return estoqueDao.salvarEstoque(estoqueModel);
	}

	@Transactional
	public void alterar(EstoqueModel estoqueModel) {
		estoqueDao.alterar(estoqueModel);
	}
	
	@Transactional
	public void alterar(Integer codProduto, Integer qtdEstoque) {
		estoqueDao.alterar(codProduto, qtdEstoque);
	}

	@Transactional
	public void excluir(EstoqueModel estoqueModel) {
		estoqueDao.excluir(estoqueModel);

	}

	public List<EstoqueModel> getEstoques() {
		return estoqueDao.getEstoques();
	}

	public List<EstoqueModel> getEstoquesPorProduto(Integer codProduto) {
		return estoqueDao.getEstoquesPorProduto(codProduto);
	}

}
