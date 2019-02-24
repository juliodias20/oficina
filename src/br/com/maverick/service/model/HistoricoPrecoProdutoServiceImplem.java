package br.com.maverick.service.model;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import br.com.maverick.Dao.model.HistoricoPrecoProdutoDaoInterface;
import br.com.maverick.model.model.HistoricoPrecoProduto;

public class HistoricoPrecoProdutoServiceImplem implements HistoricoPrecoProdutoServiceInterface {

	@Inject
	private HistoricoPrecoProdutoDaoInterface historicoPrecoProdutoDaoInterface;
	
	@Override
	public List<HistoricoPrecoProduto> getHistoricoDhref(Integer codProduto, Timestamp dhref) {
		return historicoPrecoProdutoDaoInterface.getHistoricoDhref(codProduto, dhref);
	}
	
	@Override
	public List<HistoricoPrecoProduto> getTodosHistoricoProduto(Integer codProduto){
		return historicoPrecoProdutoDaoInterface.getTodosHistoricoProduto(codProduto);
	}
}
