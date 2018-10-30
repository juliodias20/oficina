package br.com.maverick.service.model;

import java.util.List;

import br.com.maverick.model.model.OsModel;

public interface OsServiceInterface {

	OsModel salvarOs(OsModel osModel);
	
	void alterar(OsModel osModel);
	
	void excluir(OsModel osModel);
	
	List<OsModel> getOs();

	List<OsModel> getOs(Integer numOs);
	
}
