package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.model.EstoqueDaoInterface;
import br.com.maverick.model.model.EstoqueModel;

public class EstoqueServiceImplem implements EstoqueServiceInterface {

	@Inject
	private EstoqueDaoInterface estoqueDaoInterface;
	
	@Override
	@Transactional
	public EstoqueModel salvarEstoque(EstoqueModel estoqueModel) {
		return estoqueDaoInterface.salvarEstoque(estoqueModel);
	}

	@Override
	@Transactional
	public void alterar(EstoqueModel estoqueModel) {
		estoqueDaoInterface.alterar(estoqueModel);
	}
	
	@Override
	@Transactional
	public void alterar(Integer codProduto, Integer qtdEstoque) {
		estoqueDaoInterface.alterar(codProduto, qtdEstoque);
	}

	@Override
	@Transactional
	public void excluir(EstoqueModel estoqueModel) {
		estoqueDaoInterface.excluir(estoqueModel);

	}

	@Override
	public List<EstoqueModel> getEstoques() {
		return estoqueDaoInterface.getEstoques();
	}

	@Override
	public List<EstoqueModel> getEstoquesPorProduto(Integer codProduto) {
		return estoqueDaoInterface.getEstoquesPorProduto(codProduto);
	}

}
