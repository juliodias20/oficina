package br.com.maverick.Dao.model;

import java.sql.Timestamp;
import java.util.List;

import br.com.maverick.model.model.HistoricoPrecoProduto;

public interface HistoricoPrecoProdutoDaoInterface {

	List<HistoricoPrecoProduto> getHistoricoDhref(Integer codProduto, Timestamp dhref);
	
	List<HistoricoPrecoProduto> getTodosHistoricoProduto(Integer codProduto);
	

}
