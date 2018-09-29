package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.BairroModel;

public interface BairroDaoInterface {

	BairroModel salvarBairro(BairroModel bairroModel);
	
	void alterar (BairroModel bairroModel);
	
	void excluir (BairroModel bairroModel);
	
	List<BairroModel> getBairros();
	
}
