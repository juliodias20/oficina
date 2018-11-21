package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.SubOsModel;

public interface SubOsDaoInterface {

	SubOsModel salvarSubOs(SubOsModel subOsModel);
	
	void alterar(SubOsModel subOsModel);
	
	void excluir(SubOsModel subOsModel);
	
	List<SubOsModel> getSubOs();
	
	List<SubOsModel> getSubOs(Integer numOs);

	List<SubOsModel> getSubOs(Integer numOs, Integer codProduto);
	
}
