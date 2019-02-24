package br.com.maverick.service.model;

import java.util.List;

import br.com.maverick.model.model.EstoqueModel;

public interface EstoqueServiceInterface {
	
	EstoqueModel salvarEstoque(EstoqueModel estoqueModel);
	
	void alterar(EstoqueModel estoqueModel);
	
	void excluir(EstoqueModel estoqueModel);
	
	List<EstoqueModel> getEstoques();
	
	List<EstoqueModel> getEstoquesPorProduto(Integer codProduto);

}
