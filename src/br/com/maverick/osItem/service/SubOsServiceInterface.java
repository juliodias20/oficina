package br.com.maverick.service.model;

import java.util.List;

import br.com.maverick.model.model.SubOsModel;

public interface SubOsServiceInterface {

	SubOsModel salvarSubOs(SubOsModel subOsModel);
	
	void alterar(SubOsModel subOsModel);
	
	void excluir(Integer numOS, Integer codProduto);
	
	List<SubOsModel> getSubOs();
	
	List<SubOsModel> getSubOs(Integer numOs);

	List<SubOsModel> getSubOs(Integer numOs, Integer codProduto);
	
}
