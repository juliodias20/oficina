package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.ParceiroModel;

public interface ParceiroDaoInterface {

	ParceiroModel salvarParceiro(ParceiroModel parceiroModel);
	
	void alterar(ParceiroModel parceiroModel);
	
	void excluir(ParceiroModel parceiroModel);
	
	List<ParceiroModel> getParceiros();

	List<ParceiroModel> getParceiros(Integer parametro);
	
	List<ParceiroModel> getParceiros(String parametro, String tipoParametro);

}
