package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.OsModel;

public interface OsDaoInterface {

	OsModel salvarOs(OsModel osModel);
	
	void alterar(OsModel osModel);
	
	void alterar(Integer numOs, float valorTotal);

	void excluir(OsModel osModel);
	
	List<OsModel> getOs();

	List<OsModel> getOs(Integer numOs);	
	
}
