package br.com.maverick.Dao;

import java.util.List;

import br.com.maverick.model.EstoqueModel;

public interface EstoqueDaoInterface {

	EstoqueModel salvarEstoque(EstoqueModel estoqueModel);
	
	void alterar(EstoqueModel estoqueModel);
	
	void alterar(Integer codProduto, Integer qtdEstoque);
	
	void excluir(EstoqueModel estoqueModel);
	
	List<EstoqueModel> getEstoques();
	
	List<EstoqueModel> getEstoquesPorProduto(Integer codProduto);
	
}
