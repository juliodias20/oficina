package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.CidadeModel;

public interface CidadeDaoInterface {

	CidadeModel salvarCidade(CidadeModel cidadeModel);
	
	void alterar (CidadeModel cidadeModel);
	
	void excluir (CidadeModel cidadeModel);
	
	List<CidadeModel> getCidades();
	
}
