package br.com.maverick.service;

import java.util.List;

import br.com.maverick.model.ParceiroModel;

public interface ParceiroServiceInterface {

	ParceiroModel salvarParceiro(ParceiroModel parceiroModel);
	
	void alterar(ParceiroModel parceiroModel);
	
	void excluir(ParceiroModel parceiroModel);
	
	List<ParceiroModel> getParceiros();

	List<ParceiroModel> getParceiros(Integer parametro);
	
	List<ParceiroModel> getParceiros(String parametro, String tipoParametro);

}
