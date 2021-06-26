package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.ModeloModel;

public interface ModeloDaoInterface {

	ModeloModel salvarModelo(ModeloModel modeloModel);
	
	void alterar(ModeloModel modeloModel);
	
	void excluir(ModeloModel modeloModel);
	
	List<ModeloModel> getModelos();
	
	List<ModeloModel> getModelos(Integer codModelo);
	
}
